package by.overone.clinic.service;

import by.overone.clinic.dto.*;

import java.util.List;

/**
 * @see by.overone.clinic.service.impl.DetailsServiceImpl with realization of all methods
 */
public interface DetailsService {
    void addClientDetails(long id, ClientDetailsDTO clientDetailsDTO);

    void addDoctorDetails(long id, DoctorDetailsDTO doctorDetailsDTO);

    void updateClientDetails(long id, ClientDetailsDTO clientDetailsDTO);

    void updateDoctorDetails(long id, DoctorDetailsDTO doctorDetailsDTO);

    ClientDetailsDTO getClientDetails(long id);

    DoctorDetailsDTO getDoctorDetails(long id);

    List<DoctorDetailsDTO> getDoctorDetailsByType(String type);

    ClientAllDataDTO getAllClientData(long id);

    DoctorAllDataDTO getAllDoctorData(long id);

    List<ClientRecordDTO> getClientRecord(long id);
}
