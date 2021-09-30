INSERT INTO `bankaccount` (`iban`, `account_holder`, `customer_number`) VALUES
('NL12INGB012345678', 'RPL VAN DER HORST', 1),
('NL78RABO698745632', 'D JANSEN', 2),
('NL32INGB012346789', 'L HANEGRAAF', 5),
('NL23ABNA654797214', 'RPL VAN DER HORST', 1),
('NL21INGB012657899', 'B GROOTOONK', 3),
('NL54SNSB369478521', 'J JANSEN', 4),
('NL63RABO658746952', 'D JANSEN', 2),
('NL98ABNA987214669', 'RPL VAN DER HORST', 1);

INSERT INTO `car` (`license_plate`, `make`, `model`, `fuel_type`, `customer_number`) VALUES
('KNTK01', 'Volvo', 'XC90', 'DIESEL', 1),
('XVDR09', 'Mitsubishi', 'Carisma', 'LPG', 2),
('5NBV12', 'RolceRoyce', 'Space', 'DIESEL', 5),
('XX567R', 'LandRover', 'Defender', 'PETROL', 4),
('NR45HB', 'BMW', '318i', 'HYDROGEN', 2),
('RGBB54', 'MercedesBenz', 'ALG318 (AMG)', 'PETROL', 3),
('DRGH78', 'Ferrari', 'Testarossa', 'DIESEL', 5),
('RTP89T', 'Opel', 'Vectra', 'DIESEL', 1);

INSERT INTO `customer` (`customer_number`, `email`, `first_name`, `last_name`, `password`, `address`, `house_number`, `city`, `country`, `date_of_birth`) VALUES
(1, 'rob.vanderhorst@student.avans.nl', 'Rob', 'van der Horst', 'welkom01', 'Hoofdstraat', '12', 'Klundert', 'NETHERLANDS', '1983-08-29'),
(2, 'daniel.jansen@student.avans.nl', 'Daniël', 'Jansen', 'welkom02', 'Appelweg', '10', 'Made', 'LUXEMBOURG', '1978-03-20'),
(3, 'bart.grootoonk@student.avans.nl', 'Bart', 'Grootoonk', 'welkom03', 'Boompjesdijk', '48', 'Etten-Leur', 'NETHERLANDS', '1982-11-09'),
(4, 'joris.jansen@student.avans.nl', 'Joris', 'Jansen', 'welkom04', 'Marterweg', '45-B', 'Berkel-Enschot', 'NETHERLANDS', '1987-03-31'),
(5, 'lars.hanegraaf@student.avans.nl', 'Lars', 'Hanegraaf', 'welkom05', 'Vrijheid', '56', 'Breda', 'BELGIUM', '1985-06-04');

INSERT INTO `drivers_license` (`license_number`, `country`, `valid_until`, license_copy_url, `customer_number`) VALUES
('DFAP51056F', 'NETHERLANDS', '2031-04-22', '../img/driverslicense/GROOB821109.PNG', 3),
('LKSR78191N', 'NETHERLANDS', '2022-08-29', '../img/driverslicense/HORSR830829.PNG', 1),
('POGN65897N', 'NETHERLANDS', '2024-05-19', '../img/driverslicense/JANSJ870331.PNG', 4),
('GDBL63820B', 'LUXEMBOURG', '2023-06-09', '../img/driverslicense/JANSD780320.PNG', 2),
('DTYM94623F', 'BELGIUM', '2022-04-12', '../img/driverslicense/HANEL850604.PNG', 5);

INSERT INTO `contact_form` (`form_number`, `message`, `time_sent`, `customer_number`) VALUES
(1, 'test', '2021-08-29', '1'),
(2, 'test 2', '2021-09-30', '2'),
(3, 'groetjes van customer 2', '2021-09-30', '2');
