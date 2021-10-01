package com.example.sharity.entity.admin;

import com.example.sharity.entity.customer.Customer;

public abstract class Payment {


    //this function will be used to check if the credit is enough to rent a car
    public boolean checkCredit( ){
        /*
        TODO:   get bankaccount and read credit field
                get the rent amount with taxes
                compare
        */


        return true;
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
