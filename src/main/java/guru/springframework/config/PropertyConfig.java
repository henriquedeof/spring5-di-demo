package guru.springframework.config;

import guru.springframework.examplebeans.FakeDataSource;
import guru.springframework.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySource({"classpath:datasource.properties", "classpath:jms.properties"})//annotation that indicates shows which property files are read by the system. OLD FORMAT
//@PropertySources({
//        @PropertySource("classpath:datasource.properties"),
//        @PropertySource("classpath:jms.properties")
//})

//NOTE: If I am not using the annotations above, means that I am building a pure Spring Boot application.
// So, I just need to use the application.properties file, which is automatically loaded by the Spring Boot.
public class PropertyConfig {

//    @Autowired
//    private Environment env; //example of object which can get Environment Variables values.

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
//      fakeDataSource.setUser(env.getProperty("USERNAME"));//example of getting a value from Environment Property. It also overrides a property file.
        fakeDataSource.setUser(this.user);
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

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties(){//This class is reading the property file
//        return new PropertySourcesPlaceholderConfigurer();
//    }

}
