package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {
    @Autowired
    private ProductRepository rep;

    @GetMapping("/product/all")
    public Iterable<Product> getProductList() {
        return rep.findAll();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id) {
        return rep.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable int id) {
        Product p = rep.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        rep.delete(p);
        return p;
    }

    @PostMapping("/product")
    public void addProduct(@Valid @RequestBody Product p) {
        rep.findByProductcode(p.getProductCode()).ifPresent(s -> {
            throw new ProductAlreadyExistException(p.getProductCode());
        });
        rep.save(p);
    }
}
