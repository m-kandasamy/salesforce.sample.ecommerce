package mk.cloud.spring.salesforce.sample.ecommerce.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import mk.cloud.spring.salesforce.sample.ecommerce.util.mapper.DataMapper;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecordList;

public class MapperTest {
	
	@Test
	public void authMapTest() {
		DataMapper mapper = Mappers.getMapper(DataMapper.class);
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.AuthResponse dao = 
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.AuthResponse
				.builder().accessToken("accesstoken").instanceUrl("x.y.z.com/a/b/c").id("123456").issuedAt(new Date())
				.tokenType("Bearer").signature("xyzabc").build();
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse web = mapper.daoToWeb(dao);
		
		assertEquals("equals", dao.getAccessToken(), web.getAccessToken());
	}
	
	@Test
	public void itemMapTest() {
		DataMapper mapper = Mappers.getMapper(DataMapper.class);
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Item daoItem = 
				mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Item
					.builder().key("key").id(1l).code("code").description("description").unit("unit").price(0.0).build();
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item webItem = mapper.daoToWeb(daoItem);
		
		assertEquals("equals", daoItem.getKey(), webItem.getKey());
	}
	
	@Test
	public void recipientMapTest() {
		DataMapper mapper = Mappers.getMapper(DataMapper.class);
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Recipient daoRecipient = 
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Recipient
				.builder().key("key").id(1l).code("code").firstName("firstName").lastName("lastName")
				.dateOfBirth("1/1/2000").email("email@domain.com").build();
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Recipient webRecipient = mapper.daoToWeb(daoRecipient);
		
		assertEquals("equals", daoRecipient.getKey(), webRecipient.getKey());
	}
	
	@Test
	public void orderLineMapTest() {
		DataMapper mapper = Mappers.getMapper(DataMapper.class);
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Item daoItem = 
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Item
				.builder().key("key").id(1l).code("code").description("description").unit("unit").price(0.0).build();
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.OrderLine daoOrderLine = 
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.OrderLine
				.builder().key("key").id(1l).code("code").item(daoItem).count(10.0).total(20.0).build();
	
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.OrderLine webOrderLine = mapper.daoToWeb(daoOrderLine);
		
		assertEquals("equals", daoOrderLine.getItem().getKey(), webOrderLine.getItem().getKey());
	}
	
	@Test
	public void orderMapTest() {
		DataMapper mapper = Mappers.getMapper(DataMapper.class);
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Item daoItem = 
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Item
				.builder().key("key").id(1l).code("code").description("description").unit("unit").price(0.0).build();
	
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.OrderLine daoOrderLine = 
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.OrderLine
				.builder().key("key").id(1l).code("code").item(daoItem).count(10.0).total(20.0).build();
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Recipient daoRecipient = 
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Recipient
				.builder().key("key").id(1l).code("code").firstName("firstName").lastName("lastName")
				.dateOfBirth("1/1/2000").email("email@domain.com").build();
	
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Order daoOrder = 
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Order
				.builder().key("key").id(1l).code("code").recipient(daoRecipient).createdDate("today").total(0.0)
				.orderLines(new SalesforceResultRecordList<>(1, Arrays.asList(daoOrderLine))).build();
		
		mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order webOrder = mapper.daoToWeb(daoOrder);
		
		assertEquals("equals", daoOrder.getOrderLines().getRecords().get(0).getKey(), 
				webOrder.getOrderLines().getRecords().get(0).getKey());
	}
}
