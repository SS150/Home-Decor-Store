package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.IDNotFoundException;
import com.advice.LogInNotFoundException;
import com.model.Customer;
import com.model.Item;
import com.model.Order;
import com.model.User;
import com.repository.ItemRepository;
import com.repository.OrderRepository;

@Service
public class OrderService {
	
		@Autowired
		OrderRepository orderRepository;
		
		@Autowired
		ItemRepository itemRepository;
	
		public List<Order> getOrder(User user) throws Exception {
		
			if(user != null) {
				return orderRepository.findAll();
			}
			else {
				throw new LogInNotFoundException("You Can't Login.You Need To Login First");
			}
		}
		
		public Order placeOrder(Order order, int itemId,Customer customer) throws Exception{
			
			if(customer != null) 
			{	
				
			Item item = itemRepository.findById(itemId).orElseThrow(()->new IDNotFoundException("Item Are Not Present Into DataBase"));
			//this.item=item;
			order.setItem(item);
			order.setCustomer(customer);
			order.setTotalAmount(item.getPrice());
				
			//this.totalAmount += item.getPrice() ;
			
			return orderRepository.save(order);
			
			}
			else {
				throw new LogInNotFoundException("You Can't Login First Do You Login ");
			}
		}
		
		public List<Order> getAllOrder(Customer customer) throws Exception{
			// TODO Auto-generated method stub
			if(customer != null)
			{
			int customerId = customer.getPid();
			return orderRepository.findByCustomerId(customerId);
			}
			else {
				throw new LogInNotFoundException("You Can't Login First Do You Login ");
			}
			
		}
	
	

}
