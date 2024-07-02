package kg.neobis.core.utility;

import lombok.NoArgsConstructor;

import java.io.PrintStream;
import java.time.LocalDate;

import static java.util.Objects.nonNull;

@NoArgsConstructor
public class ApplicationUtility {

    public static void printBanner(
            PrintStream ps,
            String applicationName,
            String applicationVersion
    ) {
        String application = "";

        if (nonNull(applicationVersion)) {
            application = "%s (v%s)".formatted(applicationName, applicationVersion);

        }

        ps.printf("""
                 __   __     ______     ______     ______     __     ______   
                /\\ "-.\\ \\   /\\  ___\\   /\\  __ \\   /\\  == \\   /\\ \\   /\\  ___\\  
                \\ \\ \\-.  \\  \\ \\  __\\   \\ \\ \\/\\ \\  \\ \\  __<   \\ \\ \\  \\ \\___  \\ 
                 \\ \\_\\\\"\\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\  \\/\\_____\\
                  \\/_/ \\/_/   \\/_____/   \\/_____/   \\/_____/   \\/_/   \\/_____/
                                
                """, "\u001B[36m", "\u001B[37m", "\u001B[0m", application, applicationVersion);
    }
}
