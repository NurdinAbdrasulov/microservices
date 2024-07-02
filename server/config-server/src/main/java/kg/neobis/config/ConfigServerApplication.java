package kg.neobis.config;

import kg.neobis.core.utility.ApplicationUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigServer
@ComponentScan(lazyInit = true)
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ConfigServerApplication.class);
        app.setBanner((environment, sourceClass, out) -> ApplicationUtility.printBanner(
                out,
                environment.getProperty("info.application.name"),
                environment.getProperty("info.application.version")
        ));
        app.run(args);
    }
}
