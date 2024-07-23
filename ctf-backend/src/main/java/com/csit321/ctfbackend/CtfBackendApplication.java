package com.csit321.ctfbackend;

import com.csit321.ctfbackend.core.component.SetupDataLoader;
import com.csit321.ctfbackend.core.config.PostgresDataSource;
import com.csit321.ctfbackend.core.config.SecurityConfig;
import com.csit321.ctfbackend.core.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.modulith.Modulith;

@SpringBootApplication
@Modulith
public class CtfBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CtfBackendApplication.class, args);
    }

}
