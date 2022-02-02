package by.overone.clinic.dao;

import by.overone.clinic.dto.*;
import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.model.DoctorDetails;

import java.util.List;

public interface DetailsDAO {
    void addClientDetails(long id, ClientDetailsDTO clientDetailsDTO);

    void addDoctorDetails(long id, DoctorDetailsDTO doctorDetailsDTO);

    void updateClientDetails(long id, ClientDetailsDTO clientDetailsDTO);

    void updateDoctorDetails(long id, DoctorDetailsDTO doctorDetailsDTO);

    ClientDetails getClientDetails(long id);

    DoctorDetails getDoctorDetails(long id);

    List<DoctorDetails> getDoctorDetailsByType(String type);

    ClientAllDataDTO getAllClientData(long id);

    DoctorAllDataDTO getAllDoctorData(long id);

    List<ClientRecordDTO> getClientRecord(long id);
}
