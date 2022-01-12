package com.example.sharity.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Setter
@ToString
@Entity
public class CustomerImage implements Serializable {

    @Id
    @Column(unique = true, name = "customer_number")
    private Long customerNumber;

    @Column(columnDefinition = "LONGBLOB")
    private String image;

    public CustomerImage(Long customerNumber, String image) {
        this.customerNumber = customerNumber;
        this.image = image;
    }

    public CustomerImage() {
    }
}


