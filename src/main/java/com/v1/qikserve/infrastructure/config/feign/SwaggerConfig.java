package com.v1.qikserve.infrastructure.config.feign;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("API for thecnical test QikServe")
                            .description("API Rest to receive orders and apply promotions to them. Cancel(delete them form database) orders". concat(
                                    " and get the list of orders with the total price of each one."))
                            .contact(new Contact()
                                    .name("Luis Felipe Mota")
                                    .email("lupesms97@gmail.com"))
                            .license(new License()
                                    .name("Apache 2.0")
                                    .url("https://spingdoc.org.com")
                            ));


        }
}

