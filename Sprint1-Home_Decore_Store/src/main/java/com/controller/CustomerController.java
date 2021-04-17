package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.advice.InvalidCredentialException;
import com.model.Category;
import com.model.Customer;
import com.model.Item;
import com.model.Login;
import com.model.Order;
import com.model.Payment;
import com.model.PaymentReceip;
import com.model.Role;
import com.model.ShoppinCard;
import com.service.CategoryService;
import com.service.CustomerService;
import com.service.ItemService;
import com.service.OrderService;
import com.service.PaymentService;
import com.service.ShoppingCardService;

import oracle.jdbc.proxy.annotation.GetCreator;
import oracle.jdbc.proxy.annotation.Post;

@RestController("/customer")
public class CustomerController {
	
	Customer customer = null;
	Item item =null;
	Payment payment = null;
	Order order =null;
	long totalAmount = 0;
	@Autowired
	CustomerService customerservice;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ItemService itemService;
	@Autowired
	OrderService orderService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	ShoppingCardService shoppingCardService ;
	
	//-------------------------------LOGIN LOGOUT------------------------
	@PostMapping("/customerLogin")
	public ResponseEntity<Customer> getCustomerLogin(@Valid @RequestBody Login loginFromBrawser) throws Exception {
		Customer customer = customerservice.getCustomerLogin(loginFromBrawser,this.customer);
		this.customer = customer;
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		
	}
	
	@GetMapping("/logoutCustomer")
	public ResponseEntity<String>  logout() throws Exception
	{
		if(customer != null) {	
			this.customer = null;
			this.item = null;
			this.totalAmount = 0;
			return new ResponseEntity<String>( "Logout Succesfully ",HttpStatus.OK);
		}
		else {
			throw new InvalidCredentialException("You Can't Login First Do You Login ");
		}
	}
	//-----------------------------CUSTOMER-----------------------------------------------------------------------------------------------------
	
	
	@GetMapping("/getCustomerDetails")
	public ResponseEntity<Customer> getDetail() throws Exception {
		return new ResponseEntity<Customer>(customerservice.getCustomerdetails(this.customer),HttpStatus.OK);
	}
	
	@PutMapping("/updateCustomerdetails")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customerNew) throws Exception {
		
		return new ResponseEntity<Customer>( customerservice.updateCustomer(customerNew,this.customer),HttpStatus.OK);
	}
	
	
	//----------------------CATEGORY--------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/getCategoryList")
	public ResponseEntity<List<Category>> getCategory() throws Exception{
		return new ResponseEntity<List<Category>>(categoryService.getCategoryList(this.customer),HttpStatus.OK);
		
	}
	
	
	//-----------------------ITEM---------------------------------------------
	
	@GetMapping("/getIteamList/{categoryId}")
	public ResponseEntity<List<Item>> getIteam(@PathVariable int categoryId) throws Exception{
		
		return new ResponseEntity<List<Item>>(itemService.getItem(categoryId,this.customer),HttpStatus.OK);
	}
	
	
	//--------------------CARD----------------------------------------------------
	@PostMapping("/addCard/{itemId}")
	public ResponseEntity<ShoppinCard> addCard(@RequestBody ShoppinCard shoppingCard,@PathVariable int itemId) throws Exception {
		return new ResponseEntity<ShoppinCard>(shoppingCardService.addcard(shoppingCard,itemId,this.customer),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteShoppingCard/{cardId}")
	public ResponseEntity<String> deletCard(@PathVariable int cardId) throws Exception
	{
		String msg = shoppingCardService.deletecard(cardId,this.customer);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	//------------------ORDER------------------------------------------------------
	
	@PostMapping("/placeOrder/{itemId}")
	public ResponseEntity<Order> placeOrder(@Valid @RequestBody Order order,@PathVariable int itemId) throws Exception
	{
		Order storeOrder = orderService.placeOrder(order,itemId,this.customer);
		this.item = storeOrder.getItem();
		this.totalAmount += storeOrder.getTotalAmount();
		this.order = storeOrder;
		return new ResponseEntity<Order>( storeOrder,HttpStatus.OK);
	}
	
	
	@GetMapping("/GetAllOrder")
	public ResponseEntity<List<Order>> getOrder() throws Exception
	{
		return new ResponseEntity<List<Order>>( orderService.getAllOrder(this.customer),HttpStatus.OK);
	}
	
	//-------------------------------PAYMENT-------------------------------------------
	@PostMapping("/makePayment")
	public ResponseEntity<Payment> makePayment(@Valid @RequestBody Payment payment) throws Exception
	{
		 Payment payment1 =  paymentService.makePayment(payment,this.customer,this.totalAmount,this.order);
		 this.payment = payment1;
		return new ResponseEntity<Payment>(payment1,HttpStatus.OK);
		
	}
	
	@GetMapping("/paymentReceip")
	public ResponseEntity<PaymentReceip> etReceip() throws Exception
	{
		return new ResponseEntity<PaymentReceip>(paymentService.getReceip(this.payment,this.customer ,this.item,this.totalAmount),HttpStatus.OK);
	}
	
	@GetMapping("/getAllCard")
	public ResponseEntity<List<ShoppinCard>> getCard()
	{
		return new ResponseEntity<List<ShoppinCard>>(shoppingCardService.getAllCard(this.customer),HttpStatus.OK);
	}
	
	
}
