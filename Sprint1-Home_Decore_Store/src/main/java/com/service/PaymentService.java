package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.LogInNotFoundException;
import com.model.Customer;
import com.model.Item;
import com.model.Order;
import com.model.Payment;
import com.model.PaymentReceip;
import com.model.User;
import com.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public List<Payment> paymentDetails(User user) throws Exception {
		if(user != null) {
		
			return  paymentRepository.findAll();
		}
		else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
	}
	
	public Payment makePayment(Payment payment,Customer customer,long totalAmount,Order placeOrder) throws Exception {
		
		if(customer != null)//handle exception here
		{
			if(placeOrder != null) {
				payment.setCustomer(customer);
				payment.setAmount(totalAmount);
				return paymentRepository.save(payment);
			}else {
				throw new LogInNotFoundException("Doing Place Order First ");
			}
			
		}
		else {
			throw new LogInNotFoundException("You Can't Login First Do You Login ");
		}
	}

	public PaymentReceip getReceip(Payment payment,Customer customer,Item item,long totalAmount) throws Exception {
		
		if(customer != null)
		{
			if(payment != null) {
		
		
					PaymentReceip paymentReceip = new PaymentReceip(customer.getName(), item.getItemName(), payment.getCreatedAt(), totalAmount);
					return paymentReceip;
			}else {
				throw new LogInNotFoundException("Do payment First ");
			}
		}
		else {
			throw new LogInNotFoundException("You Can't Login First Do You Login ");
		}
	}


}
