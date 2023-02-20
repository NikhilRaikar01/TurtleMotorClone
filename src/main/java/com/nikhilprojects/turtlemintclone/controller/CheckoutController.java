package com.nikhilprojects.turtlemintclone.controller;

import com.nikhilprojects.turtlemintclone.model.Checkout;
import com.nikhilprojects.turtlemintclone.repository.CheckoutRepository;

import com.nikhilprojects.turtlemintclone.service.CheckoutService;

import com.nikhilprojects.turtlemintclone.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turtlemint")
public class CheckoutController {
    @Autowired
    CheckoutService checkoutService;
    @Autowired
    CheckoutRepository checkoutRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @PostMapping("/checkout/{id}")
    public String CreateCheckout(@PathVariable long id,@RequestBody Checkout checkout)
    {
        checkoutService.createCheckout(id,checkout);
        return "checkout compelete with id: "+ id;
    }
    @GetMapping("/checkout/{id}")
    public Optional<Checkout> GetById(@PathVariable long id)
    {
        return checkoutService.getById(id);
    }
    @GetMapping("/checkout")
    public List<Checkout> GetAllCheckout()
    {
        return checkoutService.getAll();
    }

    @DeleteMapping("/checkout/{id}")
    public String DeleteCheckout(@PathVariable long id)
    {
        checkoutService.deleteCheckout(id);
        return "Deleted Checkout No: "+id;
    }
    @PutMapping("/checkout/{id}")
    public String UpdateCheckout(@PathVariable long id, @RequestBody Checkout checkout)
    {
        checkoutService.updateById(id,checkout);
        return "Updated checkout req with id :"+id;
    }


}
