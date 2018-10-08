package conference;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KakaoApplication {
    private static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.properties,";

    public static void main(String[] args) {
        new SpringApplicationBuilder(KakaoApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }
}
