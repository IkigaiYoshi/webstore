package com.suai.webstore.controllers;

import com.suai.webstore.domain.Product;
import com.suai.webstore.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private ProductRepo productRepo;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/")
    public String getMainPage(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Product> products;

        if (filter != null && !filter.isEmpty()) {
            products = productRepo.findByCategory(filter);
        } else {
            products = productRepo.findAll();
        }

        model.addAttribute("products", products);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addProduct")
    public String add (@RequestParam String category,
                       @RequestParam String name,
                       @RequestParam("file")MultipartFile file,
                       @RequestParam BigDecimal price,
                       @RequestParam int quantity,
                       Model model) throws IOException {
            Product product = createProduct(category, name, file, price, quantity);
            productRepo.save(product);
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
        return "main";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/deleteProduct")
    public String delete(@RequestParam Long id){
        productRepo.deleteById(id);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editProduct")
    public String editPage(@RequestParam Long id, Model model){
        model.addAttribute("product", productRepo.findById(id).get());
        return "adminEditProduct";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/editProduct")
    public String add (@RequestParam Long id,
                       @RequestParam String category,
                       @RequestParam String name,
                       @RequestParam("file")MultipartFile file,
                       @RequestParam BigDecimal price,
                       @RequestParam int quantity) throws IOException {
        Product product = createProduct(category,name, file, price, quantity);
        if(product.getFilename() == null){
            product.setFilename(productRepo.findById(id).get().getFilename());
        }
        productRepo.deleteById(id);
        productRepo.save(product);
        return "redirect:/";
    }

    private Product createProduct(String category, String name, MultipartFile file, BigDecimal price, int quantity) throws IOException {
        Product product = new Product(category, name, price, quantity);
        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File (uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath  + "/" + resultFilename));
            product.setFilename(resultFilename);
        }
        return product;
    }

}
