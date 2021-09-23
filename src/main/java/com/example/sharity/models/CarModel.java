package com.example.sharity.models;

import com.example.sharity.Enum.MakeEnum;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class CarModel extends BaseModel {

    @Column(unique = true)
    @NotEmpty
    private String licensePlate;

    @Column
    @NotEmpty
    private MakeEnum make;

    @Column
    @NotEmpty
    private String model;


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public MakeEnum getMake() {
        return make;
    }

    public void setMake(MakeEnum make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }



}
