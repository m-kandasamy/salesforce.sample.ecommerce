package mk.cloud.spring.salesforce.sample.ecommerce.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class STARTER {
    
	protected final Log logger = LogFactory.getLog(getClass());
    
    public static void main(String[] args) {
        SpringApplication.run(STARTER.class, args);
    }
}