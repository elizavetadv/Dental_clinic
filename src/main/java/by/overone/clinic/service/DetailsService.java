package by.overone.clinic.service;

import by.overone.clinic.dto.ClientAllDataDTO;
import by.overone.clinic.dto.DoctorAllDataDTO;
import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.model.DoctorDetails;

import java.util.List;

public interface DetailsService {
    void addClientDetails(long id, ClientDetails clientDetails);

    void addDoctorDetails(long id, DoctorDetails doctorDetails);

    void updateClientDetails(ClientDetails clientDetails);

    void updateDoctorDetails(DoctorDetails doctorDetails);

    ClientDetails getClientDetails(long id);

    DoctorDetails getDoctorDetails(long id);

    List<DoctorDetails> getDoctorDetailsByType(String type);

    ClientAllDataDTO getAllClientData(long id);

    DoctorAllDataDTO getAllDoctorData(long id);
}
