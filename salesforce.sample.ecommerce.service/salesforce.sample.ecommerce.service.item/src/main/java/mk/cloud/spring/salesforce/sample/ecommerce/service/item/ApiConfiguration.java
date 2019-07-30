package mk.cloud.spring.salesforce.sample.ecommerce.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import mk.cloud.spring.salesforce.sample.ecommerce.util.UtilConfiguration;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.EcommerceVariables;

@Configuration
@Import(UtilConfiguration.class)
public class ApiConfiguration {
	
	@Autowired
	private EcommerceVariables ecommerceVariables;
	
	
	@Bean
	@ConfigurationProperties(prefix="ecommerce")
	public EcommerceVariables ecommerceVariables() {
		return new EcommerceVariables();
	}
	
    @Bean
    public EcommerceClient ecommerceClient() {
    	System.out.println(ecommerceVariables.getAuthUrl());
    	return Feign.builder()
	    	.client(new OkHttpClient())
	    	.logger(new Slf4jLogger(ApiConfiguration.class))
	    	.logLevel(Logger.Level.FULL)
			.errorDecoder(new ErrorDecoder.Default())
			.encoder(new JacksonEncoder())
			.decoder(new JacksonDecoder())
			.retryer(new Retryer.Default(500l, 3000l, 3))
			.requestInterceptor(ecommerceInterceptor())
			.target(EcommerceClient.class, ecommerceVariables.getApiUrl());
    }
    
    @Bean
    public RequestInterceptor ecommerceInterceptor() {
        return requestTemplate -> {
        	System.out.println("entering item ecommerceInterceptor");
        };
    }
 }