package com.example.sharity.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "bankaccount")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerNumber")
    private long customerNumber;

    @JsonIgnore
    @OneToOne
    @PrimaryKeyJoinColumn(name = "customerNumber")
    private Customer customer;

    @Column(name = "iban")
    private String iban;

    @Column(name = "accountHolder")
    private String accountHolder;




    //   CONSTRUCTORS
    public BankAccount(String iban, String accountHolder) {
        this.iban = iban;
        this.accountHolder = accountHolder;
    }

    public BankAccount() {
    }


    //   GETTERS & SETTERS
    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
}