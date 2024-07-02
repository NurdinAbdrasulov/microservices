package kg.neobis.feign.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.RequestInterceptor;
import kg.neobis.core.utility.CipherUtility;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SortJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeignClientConfiguration {

    @Value("${feign.cipher.secret-key}")
    String KEY_CIPHER;

    @Value("${feign.cipher.algorithm}")
    String ALGORITHM_CIPHER;

    @Value("${feign.header-name.session-id}")
    String SESSION_ID_HEADER_NAME;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            String encodedSessionId = getEncodedSessionId();
            if (Objects.nonNull(encodedSessionId)) {
                template.header(SESSION_ID_HEADER_NAME, encodedSessionId);
            }
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new PageJacksonModule());
        objectMapper.registerModule(new SortJacksonModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));

        return objectMapper;
    }

    private String getEncodedSessionId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication)
                .filter(auth -> auth.getDetails() instanceof Map)
                .map(auth -> (Map<String, Object>) auth.getDetails())
                .map(details -> (String) details.get("sessionId"))
                .map(sessionId -> CipherUtility.encrypt(sessionId, KEY_CIPHER, ALGORITHM_CIPHER))
                .orElse(null);
    }

}
