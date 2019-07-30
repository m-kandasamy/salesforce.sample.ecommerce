package mk.cloud.spring.salesforce.sample.ecommerce.service.assembly;


import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecordList;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.OrderLine;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Recipient;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount;

@RestController
@RequestMapping(
		value = "/v1", 
		consumes = "application/json",
		produces = "application/json")
public class RestApi {
	
	@Autowired
	private ItemClient itemClient;
	
	@Autowired
	private RecipientClient recipientClient;
	
	@Autowired
	private OrderClient orderClient;
	
	@RequestMapping(
			value = "/order", 
			method = RequestMethod.POST)
	public @ResponseBody ResourceRecord<?> order() {
		
		ResourceRecord<RecordCount> itemCount = itemClient.count();
		ResourceRecord<RecordCount> recipientCount = recipientClient.count();
		
		ResourceRecord<Recipient> recipient = recipientClient.getByName("" + 
				random(recipientCount.getRecord().getMin(), recipientCount.getRecord().getMax()));
		
		Order order = new Order();
		order.setRecipient(recipient.getRecord());
		
		int numberOfItemsInOrder = random(1, 10);
		
		List<OrderLine> orderLines = IntStream.range(0, numberOfItemsInOrder)
			.mapToObj(i -> {
				String name = "" + random(itemCount.getRecord().getMin(), itemCount.getRecord().getMax());
				ResourceRecord<Item> item = itemClient.getByName(name);
				return item.getRecord();
			}).filter(Objects::nonNull)
			.map(item -> {
				OrderLine orderLine = new OrderLine();
				orderLine.setItem(item);
				orderLine.setCount(new Double(random(1, 20)));
				return orderLine;
			}).collect(Collectors.toList());
		
		SalesforceResultRecordList<OrderLine> list = new SalesforceResultRecordList<>();
		list.setRecords(orderLines);
		order.setOrderLines(list);
		
		return orderClient.create(order);
	}
	
	private int random(int min, int max) {
		Random rand = new Random(); 
		int selected = rand.nextInt((max - min) + 1) + min;
		System.out.println("selecting " + selected + " between " + min + " and " + max);
		return selected;
	}

}
