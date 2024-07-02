package kg.neobis.test;

import kg.neobis.core.utility.ApplicationUtility;
import kg.neobis.feign.annotation.EnableFeign;
import kg.neobis.swagger.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableSwagger
@EnableFeign
@EnableDiscoveryClient
@SpringBootApplication
public class TestServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TestServiceApplication.class);
        app.setBanner((environment, sourceClass, out) -> ApplicationUtility.printBanner(
                out,
                environment.getProperty("info.application.name"),
                environment.getProperty("info.application.version")
        ));
        app.run(args);
    }
}
