package com.example.sharity.service;



import com.example.sharity.car.Car;
import com.example.sharity.car.TotalCostOwnership;
import com.example.sharity.car.carTypes.FuelCar;
import com.example.sharity.car.enums.FuelType;
import com.example.sharity.car.enums.Make;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.sharity.repository.CarRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class CarServiceTest
{
    @InjectMocks
    private CarService sut;

    @Mock
    private TotalCostOwnership totalCostOwnership;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateCarTestNewPricePerDay() {
        // Arrange
        Car car = mock(Car.class);
        when(car.getLicensePlate()).thenReturn("KTKN01");
        Make make = Make.MercedesBenz;
        long customerNumber = 12;
        FuelCar fuelCar = sut.addFuelCar("KTKN01", customerNumber, make, "model", 100.00, FuelType.PETROL, 50, 20);
        when(carRepository.findById(anyString())).thenReturn(Optional.ofNullable(fuelCar));

        double updatedPriceperDay = 400.0;

        // Act
        sut.updateCar(car.getLicensePlate(), updatedPriceperDay);

        // Assert Equals
        assert fuelCar != null;
        assertEquals(updatedPriceperDay, fuelCar.getPricePerDay());
    }
}

