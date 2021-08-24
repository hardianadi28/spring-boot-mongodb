package id.hardian.practice.springbootmongodb.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import id.hardian.practice.springbootmongodb.data.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
