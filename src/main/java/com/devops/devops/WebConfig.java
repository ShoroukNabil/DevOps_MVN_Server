import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                // Allow local dev & production domains
//                .allowedOriginPatterns(
//                        "http://localhost:*",        // Angular local dev
//                        "http://127.0.0.1:*",        // Localhost via IP
//                        "http://10.10.10.131:*",     // Your LAN IP
//                        "http://angular-app:*",      // Docker/compose Angular
//                        "https://your-production-domain.com" // Production
//                )
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowCredentials(true);
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:4200",
                        "http://127.0.0.1:4200"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }
}
