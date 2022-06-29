package peaksoft.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    public OpenAPI customerOpenApi(){
        return new OpenAPI().info(new Info().title("CRUD LMS PROJECT").description("author: Kubat Ashymjanov"))
                .security(List.of(new SecurityRequirement().addList("Bearer Token")));
    }
}
