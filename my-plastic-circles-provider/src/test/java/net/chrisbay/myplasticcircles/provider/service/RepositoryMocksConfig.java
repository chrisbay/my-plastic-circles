package net.chrisbay.myplasticcircles.provider.service;

import net.chrisbay.myplasticcircles.provider.repository.DiscManufacturerRepository;
import net.chrisbay.myplasticcircles.provider.repository.DiscRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@TestConfiguration
public class RepositoryMocksConfig {

    @Bean
    @Primary
    public DiscRepository discRepository() {
        return Mockito.mock(DiscRepository.class);
    }

    @Bean
    @Primary
    public DiscManufacturerRepository discManufacturerRepository() {
        return Mockito.mock(DiscManufacturerRepository.class);
    }

}


