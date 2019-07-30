package mk.cloud.spring.salesforce.sample.ecommerce.util;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mk.cloud.spring.salesforce.sample.ecommerce.util.mapper.DataMapper;
import mk.cloud.spring.salesforce.sample.ecommerce.util.mapper.OrderCompositeGenerator;
import mk.cloud.spring.salesforce.sample.ecommerce.util.reader.DataReader;

@Configuration
public class UtilConfiguration {
	
	@Bean
	public DataReader dataReader() {
		return new DataReader();
	}
	
	@Bean
	public DataMapper dataMapper() {
		return Mappers.getMapper(DataMapper.class);
	}
	
	@Bean
	public OrderCompositeGenerator orderCompositeGenerator() {
		return new OrderCompositeGenerator();
	}

}
