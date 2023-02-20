package com.nikhilprojects.turtlemintclone.repository;

import com.nikhilprojects.turtlemintclone.model.Checkout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends MongoRepository<Checkout,Long> {
}
