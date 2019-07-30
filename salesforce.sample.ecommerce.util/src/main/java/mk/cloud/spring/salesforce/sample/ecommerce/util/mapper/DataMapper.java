package mk.cloud.spring.salesforce.sample.ecommerce.util.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataMapper {
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.AuthResponse webToDao(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse daoToWeb(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.AuthResponse web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.RecordCount webToDao(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount daoToWeb(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.RecordCount web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Item webToDao(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item daoToWeb(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Item web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Recipient webToDao(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Recipient web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Recipient daoToWeb(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Recipient web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.OrderLine webToDao(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.OrderLine web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.OrderLine daoToWeb(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.OrderLine web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Order webToDao(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order web);
	
	public mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order daoToWeb(
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Order web);
}
