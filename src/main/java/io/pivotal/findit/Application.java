package io.pivotal.findit;
 
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@ComponentScan
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration 
@PropertySource("classpath:application.properties")
public class Application extends SpringBootServletInitializer {

	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(Application.class).web(true);
	    }

	    public static void main(String[] args) {
	        new SpringApplicationBuilder(Application.class).web(true).run(args);
	    }
        
}



	