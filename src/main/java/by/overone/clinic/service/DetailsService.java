package by.overone.clinic.service;

import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.model.DoctorDetails;
import by.overone.clinic.util.validation.exception.ValidationException;

public interface DetailsService {
    void addClientDetails(long id, ClientDetails clientDetails) throws ValidationException;

    void addDoctorDetails(long id, DoctorDetails doctorDetails) throws ValidationException;

    void updateClientDetails(ClientDetails clientDetails) throws ValidationException;

    void updateDoctorDetails(DoctorDetails doctorDetails) throws ValidationException;

    ClientDetails getClientDetails(long id);

    DoctorDetails getDoctorDetails(long id);
}
