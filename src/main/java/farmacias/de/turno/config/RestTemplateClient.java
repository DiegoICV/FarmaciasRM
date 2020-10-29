package farmacias.de.turno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuracion de bean para consumo de servicios
 */
@Configuration
public class RestTemplateClient {

    /**
     *
     * @return componente rest template para consumo de servicios
     */
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
