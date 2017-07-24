package org.vmis.task.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vmis.task.dto.converter.GameDtoConverter;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Configuration
public class ConverterConfiguration {

    @Bean
    GameDtoConverter buildGameDtoConverter() {
        return new GameDtoConverter();
    }
}
