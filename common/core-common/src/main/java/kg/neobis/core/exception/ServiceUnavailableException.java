package kg.neobis.core.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static java.util.Objects.nonNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceUnavailableException extends RuntimeException {


    private ServiceUnavailableException(
            String serviceName,
            String message
    ) {
        super(nonNull(message)? message : serviceName);
    }

    public static ServiceUnavailableException serviceUnavailable(String serviceName) {
        return new ServiceUnavailableException( serviceName, null);
    }

}
