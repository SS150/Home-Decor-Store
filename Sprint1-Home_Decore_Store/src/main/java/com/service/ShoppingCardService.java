package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.advice.IDNotFoundException;
import com.advice.LogInNotFoundException;
import com.model.Customer;
import com.model.Item;
import com.model.ShoppinCard;
import com.repository.ItemRepository;
import com.repository.ShoppingCardRepository;

@Service
public class ShoppingCardService {
	
	@Autowired
	ShoppingCardRepository shoppingcardRepository;
	
	@Autowired
	ItemRepository itemRepository;

	public ShoppinCard addcard(ShoppinCard shoppingCard, int itemId,Customer customer) throws Exception
	{
		// TODO Auto-generated method stub
		if(customer != null) 
		{		
		Item item = itemRepository.findById(itemId).orElseThrow(()->new IDNotFoundException("Item Are Not Present Into DataBase"));
		shoppingCard.setItem(item);
		shoppingCard.setCustomer(customer);
		return shoppingcardRepository.save(shoppingCard);
		}
		else {
			throw new ResourceNotFoundException("You Can't Login First Do You Login ");
		}
	}
	
	public String deletecard(int cardId,Customer customer) throws Exception {
		if(customer != null)//handle exception here for payment
		{
		
			ShoppinCard card = shoppingcardRepository.findById(cardId).orElseThrow(()->new IDNotFoundException("Card ID  Not Found "));
			shoppingcardRepository.delete(card);
			return "Delete Succesfuly ";
		}
		else {
			throw new LogInNotFoundException("You Can't Login First Do You Login ");
		}
	}

	public List<ShoppinCard> getAllCard(Customer customer) {
		if(customer != null) {
			return shoppingcardRepository.findAllByCId(customer.getPid());
		}
		return null;
		
			}


}
