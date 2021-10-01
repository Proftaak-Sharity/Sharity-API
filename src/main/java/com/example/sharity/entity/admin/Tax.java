package com.example.sharity.entity.admin;

import lombok.Getter;

@Getter
public abstract class Tax {

    // this will be the abstract class for taxes
    // we wont be spawning instances of this class so we can make it abstract

    private double tax = 21;


    public void transferMoney(double amount){
        //here we will get the amount the customer asks and add the taxed we get by running calculateTax

    }

    public double getRealPrice(double amount) {
        //here we will create the amount the customer will pay
        //this is because the owner can ask for an amount but we need to add taxes

        //testing amounts
        amount = 10;

        // if amount or tax = lower or equal to 0 break
        if ((amount <= 0) | (this.tax <= 0)){
            throw new IllegalArgumentException("amount can not be 0 or lower");
        } else {
            double price = amount + (amount * (tax/100));
            return price;
        }
    }

    public double getCalculatedTax(double amount) {
        // here we will calculate the taxes
        //here we will create the amount the customer will pay
        //this is because the owner can ask for an amount but we need to add taxes

        //testing amounts
        amount = 10;

        // if amount or tax = lower or equal to 0 break
        if ((amount <= 0) | (this.tax <= 0)){
            throw new IllegalArgumentException("amount can not be 0 or lower");
        } else {
            double price = amount * (tax/100);
            return price;
        }
    }
}
