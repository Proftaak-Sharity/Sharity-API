package com.example.sharity.entity.admin;

import com.example.sharity.entity.car.Car;
import com.example.sharity.entity.customer.Bankaccount;
import com.example.sharity.entity.customer.Customer;
import com.example.sharity.repository.BankaccountRepository;

import java.util.Collections;


public class Payment {


    //this function will be used to check if the credit is enough to rent a car
    public double checkCredit(Customer customer, long days, Car car ){
        /*
        TODO:   get bankaccount and read credit field
                get the rent amount with taxes
                compare
        */

//        BankaccountRepository bankaccountRepository;
//
//
//        Bankaccount bankaccount = bankaccount.getCredit(customer);
//        double credits = bankaccount.getCredit();
//        double rent = car.getRent();
//        double amountToPay = rent * days;
//
//        if (credits >= amountToPay){
//
//            return rent;
//        }
//        else {
//            return 0;
//        }
        return 30;
    }

    // this function will be used to buy more credits
    public void buyCredit(){
        /*
        TODO:   get bankaccount and read credit field
                ask the customer how much credits they want to buy
                add credits to credit field
        */


    }

    // this function will be used to transfer the credits when renting from customer to owner
    public void transferCredit(double amount){
        /*
        TODO:   get bankaccount and read credit field
                get the rent amount with taxes
                remove rent including taxes from customer credit field
                add rent without taxes to owners credit field
        */


    }

}
