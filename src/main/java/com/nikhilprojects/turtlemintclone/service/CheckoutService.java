package com.nikhilprojects.turtlemintclone.service;

import com.nikhilprojects.turtlemintclone.model.Checkout;
import com.nikhilprojects.turtlemintclone.repository.CheckoutRepository;
import com.nikhilprojects.turtlemintclone.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {
    @Autowired
    CheckoutRepository checkoutRepository;
    @Autowired
    ProfileRepository profileRepository;


    public Checkout createCheckout(long id,Checkout checkout)
    {
        checkout.setCheckoutId(id);
        checkout.setProfile(profileRepository.findById(id).get());
        return checkoutRepository.save(checkout);
    }
    public List<Checkout> getAll()
    {
        return checkoutRepository.findAll();
    }

    public Optional<Checkout> getById(long id)
    {
        return checkoutRepository.findById(id);
    }
    public void deleteCheckout(Long checkoutId)
    {
        checkoutRepository.deleteById(checkoutId);
    }
    public void updateById(@PathVariable Long id, @RequestBody Checkout checkoutDetails)
    {
        Checkout container=checkoutRepository.findById(id).get();
        container.setName(checkoutDetails.getName());
        container.setEmail(checkoutDetails.getEmail());
        container.setPhone(checkoutDetails.getPhone());
        container.setInsurer(checkoutDetails.getInsurer());
        checkoutRepository.save(container);
    }

}
