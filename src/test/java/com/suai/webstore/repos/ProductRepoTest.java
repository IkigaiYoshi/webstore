package com.suai.webstore.repos;

import com.suai.webstore.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;

    @Test
    public void findAllTest(){
        Iterable<Product> products = productRepo.findAll();
        Assert.assertNotNull(products);
    }

    @Test
    public void findByCategoryTest(){
        Iterable<Product> products = productRepo.findByCategory("other");
        Assert.assertNotNull(products);
    }
}
