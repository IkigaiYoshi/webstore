package com.suai.webstore.domain;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems = new LinkedList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
    }

    public CartItem getCartItem(Long productId){
        for(CartItem cartItem : cartItems){
            if(cartItem.getProduct().getId().equals(productId)){
                return cartItem;
            }
        }
        return null;
    }

    public boolean resetCartItem(CartItem cartItem){
        for(CartItem item : cartItems){
            if(item.getProduct().getId().equals(cartItem.getProduct().getId())){
                item = cartItem;
            }
        }
        return false;
    }

    public boolean deleteCartItem(CartItem cartItem){
        return cartItems.remove(cartItem);
    }

    public Long checkQuatity(){
        for(CartItem item : cartItems){
            if(item.getQuantity() > item.getProduct().getQuantity()) {
                return item.getProduct().getId();
            }
        }
        return null;
    }

    public int getTotalPrice(){
        int result = 0;
        for (CartItem cartItem : cartItems){
            result += cartItem.getQuantity() * cartItem.getProduct().getPrice().intValue();
        }
        return result;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(CartItem cartItem : cartItems){
            stringBuilder
                    .append("Category: ")
                    .append(cartItem.getProduct().getCategory())
                    .append(". ")
                    .append("Name: ")
                    .append(cartItem.getProduct().getName())
                    .append(". ")
                    .append("Quantity: ")
                    .append(cartItem.getQuantity())
                    .append(".\n");
        }
        return stringBuilder.toString();
    }
}
