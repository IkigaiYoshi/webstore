package com.suai.webstore.controllers;

import com.suai.webstore.domain.Order;
import com.suai.webstore.domain.Role;
import com.suai.webstore.domain.User;
import com.suai.webstore.repos.OrderRepo;
import com.suai.webstore.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/userList")
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "adminUserList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "adminUserEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/admin";
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "adminAddProduct";
    }

    @GetMapping("/ordersList")
    public String ordersList(Model model){
        Iterable<Order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);
        return "ordersList";
    }
}
