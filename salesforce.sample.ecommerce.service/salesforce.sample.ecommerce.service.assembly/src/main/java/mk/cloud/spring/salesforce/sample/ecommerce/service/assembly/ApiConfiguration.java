package mk.cloud.spring.salesforce.sample.ecommerce.service.assembly;


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
    public OrderClient orderClient() {
    	System.out.println(ecommerceVariables.getAuthUrl());
    	return Feign.builder()
	    	.client(new OkHttpClient())
	    	.logger(new Slf4jLogger(ApiConfiguration.class))
	    	.logLevel(Logger.Level.FULL)
			.errorDecoder(new ErrorDecoder.Default())
			.encoder(new JacksonEncoder())
			.decoder(new JacksonDecoder())
			.retryer(new Retryer.Default(500l, 3000l, 3))
			.requestInterceptor(ordernterceptor())
			.target(OrderClient.class, ecommerceVariables.getOrderApiUrl());
    }
    
    @Bean
    public RequestInterceptor ordernterceptor() {
        return requestTemplate -> {
        	System.out.println("entering ordernterceptor");
        };
    }
    
    @Bean
    public ItemClient itemClient() {
    	System.out.println(ecommerceVariables.getAuthUrl());
    	return Feign.builder()
	    	.client(new OkHttpClient())
	    	.logger(new Slf4jLogger(ApiConfiguration.class))
	    	.logLevel(Logger.Level.FULL)
			.errorDecoder(new ErrorDecoder.Default())
			.encoder(new JacksonEncoder())
			.decoder(new JacksonDecoder())
			.retryer(new Retryer.Default(500l, 3000l, 3))
			.requestInterceptor(itemInterceptor())
			.target(ItemClient.class, ecommerceVariables.getItemApiUrl());
    }
    
    @Bean
    public RequestInterceptor itemInterceptor() {
        return requestTemplate -> {
        	System.out.println("entering itemInterceptor");
        };
    }
    
    @Bean
    public RecipientClient recipientClient() {
    	System.out.println(ecommerceVariables.getAuthUrl());
    	return Feign.builder()
	    	.client(new OkHttpClient())
	    	.logger(new Slf4jLogger(ApiConfiguration.class))
	    	.logLevel(Logger.Level.FULL)
			.errorDecoder(new ErrorDecoder.Default())
			.encoder(new JacksonEncoder())
			.decoder(new JacksonDecoder())
			.retryer(new Retryer.Default(500l, 3000l, 3))
			.requestInterceptor(recipientInterceptor())
			.target(RecipientClient.class, ecommerceVariables.getRecipientApiUrl());
    }
    
    @Bean
    public RequestInterceptor recipientInterceptor() {
        return requestTemplate -> {
        	System.out.println("entering recipientInterceptor");
        };
    }
 }