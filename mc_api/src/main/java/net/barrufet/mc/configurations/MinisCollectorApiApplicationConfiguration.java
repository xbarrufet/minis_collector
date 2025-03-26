package net.barrufet.mc.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinisCollectorApiApplicationConfiguration {

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

}
