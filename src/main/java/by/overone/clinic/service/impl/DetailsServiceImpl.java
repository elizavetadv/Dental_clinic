package by.overone.clinic.service.impl;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.DetailsDAO;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.exception.DAODetailsNotFoundException;
import by.overone.clinic.dto.ClientAllDataDTO;
import by.overone.clinic.dto.DoctorAllDataDTO;
import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.model.DoctorDetails;
import by.overone.clinic.model.Role;
import by.overone.clinic.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    DetailsDAO detailsDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public void addClientDetails(long id, ClientDetails clientDetails){
        detailsDAO.addClientDetails(id, clientDetails);
    }

    @Override
    public void addDoctorDetails(long id, DoctorDetails doctorDetails){
        detailsDAO.addDoctorDetails(id, doctorDetails);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails){
        detailsDAO.updateClientDetails(clientDetails);
    }

    @Override
    public void updateDoctorDetails(DoctorDetails doctorDetails){
        detailsDAO.updateDoctorDetails(doctorDetails);
    }

    @Override
    public ClientDetails getClientDetails(long id) {
        userDAO.getUserById(id);
        if(!userDAO.getUser(id).getRole().equals(Role.CLIENT.toString())){
            throw new DAODetailsNotFoundException(ExceptionCode.NOT_EXISTING_CLIENT_DETAILS.getErrorCode());
        }
        return detailsDAO.getClientDetails(id);
    }

    @Override
    public DoctorDetails getDoctorDetails(long id) {
        userDAO.getUserById(id);
        if(!userDAO.getUser(id).getRole().equals(Role.DOCTOR.toString())){
            throw new DAODetailsNotFoundException(ExceptionCode.NOT_EXISTING_DOCTOR_DETAILS.getErrorCode());
        }
        return detailsDAO.getDoctorDetails(id);
    }

    @Override
    public List<DoctorDetails> getDoctorDetailsByType(String type) {
        return detailsDAO.getDoctorDetailsByType(type);
    }

    @Override
    public ClientAllDataDTO getAllClientData(long id) {
        if(!userDAO.getUser(id).getRole().equals(Role.CLIENT.toString())){
            throw new DAODetailsNotFoundException(ExceptionCode.NOT_EXISTING_CLIENT_DETAILS.getErrorCode());
        }
        return detailsDAO.getAllClientData(id);
    }

    @Override
    public DoctorAllDataDTO getAllDoctorData(long id) {
        if(!userDAO.getUser(id).getRole().equals(Role.DOCTOR.toString())){
            throw new DAODetailsNotFoundException(ExceptionCode.NOT_EXISTING_DOCTOR_DETAILS.getErrorCode());
        }
        return detailsDAO.getAllDoctorData(id);
    }
}
