package by.overone.clinic.service.impl;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.DetailsDAO;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.exception.DAOIncorrectDataException;
import by.overone.clinic.dao.exception.DAONotExistException;
import by.overone.clinic.dto.*;
import by.overone.clinic.model.DoctorType;
import by.overone.clinic.model.Role;
import by.overone.clinic.service.DetailsService;
import by.overone.clinic.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    DetailsDAO detailsDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addClientDetails(long id, ClientDetailsDTO clientDetailsDTO){
        userService.getUserById(id);
        detailsDAO.addClientDetails(id, clientDetailsDTO);
    }

    @Override
    public void addDoctorDetails(long id, DoctorDetailsDTO doctorDetailsDTO){
        userService.getUserById(id);
        detailsDAO.addDoctorDetails(id, doctorDetailsDTO);
    }

    @Override
    public void updateClientDetails(long id, ClientDetailsDTO clientDetailsDTO){
        getClientDetails(id);
        detailsDAO.updateClientDetails(id, clientDetailsDTO);
    }

    @Override
    public void updateDoctorDetails(long id, DoctorDetailsDTO doctorDetailsDTO){
        getDoctorDetails(id);
        detailsDAO.updateDoctorDetails(id, doctorDetailsDTO);
    }

    @Override
    public ClientDetailsDTO getClientDetails(long id) {
        userService.getUserById(id);
        if(!userDAO.getUserById(id).get().getRole().equals(Role.CLIENT.toString())){
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_CLIENT_DETAILS.getErrorCode());
        }
        return modelMapper.map(detailsDAO.getClientDetails(id), ClientDetailsDTO.class);
    }

    @Override
    public DoctorDetailsDTO getDoctorDetails(long id) {
        userService.getUserById(id);
        if(!userDAO.getUserById(id).get().getRole().equals(Role.DOCTOR.toString())){
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_DOCTOR_DETAILS.getErrorCode());
        }
        return modelMapper.map(detailsDAO.getDoctorDetails(id), DoctorDetailsDTO.class);
    }

    @Override
    public List<DoctorDetailsDTO> getDoctorDetailsByType(String type) {
        if(!type.equals(DoctorType.SURGEON.toString().toLowerCase())
                && !type.equals(DoctorType.ORTHOPEDIST.toString().toLowerCase())
                && !type.equals(DoctorType.ORTHODONTIST.toString().toLowerCase())){
            throw new DAOIncorrectDataException(ExceptionCode.INCORRECT_QUERY_DATA.getErrorCode());
        }
        return detailsDAO.getDoctorDetailsByType(type)
                .stream()
                .map(doctor -> new DoctorDetailsDTO(doctor.getName(), doctor.getSurname(), doctor.getDoctorType()))
                .collect(Collectors.toList());
    }

    @Override
    public ClientAllDataDTO getAllClientData(long id) {
        userService.getUserById(id);
        if(!userDAO.getUserById(id).get().getRole().equals(Role.CLIENT.toString())){
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_CLIENT_DETAILS.getErrorCode());
        }
        return detailsDAO.getAllClientData(id);
    }

    @Override
    public DoctorAllDataDTO getAllDoctorData(long id) {
        userService.getUserById(id);
        if(!userDAO.getUserById(id).get().getRole().equals(Role.DOCTOR.toString())){
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_DOCTOR_DETAILS.getErrorCode());
        }
        return detailsDAO.getAllDoctorData(id);
    }

    @Override
    public List<ClientRecordDTO> getClientRecord(long id) {
        getClientDetails(id);
        return detailsDAO.getClientRecord(id);
    }
}
