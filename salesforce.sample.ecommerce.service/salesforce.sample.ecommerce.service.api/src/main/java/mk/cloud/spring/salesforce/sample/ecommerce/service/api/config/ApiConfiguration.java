package mk.cloud.spring.salesforce.sample.ecommerce.service.api.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.benmanes.caffeine.cache.Caffeine;

import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;
import feign.Target;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import mk.cloud.spring.salesforce.sample.ecommerce.service.api.client.EcommerceAuthClient;
import mk.cloud.spring.salesforce.sample.ecommerce.service.api.client.SalesforceClient;
import mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.SalesforceApiService;
import mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.EcommerceAuthService;
import mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.impl.SalesforceApiServiceImpl;
import mk.cloud.spring.salesforce.sample.ecommerce.util.UtilConfiguration;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.EcommerceVariables;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceVariables;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthRequest;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Item;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Order;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.OrderLine;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Recipient;
import mk.cloud.spring.salesforce.sample.ecommerce.util.reader.DataReader;

@Configuration
@Import(UtilConfiguration.class)
public class ApiConfiguration {
	
	
	@Autowired
	private DataReader dataReader;
	
	@Autowired
	private EcommerceVariables ecommerceVariables;
	
	@Bean
	@ConfigurationProperties(prefix="salesforce")
	public SalesforceVariables salesforceVariables() {
		return new SalesforceVariables();
	}
	
	@Bean
	@ConfigurationProperties(prefix="ecommerce")
	public EcommerceVariables ecommerceVariables() {
		return new EcommerceVariables();
	}
	
	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("ecommerceToken");
		cacheManager.setCaffeine(caffeineCacheBuilder());
		return cacheManager;
	}

	Caffeine<Object, Object> caffeineCacheBuilder() {
		String expiry = ecommerceVariables.getTokenExpiry();
		return Caffeine.newBuilder().initialCapacity(100).maximumSize(500).expireAfterWrite(
				new Long(expiry), TimeUnit.SECONDS)
				.weakKeys().recordStats();
	}
	
    @Bean
    public EcommerceAuthClient salesforceAuthClient() {
    	System.out.println(ecommerceVariables.getAuthUrl());
    	return Feign.builder()
	    	.client(new OkHttpClient())
	    	.logger(new Slf4jLogger(ApiConfiguration.class))
	    	.logLevel(Logger.Level.FULL)
			.errorDecoder(new ErrorDecoder.Default())
			.encoder(new JacksonEncoder())
			.decoder(new JacksonDecoder())
			.retryer(new Retryer.Default(500l, 3000l, 3))
			.requestInterceptor(ecommerceAuthInterceptor())
			.target(EcommerceAuthClient.class, ecommerceVariables.getAuthUrl());
    }
    
    @Bean
    public RequestInterceptor ecommerceAuthInterceptor() {
        return requestTemplate -> {
        	AuthRequest authRequest = new AuthRequest();
        	authRequest.setUsername(ecommerceVariables.getUsername());
        	authRequest.setPassword(ecommerceVariables.getPassword());
        	requestTemplate.body(dataReader.write(authRequest));
        	System.out.println("entering ecommerceAuthInterceptor");
        };
    }
    
    @Bean
    public SalesforceClient salesforceClient() {
    	return Feign.builder()
	    	.client(new OkHttpClient())
	    	.logger(new Slf4jLogger(ApiConfiguration.class))
	    	.logLevel(Logger.Level.FULL)
			.errorDecoder(new ErrorDecoder.Default())
			.encoder(new JacksonEncoder())
			.decoder(new JacksonDecoder())
			.retryer(new Retryer.Default(500l, 3000l, 3))
			.requestInterceptor(salesforceInterceptor())
			.target(Target.EmptyTarget.create(SalesforceClient.class));
    }
    

    @Bean
    public RequestInterceptor salesforceInterceptor() {
    	
        return new RequestInterceptor() {
			@Autowired
			private EcommerceAuthService ecommerceAuthService;
        	
			@Override
			public void apply(RequestTemplate template) {
				ResourceRecord<AuthResponse> response = ecommerceAuthService.login();
				AuthResponse authResponse = response.getRecord();
				String headerValue = authResponse.getTokenType() + " " + authResponse.getAccessToken();
				System.out.println("salesforceInterceptor " + headerValue);
				template.header("Authorization", headerValue);
				System.out.println("entering salesforceInterceptor");
			}
		};
    }
    
    @Bean
    public SalesforceApiService<Item> itemSalesforceApiService() {
    	return new SalesforceApiServiceImpl<Item>("Item__c", Item.class);
    }
	
    @Bean
    public SalesforceApiService<Recipient> recipientSalesforceApiService() {
    	return new SalesforceApiServiceImpl<Recipient>("Recipient__c", Recipient.class);
    }
	
    @Bean
    public SalesforceApiService<Order> orderSalesforceApiService() {
    	return new SalesforceApiServiceImpl<Order>("Order__c", Order.class);
    }
    
    @Bean
    public SalesforceApiService<OrderLine> orderLineSalesforceApiService() {
    	return new SalesforceApiServiceImpl<OrderLine>("OrderLine__c", OrderLine.class);
    }
}