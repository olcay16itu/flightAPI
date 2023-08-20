package com.Amadeus.flight.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mehmet Can",
                        email = "mehmetcanolcay123@gmail.com",
                        url = "https://github.com/olcay16itu"
                ),
                description = "Flight API documentation for Amadeus program",
                title = "Flight API Documentation - Mehmet Can Olçay",
                version = "1.0"
        ),

        servers = { @Server(
                description = "LOCAL ENV",
                url = "http://localhost:8080"
        )
        },
        security = {
        @SecurityRequirement(
                name = "basicAuth"
        )
}

)
@SecurityScheme(
        name = "basicAuth",
        description = "Basic auth desc",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in= SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
