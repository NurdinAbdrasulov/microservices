package kg.neobis.gateway;

import kg.neobis.core.utility.ApplicationUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        app.setBanner((environment, sourceClass, out) -> ApplicationUtility.printBanner(
                out,
                environment.getProperty("info.application.name"),
                environment.getProperty("info.application.version")
        ));
        app.run(args);
    }
}
