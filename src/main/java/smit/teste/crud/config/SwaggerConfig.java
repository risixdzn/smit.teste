package smit.teste.crud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        List<Tag> tags = List.of(
                new Tag().name("Products").description("Manage the products in the system.")
        );

        return new OpenAPI().info(new Info().title("Crud API").version("1.0")).tags(tags);
    }
}
