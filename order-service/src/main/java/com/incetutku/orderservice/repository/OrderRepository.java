package com.incetutku.orderservice.repository;

import com.incetutku.orderservice.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer> {
}
