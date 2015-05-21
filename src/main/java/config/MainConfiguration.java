package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import provider.IdProvider;
import provider.impl.RandomIdProvider;

/**
 * Created by mmatosevic on 21.5.2015.
 */
@Configuration
public class MainConfiguration {

    @Bean
    public IdProvider idProvider(){
        return new RandomIdProvider();
    }
}
