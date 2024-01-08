package com.example.productservice.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", length = 20, nullable = false)
    private int productId;

    @Column(name = "product_name", length = 50, nullable = false)
    private String productName;

    @Column(name = "supplier_id", length = 50, nullable = false)
    private int supplierId;

    @Column(name = "product_description", length = 150)
    private String productDescription;

    @Column(name = "category", length = 50, nullable = false)
    private String category;

    @Column(name = "price", length = 50, nullable = false)
    private String price;

    @Column(name = "stock_quantity", length = 20, nullable = false)
    private String stockQuantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
