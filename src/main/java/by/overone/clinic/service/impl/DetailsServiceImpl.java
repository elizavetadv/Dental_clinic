package by.overone.clinic.service.impl;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.DetailsDAO;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.exception.DAODetailsNotFoundException;
import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.model.DoctorDetails;
import by.overone.clinic.model.Role;
import by.overone.clinic.service.DetailsService;
import by.overone.clinic.util.validation.ClientDetailsValidator;
import by.overone.clinic.util.validation.DoctorDetailsValidation;
import by.overone.clinic.util.validation.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class DetailsServiceImpl implements DetailsService {

    @Autowired
    DetailsDAO detailsDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public void addClientDetails(long id, ClientDetails clientDetails) throws ValidationException {
        ClientDetailsValidator.validateClientDetails(clientDetails);
        detailsDAO.addClientDetails(id, clientDetails);
    }

    @Override
    public void addDoctorDetails(long id, DoctorDetails doctorDetails) throws ValidationException {
        DoctorDetailsValidation.validateDoctorDetails(doctorDetails);
        detailsDAO.addDoctorDetails(id, doctorDetails);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws ValidationException {
        ClientDetailsValidator.validateClientDetails(clientDetails);
        detailsDAO.updateClientDetails(clientDetails);
    }

    @Override
    public void updateDoctorDetails(DoctorDetails doctorDetails) throws ValidationException {
        DoctorDetailsValidation.validateDoctorDetails(doctorDetails);
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
}
