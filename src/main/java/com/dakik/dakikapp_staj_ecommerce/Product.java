package com.dakik.dakikapp_staj_ecommerce;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product", schema = "dakikapp")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @NotNull(message = "'productcode' cannot be null")
    private int productcode;
    @NotEmpty(message = "'productname' cannot be empty")
    private String productname;
    private String imageurl;

    public Product() {
    }

    public Product(int productcode, String productname, String imageurl) {
        this.productcode = productcode;
        this.productname = productname;
        this.imageurl = imageurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductCode() {
        return productcode;
    }

    public void setProductCode(int productCode) {
        this.productcode = productCode;
    }

    public String getProductName() {
        return productname;
    }

    public void setProductName(String productName) {
        this.productname = productName;
    }

    public String getImageUrl() {
        return imageurl;
    }

    public void setImageUrl(String imageurl) {
        this.imageurl = imageurl;
    }

}
