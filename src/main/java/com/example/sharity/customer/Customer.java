package com.example.sharity.customer;



import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table
@SecondaryTable(name = "BankAccount", pkJoinColumns = @PrimaryKeyJoinColumn(name = "customerNumber"))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long customerNumber;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private BankAccount bankAccount;











    public Customer(Long customerNumber, String lastName, String firstName, LocalDate dateOfBirth, String email, BankAccount bankAccount) {
        this.customerNumber = customerNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.bankAccount = bankAccount;
    }

    public Customer(String lastName, String firstName, LocalDate dateOfBirth, String email, BankAccount bankAccount) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.bankAccount = bankAccount;
    }

    public Customer() {
    }


    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerNumber=" + customerNumber +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", bankAccount=" + bankAccount +
                '}';
    }
}
