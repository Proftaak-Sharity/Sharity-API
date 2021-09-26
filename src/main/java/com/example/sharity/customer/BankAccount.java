package com.example.sharity.customer;

import com.example.sharity.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Entity
@Table(name = "bankaccount")
public class BankAccount extends BaseModel {


    @JsonIgnore
    @OneToMany(mappedBy = "bankaccount", cascade = CascadeType.ALL)
    public Set<Customer> customer;

    @Column(name = "iban", unique = true)
    @NotEmpty
    private String iban;

    @Column(name = "accountHolder")
    @NotEmpty
    private String accountHolder;




    //   CONSTRUCTORS
    public BankAccount(String iban, String accountHolder) {
        this.iban = iban;
        this.accountHolder = accountHolder;
    }

    public BankAccount() {
    }


    //   GETTERS & SETTERS

    public Set<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(Set<Customer> customer) {
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

    @Override
    public String toString() {
        return "BankAccount{" +
                "customer=" + customer +
                ", iban='" + iban + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                '}';
    }
}