package com.example.sharity.entity.car;

import com.example.sharity.entity.car.enums.FuelType;
import org.springframework.stereotype.Component;

@Component
public class TotalCostOwnership {

    public double TotalCostOwnershipFuel(Integer sizeFuelTank, Integer kmPerLiterFuel, FuelType fuelType) {

        double fuelPricePetrol = 2.059;
        double fuelPriceDiesel = 1.709;
        double fuelPriceLPG = 1.059;
        double range = sizeFuelTank * kmPerLiterFuel;

        if (fuelType.equals(FuelType.PETROL)) {
            return (sizeFuelTank * fuelPricePetrol) / range;
        } else if ((fuelType.equals(FuelType.DIESEL))) {
            return (sizeFuelTank * fuelPriceDiesel) / range;
        } else {
            return (sizeFuelTank * fuelPriceLPG) / range;
        }
    }

    public double TotalCostOwnerShipElectric(Integer batteryCapacity, Integer kmPerKw){
        double priceElectricity = 0.28;
        double range = batteryCapacity * kmPerKw;

        return (batteryCapacity * priceElectricity) / range;
    }

    public double TotalCostOwnerShipHydrogen(double sizeFuelTank, Integer kmPerKilo){
        double priceHydrogen = 10;
        double range = sizeFuelTank * kmPerKilo;

        return (sizeFuelTank * priceHydrogen) / range;
    }

}
