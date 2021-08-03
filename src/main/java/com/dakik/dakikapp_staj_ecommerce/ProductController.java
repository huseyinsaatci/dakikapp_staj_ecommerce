package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RequestMapping("/products")
@CrossOrigin(origins = "*")
@RestController
public class ProductController {
    @Autowired
    private ProductRepository rep;

    @Autowired
    private ProductService productService;

    @GetMapping()
    public Iterable<Product> getProductList() {
        return rep.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return rep.findById(id).orElseThrow(() -> productService.throwProductNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id, WebRequest request) {
        Product p = rep.findById(id).orElseThrow(() -> productService.throwProductNotFoundException(id));
        rep.delete(p);
        return GeneralExceptionHandler.getSuccessfulResponseEntity(request);
    }

    @PostMapping()
    public ResponseEntity<Object> addProduct(@Valid @RequestBody Product p, WebRequest request) {
        rep.save(p);
        return GeneralExceptionHandler.getSuccessfulResponseEntity(request);
    }
}