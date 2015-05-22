package config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import provider.IdProvider;
import provider.impl.RandomIdProvider;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by mmatosevic on 21.5.2015.
 */
@Configuration
public class MainConfiguration {

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
        HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
        factory.setEntityManagerFactory(emf);
        return factory;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ret = new DriverManagerDataSource();
        ret.setDriverClassName("org.h2.Driver");
        ret.setUsername("sa");
        ret.setPassword("");
        ret.setUrl("jdbc:h2:file:C:/Users/mmatosevic/database/MainDB");
        return ret;
    }

    @Bean
    public IdProvider idProvider(){
        return new RandomIdProvider();
    }
}
