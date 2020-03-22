package guru.springframework.config;

import guru.springframework.examplebeans.FakeDataSource;
import guru.springframework.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
//@PropertySource({"classpath:datasource.properties", "classpath:jms.properties"})//annotation that indicates shows which property files are read by the system. OLD FORMAT
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:jms.properties")
})
public class PropertyConfig {

    @Autowired
    private Environment env; //example of object which can get Environment Variables values.

    @Value("${guru.user}")
    private String user;

    @Value("${guru.password}")
    private String password;

    @Value("${guru.dburl}")
    private String dburl;

    @Value("${guru.jms.user}")
    private String jmsUser;

    @Value("${guru.jms.password}")
    private String jmsPassword;

    @Value("${guru.jms.url}")
    private String jmsUrl;

    @Bean
    public FakeDataSource fakeDataSource(){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUser(env.getProperty("USERNAME"));//example of getting a value from Environment Property. It also overrides a property file.
        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(dburl);

        return fakeDataSource;
    }

    @Bean
    public FakeJmsBroker fakeJmsBroker(){
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUser(this.jmsUser);
        fakeJmsBroker.setPassword(this.jmsPassword);
        fakeJmsBroker.setUrl(this.jmsUrl);

        return fakeJmsBroker;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){//This class is reading the property file
        return new PropertySourcesPlaceholderConfigurer();
    }

}
