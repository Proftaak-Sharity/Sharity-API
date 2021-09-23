package com.example.sharity.admin;

public class ContactForm {
    // This is a class to construct a contact form for customers to cotact admins

    String username;
    int customerNumber;
    String email;
    String Message;


    public ContactForm(String username, int customerNumber, String email, String Message) {
        this.username = username;
        this.customerNumber = customerNumber;
        this.email = email;
        this.Message = Message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    //    TEST

    @Override
    public String toString() {
        return "ContactForm{" +
                "username='" + username + '\'' +
                ", customerNumber=" + customerNumber +
                ", email='" + email + '\'' +
                ", Menssage='" + Message + '\'' +
                '}';
    }
}

