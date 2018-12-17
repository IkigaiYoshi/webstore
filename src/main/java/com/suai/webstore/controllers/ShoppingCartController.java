
package com.suai.webstore.controllers;

import com.suai.webstore.domain.Cart;
import com.suai.webstore.domain.CartItem;
import com.suai.webstore.domain.Order;
import com.suai.webstore.domain.Product;
import com.suai.webstore.repos.OrderRepo;
import com.suai.webstore.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ShoppingCartController {
    @Autowired
    private  ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/shoppingCart")
    public String shoppingCart(HttpSession session, Model model) {
        Cart cart = new Cart();
        if(session.getAttribute("cart") != null) {
            cart = (Cart)session.getAttribute("cart");
            model.addAttribute("cartItems", cart.getCartItems());
            model.addAttribute("totalPrice", cart.getTotalPrice());
        }else {
            session.setAttribute("cart", cart);
        }
        if(cart.getCartItems().isEmpty()){
            model.addAttribute("cartEmpty", "Cart empty");
        }
        return "shoppingCart";
    }

    @GetMapping("/shoppingCart/addProduct")
    public String addProductToCart(@RequestParam Long productId, HttpSession session, Model model) {
        Cart cart = new Cart();
        if(session.getAttribute("cart") != null){
             cart = (Cart) session.getAttribute("cart");
        }
        if(productRepo.findById(productId).isPresent()){
            CartItem cartItem = cart.getCartItem(productId);
            if(cartItem == null) {
                cart.addCartItem(new CartItem(productRepo.findById(productId).get(), 1));
            }else {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cart.resetCartItem(cartItem);
            }
        }
        session.setAttribute("cart", cart);
        model.addAttribute("cartItems", cart.getCartItems());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "shoppingCart";
    }

    @PostMapping("/shoppingCart/deleteProduct")
    public String delete(@RequestParam Long id, HttpSession session, Model model){
        if(session.getAttribute("cart") != null){
            Cart cart  = (Cart) session.getAttribute("cart");
            cart.deleteCartItem(cart.getCartItem(id));
            session.setAttribute("cart", cart);
        }
        return "redirect:/shoppingCart";
    }

    @PostMapping("/shoppingCart/editProduct")
    public String edit(@RequestParam Long id, @RequestParam int quantity, HttpSession session, Model model){
        if(session.getAttribute("cart") != null){
            Cart cart  = (Cart) session.getAttribute("cart");
            CartItem cartItem = cart.getCartItem(id);
            cartItem.setQuantity(quantity);
            cart.resetCartItem(cartItem);
            session.setAttribute("cart", cart);
            model.addAttribute("cartItems", cart.getCartItems());
            model.addAttribute("totalPrice", cart.getTotalPrice());
        }else {
            return "redirect:/shoppingCart";
        }
        return "shoppingCart";
    }

    @GetMapping("/shoppingCart/checkout")
    public String edit(HttpSession session, Model model){
        if(session.getAttribute("cart") != null){
            Cart cart  = (Cart) session.getAttribute("cart");
            Long productId = cart.checkQuatity();
            if(productId == null){
                for(CartItem cartItem : cart.getCartItems()){
                    Product product = productRepo.findById(cartItem.getProduct().getId()).get();
                    productRepo.deleteById(product.getId());
                    product.setQuantity(product.getQuantity() - cartItem.getQuantity());
                    productRepo.save(product);
                }
/*                session.removeAttribute("cart");*/
                model.addAttribute("totalPrice", cart.getTotalPrice());
            }
            else {
                model.addAttribute("errorName", productRepo.findById(productId).get().getName());
                model.addAttribute("errorQuantity", productRepo.findById(productId).get().getQuantity());
                model.addAttribute("cartItems", cart.getCartItems());
                model.addAttribute("totalPrice", cart.getTotalPrice());
                return "shoppingCart";
            }
        }else {
            return "redirect:/shoppingCart";
        }
        return "checkoutPage";
    }

    @PostMapping("/shoppingCart/checkout")
    public String edit(@RequestParam Long userId,
                       @RequestParam String username,
                       @RequestParam String address,
                       @RequestParam String mail,
                       HttpSession session,
                       Model model){
        if(session.getAttribute("cart") != null){
            Cart cart  = (Cart) session.getAttribute("cart");
            Order order = new Order(userId, username, address, mail, cart.toString());
            orderRepo.save(order);
            session.removeAttribute("cart");
        }else {
            return "redirect:/shoppingCart";
        }
        return "finalPage";
    }
}
