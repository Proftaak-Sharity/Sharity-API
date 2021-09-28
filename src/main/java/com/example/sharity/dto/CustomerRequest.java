package com.example.sharity.dto;

import com.example.sharity.entity.customer.Customer;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRequest {

    private Customer customer;
}
