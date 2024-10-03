package football.cafe;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInCheckIntercepter())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/h2-console","/error","/signin", "/news", "/signup", "/css/**", "/js/**", "/images/**", "/static/**", "/*.ico");
    }
}
