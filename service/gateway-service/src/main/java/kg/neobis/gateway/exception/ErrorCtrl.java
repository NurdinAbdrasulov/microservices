package kg.neobis.gateway.exception;

import kg.neobis.core.exception.ServiceUnavailableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/error")
public class ErrorCtrl {

    @PostMapping("/service-unavailable/{serviceName}")
    public void serviceUnavailable(
            @PathVariable String serviceName
    ) {
        throw ServiceUnavailableException.serviceUnavailable(serviceName);
    }
}
