package kg.neobis.gateway.configuration.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ServiceRoute {

    INVOICE("invoice", "lb://invoice", true),
    ;

    String id;
    String uri;
    Boolean isActive;

    ServiceRoute(
            String id,
            String uri,
            Boolean isActive
    ) {
        this.id = id;
        this.uri = uri;
        this.isActive = isActive;
    }

    public static List<ServiceRoute> getServiceRoutes() {
        return Arrays
                .stream(values())
                .toList();
    }
}
