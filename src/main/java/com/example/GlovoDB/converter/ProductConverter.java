package com.example.GlovoDB.converter;

import com.example.GlovoDB.entity.OrderEntity;
import com.example.GlovoDB.entity.ProductEntity;
import com.example.GlovoDB.model.Order;
import com.example.GlovoDB.model.Product;

public class ProductConverter {
    public static Product productEntityToProduct(ProductEntity productEntity){
        return Product.builder()
                .productId(productEntity.getProductId())
                .name(productEntity.getName())
                .cost(productEntity.getCost())
                .build();
    }
    public static ProductEntity productToProductEntity(Product product){
        return ProductEntity.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .cost(product.getCost())
                .build();
    }
}
