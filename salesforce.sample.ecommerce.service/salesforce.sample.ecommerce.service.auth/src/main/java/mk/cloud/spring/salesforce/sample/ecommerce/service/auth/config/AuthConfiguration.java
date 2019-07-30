package mk.cloud.spring.salesforce.sample.ecommerce.service.auth.config;

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
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import mk.cloud.spring.salesforce.sample.ecommerce.service.auth.client.SalesforceAuthClient;
import mk.cloud.spring.salesforce.sample.ecommerce.util.UtilConfiguration;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceVariables;

@Configuration
@Import(UtilConfiguration.class)
public class AuthConfiguration {
	
	@Autowired
	private SalesforceVariables salesforceVariables;
	
	@Bean
	@ConfigurationProperties(prefix="salesforce")
	public SalesforceVariables salesforceVariables() {
		return new SalesforceVariables();
	}
	
	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("salesforceToken");
		cacheManager.setCaffeine(caffeineCacheBuilder());
		return cacheManager;
	}

	Caffeine<Object, Object> caffeineCacheBuilder() {
		String expiry = salesforceVariables.getTokenExpiry();
		return Caffeine.newBuilder().initialCapacity(100).maximumSize(500).expireAfterWrite(
				new Long(expiry), TimeUnit.SECONDS)
				.weakKeys().recordStats();
	}
	
    @Bean
    public SalesforceAuthClient salesforceAuthClient() {
    	System.out.println(salesforceVariables.getAuthUrl());
    	return Feign.builder()
	    	.client(new OkHttpClient())
	    	.logger(new Slf4jLogger(AuthConfiguration.class))
	    	.logLevel(Logger.Level.FULL)
			.errorDecoder(new ErrorDecoder.Default())
			.encoder(new JacksonEncoder())
			.decoder(new JacksonDecoder())
			.retryer(new Retryer.Default(500l, 3000l, 3))
			.requestInterceptor(salesforceAuthInterceptor())
			.target(SalesforceAuthClient.class, salesforceVariables.getAuthUrl());
    }

    @Bean
    public RequestInterceptor salesforceAuthInterceptor() {
        return requestTemplate -> {
        	requestTemplate.query("client_id", salesforceVariables.getClientId());
        	requestTemplate.query("client_secret", salesforceVariables.getClientSecret());
        	System.out.println("entering salesforceAuthInterceptor");
        };
    }
}