package ia.example.shoppinglist.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ia.example.shoppinglist.rest.service.UniversalMapper;

@Configuration
public class TestConfigUniversalMapper {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setFieldMatchingEnabled(true).setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return mapper;
    }

    @Bean
    public UniversalMapper universalMapper(ModelMapper modelMapper) {
        return new UniversalMapper(modelMapper);
    }

}
