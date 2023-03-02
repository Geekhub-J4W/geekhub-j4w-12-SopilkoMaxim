package edu.geekhub;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

    @Bean
    @Qualifier("postgresDb")
    public HikariDataSource dataSource(@Value("${database.username}") String username,
                                       @Value("${database.password}") String password,
                                       @Value("${database.url}") String url,
                                       @Value("${database.driver}") String driver){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);

        return new HikariDataSource(hikariConfig);
    }

    @Bean("myPostgresDb")
    public NamedParameterJdbcTemplate researchCenterNamedJdbc(@Qualifier("postgresDb") HikariDataSource dataSource)
    {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(@Qualifier("postgresDb") HikariDataSource dataSource){
        return Flyway.configure()
                .dataSource(dataSource)
                .outOfOrder(true)
                .locations("classpath:/db/flyway")
                .cleanDisabled(true)
                .baselineOnMigrate(true)
                .load();
    }
}
