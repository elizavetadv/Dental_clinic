INSERT INTO `dental_clinic`.`user` (`user_id`, `login`, `password`, `email`, `role`, `status`) VALUES (1, 'tom', 'aa52887fa5c5cbfe8f410f1d09bc33f2', 'tom@gmail.com', 'DOCTOR', 'ACTIVE');
INSERT INTO `dental_clinic`.`user` (`user_id`, `login`, `password`, `email`, `role`, `status`) VALUES (2, 'sofya', 'a74e7c750751d8c75d8cbeef0f27ecdd', 'sofya@gmail.com', 'CLIENT', 'ACTIVE');
INSERT INTO `dental_clinic`.`user` (`user_id`, `login`, `password`, `email`, `role`, `status`) VALUES (3, 'john1', 'f83ecc0846c58a1050e5d77ff5a6094d', 'john@gmail.com', 'DOCTOR', 'ACTIVE');
INSERT INTO `dental_clinic`.`user` (`user_id`, `login`, `password`, `email`, `role`, `status`) VALUES (4, 'maria', 'e6d50305c7ad75705f8ff952004a1565', 'maria@gmail.com', 'USER', 'ACTIVE');
INSERT INTO `dental_clinic`.`user` (`user_id`, `login`, `password`, `email`, `role`, `status`) VALUES (5, 'helena', '58be72efec750c1b47b7f0571433fc82', 'helena@gmail.com', 'DOCTOR', 'ACTIVE');

INSERT INTO `dental_clinic`.`client_details` (`client_user_id`, `name`, `surname`, `address`, `data_birth`, `phone_number`) VALUES (2, 'Sofya', 'Karson', 'Brest', '03.12.1998', '+375-44-501-13-01');

INSERT INTO `dental_clinic`.`doctor_details` (`doctor_user_id`, `name`, `surname`, `doctor_type`) VALUES (1, 'Tom', 'Holland', 'surgeon');
INSERT INTO `dental_clinic`.`doctor_details` (`doctor_user_id`, `name`, `surname`, `doctor_type`) VALUES (3, 'John', 'Holmes', 'orthodontist');
INSERT INTO `dental_clinic`.`doctor_details` (`doctor_user_id`, `name`, `surname`, `doctor_type`) VALUES (6, 'Helena', 'Karter', 'orthopedist');

INSERT INTO `dental_clinic`.`record` (`id_record`, `doctor_type`, `date`, `time`, `status`, `user_user_id`, `doctor_id`) VALUES (1, 'orthodontist', '2022-03-21', '12:00:00', 'WAITING', 2, 0);
INSERT INTO `dental_clinic`.`record` (`id_record`, `doctor_type`, `date`, `time`, `status`, `user_user_id`, `doctor_id`) VALUES (2, 'orthopedist', '2022-01-15', '16:00:00', 'DONE', 2, 6);
INSERT INTO `dental_clinic`.`record` (`id_record`, `doctor_type`, `date`, `time`, `status`, `user_user_id`, `doctor_id`) VALUES (3, 'surgeon', '2022-03-08', '10:00:00', 'CONFIRMED', 2, 1);

INSERT INTO `dental_clinic`.`reception` (`id_reception`, `record_id_record`, `client_id`, `doctor_id`) VALUES (1, 1, '2', '6');
INSERT INTO `dental_clinic`.`reception` (`id_reception`, `record_id_record`, `client_id`, `doctor_id`) VALUES (2, 2, '2', '1');
INSERT INTO `dental_clinic`.`reception` (`id_reception`, `record_id_record`, `client_id`, `doctor_id`) VALUES (3, 3, '2', '0');

INSERT INTO `dental_clinic`.`doctor_timetable` (`doctor_timetable_id`, `client_surname`, `date`, `time`, `doctor_details_doctor_user_id`, `status`) VALUES (1, 'Karson', '2022-01-15', '16:00:00', 6, 'DONE');
INSERT INTO `dental_clinic`.`doctor_timetable` (`doctor_timetable_id`, `client_surname`, `date`, `time`, `doctor_details_doctor_user_id`, `status`) VALUES (2, 'Karson', '2022-03-08', '10:00:00', 1, 'CONFIRMED');

INSERT INTO `dental_clinic`.`client_record` (`client_record_id`, `doctor_surname`, `doctor_type`, `date`, `time`, `client_details_client_user_id`) VALUES (1, 'Karter', 'orthopedist', '2022-01-15', '16:00:00', 2);
INSERT INTO `dental_clinic`.`client_record` (`client_record_id`, `doctor_surname`, `doctor_type`, `date`, `time`, `client_details_client_user_id`) VALUES (2, 'Holland', 'surgeon', '2022-03-08', '10:00:00', 2);

INSERT INTO `dental_clinic`.`timetable` (`idtimetable`, `time`) VALUES (1, '8:00:00');
INSERT INTO `dental_clinic`.`timetable` (`idtimetable`, `time`) VALUES (2, '9:00:00');
INSERT INTO `dental_clinic`.`timetable` (`idtimetable`, `time`) VALUES (3, '10:00:00');
INSERT INTO `dental_clinic`.`timetable` (`idtimetable`, `time`) VALUES (4, '11:00:00');
INSERT INTO `dental_clinic`.`timetable` (`idtimetable`, `time`) VALUES (5, '12:00:00');
INSERT INTO `dental_clinic`.`timetable` (`idtimetable`, `time`) VALUES (6, '14:00:00');
INSERT INTO `dental_clinic`.`timetable` (`idtimetable`, `time`) VALUES (7, '15:00:00');
INSERT INTO `dental_clinic`.`timetable` (`idtimetable`, `time`) VALUES (8, '16:00:00');
INSERT INTO `dental_clinic`.`timetable` (`idtimetable`, `time`) VALUES (9, '17:00:00');
