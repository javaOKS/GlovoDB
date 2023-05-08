package com.example.GlovoDB.repository;

import com.example.GlovoDB.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity,Integer> {

    public List<ProductEntity> findAll();
}
