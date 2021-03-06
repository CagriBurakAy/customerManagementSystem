package com.nish.project.service;

import com.nish.project.model.customer.Customer;
import com.nish.project.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> listAll() {
        return customerRepository.findAll();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer get(Integer id) {
        return customerRepository.findById(id).get();
    }

    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    public void update(Customer customer, Integer id) {

        customerRepository.save(customer);
    }
}