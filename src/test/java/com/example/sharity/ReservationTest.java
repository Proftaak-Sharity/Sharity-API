//package com.example.sharity;
//
//
//public class ReservationTest {
//
//    private CarService sut;
//
//    @BeforeEach
//    public void beforeEach() {
//        CarRepository repository = mock(CarRepository.class);
//
//        Car car = mock(Car.class);
//        when(Car.getPricePerDay()).thenReturn(100.00);
//
//        when(repository.getById(FuelCar.getLicensePlate("KNTK01"))).thenReturn(car);
//
//        sut = new CarService(repository);
//    }
//
//    check if we get the right amount of days
//    @Test
//    void addReservationGetDaysTest() {
//        // Arrange
//        Reservation reservation = new Reservation(3L,"KNTK01", 100, LocalDate.of(2021, Month.DECEMBER, 1),
//                LocalDate.of(2021, Month.DECEMBER, 5));
//        // Act
//        int days = Period.between(reservation.getStartDate(), reservation.getEndDate()).getDays();
//        // Assert
//        assertEquals(4, days);
//    }
//
//    //check whether the rent is calculated correctly
//    @Test
//    void addReservationGetRentTest() {
//        // Arrange
//        Car car = new FuelCar("KNTK01", Make.Volvo, "XC90", FuelType.DIESEL, 52, 12, 100.00, null);
//        Reservation reservation = new Reservation(3L,"KNTK01", 600, LocalDate.of(2021, Month.DECEMBER, 1),
//                LocalDate.of(2021, Month.DECEMBER, 5));
//        // Act
//        int days = Period.between(reservation.getStartDate(), reservation.getEndDate()).getDays();
//        double rent = car.getPricePerDay() * days;
//
//        // Assert
//        assertEquals(400.00, rent);
//    }
//
//}

