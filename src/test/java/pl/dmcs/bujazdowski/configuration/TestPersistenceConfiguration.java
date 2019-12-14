package pl.dmcs.bujazdowski.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "pl.dmcs.bujazdowski.dao")
@EnableTransactionManagement
public class TestPersistenceConfiguration {



}
