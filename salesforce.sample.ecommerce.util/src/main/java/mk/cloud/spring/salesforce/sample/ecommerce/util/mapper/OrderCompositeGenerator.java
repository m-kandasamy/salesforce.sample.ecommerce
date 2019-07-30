package mk.cloud.spring.salesforce.sample.ecommerce.util.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import mk.cloud.spring.salesforce.sample.ecommerce.util.model.composite.CompositeRequest;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.composite.SalesforceCompositeRequest;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.OrderLine;

public class OrderCompositeGenerator {
	
	public SalesforceCompositeRequest generate(Order order) {
		SalesforceCompositeRequest salesforceCompositeRequest = 
				new SalesforceCompositeRequest();
		
		salesforceCompositeRequest.setAllOrNone(true);
		
		CompositeRequest orderCompositeRequest = new CompositeRequest();
		orderCompositeRequest.setMethod("POST");
		orderCompositeRequest.setReferenceId("Order");
		orderCompositeRequest.setUrl("/services/data/v43.0/sobjects/Order__c/");
		orderCompositeRequest.getBody().put("Recipient__c", order.getRecipient().getKey());
		salesforceCompositeRequest.getCompositeRequest().add(orderCompositeRequest);
		salesforceCompositeRequest.getCompositeRequest().addAll(
			IntStream.range(0, order.getOrderLines().getRecords().size())
			.mapToObj(i -> {
				OrderLine orderLine = order.getOrderLines().getRecords().get(i);
				CompositeRequest orderLineCompositeRequest = new CompositeRequest();
				orderLineCompositeRequest.setMethod("POST");
				orderLineCompositeRequest.setReferenceId("OrderLine" + i);
				orderLineCompositeRequest.setUrl("/services/data/v43.0/sobjects/OrderLine__c/");
				orderLineCompositeRequest.getBody().put("Order__c", "@{Order.id}");
				orderLineCompositeRequest.getBody().put("Count__c", orderLine.getCount());
				orderLineCompositeRequest.getBody().put("Item__c", orderLine.getItem().getKey());
				return orderLineCompositeRequest;
			}).collect(Collectors.toList())
		);
		
		return salesforceCompositeRequest;
		
	}

}