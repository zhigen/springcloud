package com.zglu.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.UiConfiguration;

import java.util.List;

/**
 * @author zglu
 */
@RestController
@RequestMapping("/swagger-resources")
@AllArgsConstructor
public class SwaggerResourceController {
    private final MySwaggerResourceProvider mySwaggerResourceProvider;

    @GetMapping("/configuration/security")
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(new SecurityConfiguration(null, null, null, null, null, ApiKeyVehicle.HEADER, "api_key", ","), HttpStatus.OK);
    }

    @GetMapping("/configuration/ui")
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<>(new UiConfiguration(null, "none", "alpha", "schema", UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, null), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(mySwaggerResourceProvider.get(), HttpStatus.OK);
    }
}
