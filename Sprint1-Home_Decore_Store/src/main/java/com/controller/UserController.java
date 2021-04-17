package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.aop.ThrowsAdvice;
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

import com.advice.LogInNotFoundException;
import com.model.Category;
import com.model.Customer;
import com.model.Item;
import com.model.Login;
import com.model.Order;
import com.model.Payment;
import com.model.Role;
import com.model.User;
import com.repository.UserRepository;
import com.service.CategoryService;
import com.service.CustomerService;
import com.service.ItemService;
import com.service.OrderService;
import com.service.PaymentService;
import com.service.RoleService;
import com.service.UserService;

import oracle.jdbc.proxy.annotation.Post;



@RestController("/User")
public class UserController {
	
	User user=null;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	CustomerService customerService;
	
	
	//------------------------LOGIN LOGOUT---------------------------------------------
	@PostMapping("/userLogin")
	public ResponseEntity<User> getLogin(@Valid @RequestBody Login login) throws Exception {
		User user = userService.getLogin(login,this.user);
		this.user = user;
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/logoutUser")
	public ResponseEntity<String>  logout() throws Exception
	{
		if(user != null) {	
		this.user = null;
		return new ResponseEntity<String>("Logout Succesfully",HttpStatus.OK);
		}
		else {
			throw new LogInNotFoundException("You Can't Login.You Need To Login First");
		}
	}
	
	//--------------------USER------------------------------------
	
	@GetMapping("/getUserDetails")
	public  ResponseEntity<User> getUser() throws Exception{
		User user = userService.getUser(this.user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PutMapping("/updateUserdetails")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User userNew) throws Exception
	{
		User updatUser = userService.updateUser(userNew,this.user);
		return new ResponseEntity<User>(updatUser,HttpStatus.OK);
	}
	
	
	//--------------------ITEM MANAGE------------------------------
	
	@PostMapping("/createItem/{categoryId}")
	public ResponseEntity<Item> additem(@Valid @RequestBody Item item,@PathVariable int categoryId) throws Exception {
		Item createdItem = itemService.addItem(item,categoryId,this.user);
		return new ResponseEntity<Item>(createdItem,HttpStatus.OK); 
	}

	@PutMapping("/updateItem/{itemId}")
	public ResponseEntity<Item> updateItem(@Valid @RequestBody Item item,@PathVariable int itemId) throws Exception
	{
				
		Item updatItem = itemService.updateItem(item,itemId,this.user);
		return new ResponseEntity<Item>(updatItem,HttpStatus.OK);
					
	}
	
	@GetMapping("/viewDetailsOfItem/{itemId}")
	public ResponseEntity<Item> veiwItem(@PathVariable int itemId) throws Exception {
		Item item = itemService.viewItem(itemId,this.user);
		return new ResponseEntity<Item>(item,HttpStatus.OK);
	}
	
	@GetMapping("/getItemList")
	public List<Item> getItemList() throws Exception{
		return itemService.getItemList(this.user);
	}
	
	@GetMapping("/getItemByName/{itemName}")
	public ResponseEntity<Item> getItemByName(@PathVariable String itemName) throws Exception {
		Item item = itemService.getItemByName(itemName,this.user);
		return new ResponseEntity<Item>(item,HttpStatus.OK);
		
	}
	
	@GetMapping("/getItemByPrice/{price}")
	public ResponseEntity<List<Item>> getItemByPrice(@PathVariable long price) throws Exception {
		List<Item> list = itemService.getItemByPrice(price,this.user);
		return new ResponseEntity<List<Item>>(list,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/deleteItem/{itemId}")
	public ResponseEntity<String> deleteItem(@PathVariable int itemId) throws Exception
	{
		 String msg = itemService.deleteItem(itemId,this.user);
		 return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	//--------------------CUSTOMER-----------------------------------------------------------
	
	
	@PostMapping("/addCustomer/{roleId}")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer,@PathVariable int roleId) throws Exception {
		return new ResponseEntity<Customer>( customerService.addCustomer(customer,this.user,roleId),HttpStatus.OK);
		
	}
	
	@GetMapping("/getCustomerList")
	public ResponseEntity<List<Customer>> getCustomer() throws Exception{
		
		return new  ResponseEntity<List<Customer>>(customerService.getCustomer(this.user),HttpStatus.OK);
	}
	
	@GetMapping("/viewDetailsOfCustomers/{customerId}")
	public ResponseEntity<Customer> veiwCustomer(@PathVariable int customerId) throws Exception {
		Customer customer = customerService.viewCustomer(customerId,this.user);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) throws Exception
	{
		 String msg = customerService.deleteCustomer(customerId,this.user);
		 return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	//----------------------------------ORDER---------------------------------------------
	
	@GetMapping("/getListOrder")
	public ResponseEntity<List<Order>> getOrder() throws Exception{
		
		return new  ResponseEntity<List<Order>>(orderService.getOrder(this.user),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	//----------------------CATEGORY MANAGE------------------------------------------------------
	
	@PostMapping("/createCategory")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) throws Exception {
		
		return new ResponseEntity<Category>( categoryService.addCategory(category,this.user),HttpStatus.OK);
		
	}
	
	@PutMapping(path="/updateCategory/{categoryId}") 
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category , @PathVariable int categoryId) throws Exception
	{
		return new ResponseEntity<Category>(categoryService.updateCategory(category,categoryId,this.user),HttpStatus.OK);
		
		
	}
	
	@GetMapping("/GetListOfCategory")
	public ResponseEntity<List<Category>> getCategoryList() throws Exception
	{
		return  new ResponseEntity<List<Category>>(categoryService.getCategoryList(this.user),HttpStatus.OK);
	}
	
	@GetMapping("/ViewOfCategory/{categoryId}")
	public ResponseEntity<Category> viewCategory(@PathVariable int categoryId) throws Exception
	{
		return new ResponseEntity<Category>(categoryService.viewCategory(categoryId,this.user),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) throws Exception
	{
		 String messge = categoryService.deleteCategory(categoryId,this.user);
		 return new ResponseEntity<String>(messge,HttpStatus.OK);
	}
	
	//--------------------------------Role------------------------------------------------------------
	
	@PostMapping("/addRole")
	public ResponseEntity<Role> addRole(@RequestBody Role role) throws Exception
	{
		return new ResponseEntity<Role>( roleService.addRole(role,this.user),HttpStatus.OK);
	}
	
	@GetMapping("/getListRole")
	public ResponseEntity<List<Role>> getRole() throws Exception {
		return new ResponseEntity<List<Role>>(roleService.getRole(this.user),HttpStatus.OK); 
	}
	
	//-------------------------PAYMENT--------------------------------------------------------------------
	
	@GetMapping("/paymentDetails")
	public ResponseEntity<List<Payment>> paymentDetails() throws Exception
	{
		List<Payment > list = paymentService.paymentDetails(this.user);
		return new ResponseEntity<List<Payment>>(list,HttpStatus.OK);
	}
	
}
