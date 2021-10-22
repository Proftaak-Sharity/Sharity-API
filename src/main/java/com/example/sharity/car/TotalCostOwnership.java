package com.example.sharity.car;

import com.example.sharity.car.enums.FuelType;
import com.example.sharity.service.NumberRounder;
import org.springframework.stereotype.Component;

@Component
public class TotalCostOwnership {

    public double TotalCostOwnershipFuel(Integer sizeFuelTank, Integer kmPerLiterFuel, FuelType fuelType) {
        double fuelPricePetrol = 2.059;
        double fuelPriceDiesel = 1.709;
        double fuelPriceLPG = 1.059;
        double range = sizeFuelTank * kmPerLiterFuel;
        double pricePerKm = (sizeFuelTank * fuelPricePetrol) / range;

        if (fuelType.equals(FuelType.PETROL)) {
            double environmentalSurcharge = 1.15; // 15%
            return NumberRounder.roundDouble((pricePerKm * environmentalSurcharge), 2);
        } else if ((fuelType.equals(FuelType.DIESEL))) {
            double environmentalSurcharge = 1.20; // 20%
            return NumberRounder.roundDouble((pricePerKm * environmentalSurcharge), 2);
        } else {
            double environmentalSurcharge = 1.10; // 10%
            return NumberRounder.roundDouble((pricePerKm * environmentalSurcharge), 2);
        }
    }

    public double TotalCostOwnerShipElectric(Integer batteryCapacity, Integer kmPerKw){
        double priceElectricity = 0.28;
        double range = batteryCapacity * kmPerKw;
        double pricePerKm = (batteryCapacity * priceElectricity) / range;
        double environmentalSurcharge = 0.95; // 5% discount

        return NumberRounder.roundDouble((pricePerKm * environmentalSurcharge), 2);
    }

    public double TotalCostOwnerShipHydrogen(double sizeFueltank, Integer kmPerKilo){
        double priceHydrogen = 10;
        double range = sizeFueltank * kmPerKilo;
        double pricePerKm = (sizeFueltank * priceHydrogen) / range;
        double environmentalSurcharge = 0.95; // 5% discount

        return NumberRounder.roundDouble((pricePerKm * environmentalSurcharge), 2);
    }
}
