package com.infosys.casperstay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.casperstay.dao.CartDao;
import com.infosys.casperstay.dao.HotelDao;
import com.infosys.casperstay.dao.UserDao;
import com.infosys.casperstay.dto.AddToCartRequest;
import com.infosys.casperstay.dto.CartDataResponse;
import com.infosys.casperstay.dto.CartResponse;
import com.infosys.casperstay.model.Cart;
import com.infosys.casperstay.model.Hotel;
import com.infosys.casperstay.model.User;

@RestController
@RequestMapping("api/user/")
public class CartController {
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private HotelDao hotelDao;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@PostMapping("cart/add")
	public ResponseEntity add(@RequestBody AddToCartRequest addToCartRequest) {
		
		System.out.println("request came for ADD Hotel TO CART");
		System.out.println(addToCartRequest);
		Optional<User> optionalUser = userDao.findById(addToCartRequest.getUserId());
		User user = null;
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		
		Optional<Hotel> optionalHotel = hotelDao.findById(addToCartRequest.getHotelId());
		Hotel hotel = null;
		if(optionalHotel.isPresent()) {
			hotel = optionalHotel.get();
		}
		
		Cart cart = new Cart();
		cart.setHotel(hotel);
		cart.setQuantity(addToCartRequest.getQuantity());
		cart.setUser(user);
		
		cartDao.save(cart);
		
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	@GetMapping("mycart")
	public ResponseEntity getMyCart(@RequestParam("userId") int userId) throws JsonProcessingException {
		
		System.out.println("request came for MY CART for USER ID : "+userId);
		
		List<CartDataResponse> cartDatas = new ArrayList<>();
		
		List<Cart> userCarts = cartDao.findByUser_id(userId);
		
		double totalCartPrice = 0;
		
		for (Cart cart : userCarts) {
			CartDataResponse cartData = new CartDataResponse();
			cartData.setCartId(cart.getId());
			cartData.setHotelDescription(cart.getHotel().getDescription());
			cartData.setHotelName(cart.getHotel().getTitle());
			cartData.setHotelImage(cart.getHotel().getImageName());
			cartData.setQuantity(cart.getQuantity());
			cartData.setHotelId(cart.getHotel().getId());
			
			cartDatas.add(cartData);
			
			double productPrice = Double.parseDouble(cart.getHotel().getPrice().toString());
			
			totalCartPrice =  totalCartPrice + (cart.getQuantity() * productPrice);
			
		}
		
		CartResponse cartResponse = new CartResponse();
		cartResponse.setTotalCartPrice(String.valueOf(totalCartPrice));
		cartResponse.setCartData(cartDatas);
		
		String json = objectMapper.writeValueAsString(cartResponse);
		
		System.out.println(json);
		
		return new ResponseEntity(cartResponse, HttpStatus.OK);
		
	}
	
	@GetMapping("mycart/remove")
	public ResponseEntity removeCartItem(@RequestParam("cartId") int cartId) throws JsonProcessingException {
		
		System.out.println("request came for DELETE CART ITEM WHOSE ID IS : "+cartId);
		
		Optional<Cart> optionalCart = this.cartDao.findById(cartId);
		Cart cart = new Cart();
		
		if(optionalCart.isPresent()) {
			cart = optionalCart.get();
		}
		
		this.cartDao.delete(cart);
		
		return new ResponseEntity("SUCCESS", HttpStatus.OK);
		
	}

}
