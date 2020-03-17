package guru.springframework.config;

import guru.springframework.examplebeans.FakeDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:datasource.properties")//adding annotation that indicates which file must be read.
public class PropertyConfig {

    @Value("${guru.user}")
    private String user;

    @Value("${guru.password}")
    private String password;

    @Value("${guru.dburl}")
    private String dburl;

    @Bean
    public FakeDataSource fakeDataSource(){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUser(user);
        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(dburl);

        return fakeDataSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){//This class is reading the property file
        return new PropertySourcesPlaceholderConfigurer();
    }

}
