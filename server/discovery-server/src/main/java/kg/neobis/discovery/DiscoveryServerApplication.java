package kg.neobis.discovery;

import kg.neobis.core.utility.ApplicationUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DiscoveryServerApplication.class);
        app.setBanner((environment, sourceClass, out) -> ApplicationUtility.printBanner(
                out,
                environment.getProperty("info.application.name"),
                environment.getProperty("info.application.version")
        ));
        app.run(args);
    }
}
