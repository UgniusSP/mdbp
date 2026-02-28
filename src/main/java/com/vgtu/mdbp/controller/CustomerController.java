package com.vgtu.mdbp.controller;

import com.vgtu.mdbp.model.Customer;
import com.vgtu.mdbp.model.Order;
import com.vgtu.mdbp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/email")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        Optional<Customer> customer = customerService.getCustomerByEmail(email);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/first-name")
    public ResponseEntity<List<Customer>> getCustomersByFirstName(@RequestParam String firstName) {
        List<Customer> customers = customerService.getCustomersByFirstName(firstName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search/last-name")
    public ResponseEntity<List<Customer>> getCustomersByLastName(@RequestParam String lastName) {
        List<Customer> customers = customerService.getCustomersByLastName(lastName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search/city")
    public ResponseEntity<List<Customer>> getCustomersByCity(@RequestParam String city) {
        List<Customer> customers = customerService.getCustomersByCity(city);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search/country")
    public ResponseEntity<List<Customer>> getCustomersByCountry(@RequestParam String country) {
        List<Customer> customers = customerService.getCustomersByCountry(country);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search/active")
    public ResponseEntity<List<Customer>> getActiveCustomers() {
        List<Customer> customers = customerService.getActiveCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search/city-country")
    public ResponseEntity<List<Customer>> getCustomersByCityAndCountry(
            @RequestParam String city,
            @RequestParam String country) {
        List<Customer> customers = customerService.getCustomersByCityAndCountry(city, country);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search/top-spenders")
    public ResponseEntity<List<Customer>> getTopCustomersBySpent() {
        List<Customer> customers = customerService.getTopCustomersBySpent();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        if (customerService.deleteCustomer(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCustomers() {
        customerService.deleteAllCustomers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable String id) {
        List<Order> orders = customerService.getCustomerOrders(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}/stats/total-spent")
    public ResponseEntity<Double> getCustomerTotalSpent(@PathVariable String id) {
        Double totalSpent = customerService.getCustomerTotalSpent(id);
        return new ResponseEntity<>(totalSpent, HttpStatus.OK);
    }

    @GetMapping("/{id}/stats/order-count")
    public ResponseEntity<Integer> getCustomerOrderCount(@PathVariable String id) {
        Integer count = customerService.getCustomerOrderCount(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/{id}/stats/average-order-value")
    public ResponseEntity<Double> getCustomerAverageOrderValue(@PathVariable String id) {
        Double avgValue = customerService.getCustomerAverageOrderValue(id);
        return new ResponseEntity<>(avgValue, HttpStatus.OK);
    }

    @PutMapping("/{id}/stats/update")
    public ResponseEntity<Customer> updateCustomerStats(@PathVariable String id) {
        Customer customer = customerService.updateCustomerStats(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

