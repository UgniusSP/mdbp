package com.vgtu.mdbp.service;

import com.vgtu.mdbp.model.Customer;
import com.vgtu.mdbp.model.Order;
import com.vgtu.mdbp.repository.CustomerRepository;
import com.vgtu.mdbp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public Customer createCustomer(Customer customer) {
        customer.setRegistrationDate(LocalDateTime.now());
        customer.setTotalSpent(0.0);
        customer.setTotalOrders(0);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public List<Customer> getCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    public List<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    public List<Customer> getCustomersByCity(String city) {
        return customerRepository.findByCity(city);
    }

    public List<Customer> getCustomersByCountry(String country) {
        return customerRepository.findByCountry(country);
    }

    public List<Customer> getActiveCustomers() {
        return customerRepository.findByIsActive(true);
    }

    public List<Customer> getCustomersByCityAndCountry(String city, String country) {
        return customerRepository.findByCityAndCountry(city, country);
    }

    public List<Customer> getTopCustomersBySpent() {
        return customerRepository.findByIsActiveOrderByTotalSpentDesc(true);
    }

    public Customer updateCustomer(String id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            return customerRepository.save(customer);
        }
        return null;
    }

    public boolean deleteCustomer(String id) {
        if (customerRepository.existsById(id)) {
            orderRepository.deleteAll(orderRepository.findByCustomerId(id));
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    public List<Order> getCustomerOrders(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Double getCustomerTotalSpent(String customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    public Integer getCustomerOrderCount(String customerId) {
        return orderRepository.findByCustomerId(customerId).size();
    }

    public Double getCustomerAverageOrderValue(String customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            return 0.0;
        }
        return orders.stream()
                .mapToDouble(Order::getTotalPrice)
                .average()
                .orElse(0.0);
    }

    public Customer updateCustomerStats(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            Customer c = customer.get();
            List<Order> orders = orderRepository.findByCustomerId(customerId);
            c.setTotalSpent(orders.stream().mapToDouble(Order::getTotalPrice).sum());
            c.setTotalOrders(orders.size());
            return customerRepository.save(c);
        }
        return null;
    }
}


