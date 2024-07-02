package kg.neobis.invoice;

import kg.neobis.core.utility.ApplicationUtility;
import kg.neobis.feign.annotation.EnableFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableFeign
@EnableDiscoveryClient
@SpringBootApplication
public class InvoiceServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(InvoiceServiceApplication.class);
        app.setBanner((environment, sourceClass, out) -> ApplicationUtility.printBanner(
                out,
                environment.getProperty("info.application.name"),
                environment.getProperty("info.application.version")
        ));
        app.run(args);
    }
}
