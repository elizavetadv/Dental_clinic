package by.overone.clinic.dao;

import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.model.DoctorDetails;

public interface DetailsDAO {
    void addClientDetails(long id, ClientDetails userDetails);

    void addDoctorDetails(long id, DoctorDetails doctorDetails);

    void updateClientDetails(ClientDetails clientDetails);

    void updateDoctorDetails(DoctorDetails doctorDetails);

    ClientDetails getClientDetails(long id);

    DoctorDetails getDoctorDetails(long id);
}
