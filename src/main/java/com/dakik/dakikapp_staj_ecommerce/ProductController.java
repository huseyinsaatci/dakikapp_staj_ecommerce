package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private ProductRepository rep;

    @GetMapping(path = "all")
    public Iterable<Product> getProductList() {
        return rep.findAll();
    }

    @GetMapping(path = "/get")
    public Product getProduct(@RequestParam int id) {
        return rep.findById(id).get();
    }

    @DeleteMapping(path = "/delete")
    public Product deleteProduct(@RequestParam int id) {
        Product p = rep.findById(id).get();
        rep.delete(p);
        return p;
    }

    @PostMapping(path = "/add")
    public Product addProduct(@RequestParam int product_code, @RequestParam String product_name,
            @RequestParam String image_url) {
        Product p = new Product(product_code, product_name, image_url);
        rep.save(p);
        return p;
    }

    // @GetMapping(path = "/login")
    // public boolean logIn(@RequestParam String email, @RequestParam String
    // password) {
    // User u = rep.findByEmail(email);
    // return u != null && password.equals(u.getPassword());
    // }
}
