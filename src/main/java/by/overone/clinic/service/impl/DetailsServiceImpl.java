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

    /**
     * This method calls the method from dao to add client data to database.
     * If user with another not USER role tries to add details, DAONotExistException will be thrown
     *
     * @param id client id
     * @param clientDetailsDTO personal client data to add to bd
     */
    @Override
    public void addClientDetails(long id, ClientDetailsDTO clientDetailsDTO){
        userService.getUserById(id);
        if(userDAO.getUserById(id).get().getRole().equals(Role.USER.toString())) {
            detailsDAO.addClientDetails(id, clientDetailsDTO);
        } else{
            throw new DAONotExistException(ExceptionCode.IMPOSSIBLE_ACTION.getErrorCode());
        }
    }

    /**
     * This method calls the method from dao to add doctor data to database.
     * If user with another not USER role tries to add details, DAONotExistException will be thrown
     *
     * @param id doctor id
     * @param doctorDetailsDTO data about doctor
     */
    @Override
    public void addDoctorDetails(long id, DoctorDetailsDTO doctorDetailsDTO){
        userService.getUserById(id);
        if(userDAO.getUserById(id).get().getRole().equals(Role.USER.toString())){
            detailsDAO.addDoctorDetails(id, doctorDetailsDTO);
        } else{
            throw new DAONotExistException(ExceptionCode.IMPOSSIBLE_ACTION.getErrorCode());
        }
    }

    /**
     * Service method for updating client details, calls method from dao and checks if client exists before updating
     *
     * @param id client id
     * @param clientDetailsDTO client data
     */
    @Override
    public void updateClientDetails(long id, ClientDetailsDTO clientDetailsDTO){
        getClientDetails(id);
        detailsDAO.updateClientDetails(id, clientDetailsDTO);
    }

    /**
     * Service method for updating doctor details, calls method from dao and checks if doctor exists before updating
     *
     * @param id doctor id
     * @param doctorDetailsDTO doctor data
     */
    @Override
    public void updateDoctorDetails(long id, DoctorDetailsDTO doctorDetailsDTO){
        getDoctorDetails(id);
        detailsDAO.updateDoctorDetails(id, doctorDetailsDTO);
    }

    /**
     * Return client data from database.
     * It throws DAONotExistException if it's not a client
     *
     * @param id client id
     * @return client details dto
     */
    @Override
    public ClientDetailsDTO getClientDetails(long id) {
        userService.getUserById(id);
        if(!userDAO.getUserById(id).get().getRole().equals(Role.CLIENT.toString())){
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_CLIENT_DETAILS.getErrorCode());
        }
        return modelMapper.map(detailsDAO.getClientDetails(id), ClientDetailsDTO.class);
    }

    /**
     * Return doctor data from database.
     * It throws DAONotExistException if it's not a doctor
     *
     * @param id doctor id
     * @return doctor details dto
     */
    @Override
    public DoctorDetailsDTO getDoctorDetails(long id) {
        userService.getUserById(id);
        if(!userDAO.getUserById(id).get().getRole().equals(Role.DOCTOR.toString())){
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_DOCTOR_DETAILS.getErrorCode());
        }
        return modelMapper.map(detailsDAO.getDoctorDetails(id), DoctorDetailsDTO.class);
    }

    /**
     * Return all doctor details for doctors with doctor type in params.
     * It throws DAOIncorrectDataException if doctor type is incorrect
     *
     * @param type doctor type
     * @return list of doctor details
     */
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

    /**
     * Return all data about client from database
     * It throws DAONotExistException if we try to get this data for not a client
     *
     * @param id client id
     * @return all data about client
     */
    @Override
    public ClientAllDataDTO getAllClientData(long id) {
        userService.getUserById(id);
        if(!userDAO.getUserById(id).get().getRole().equals(Role.CLIENT.toString())){
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_CLIENT_DETAILS.getErrorCode());
        }
        return detailsDAO.getAllClientData(id);
    }

    /**
     * Return all data about doctor from database
     * It throws DAONotExistException if we try to get this data for not a doctor
     *
     * @param id doctor id
     * @return all data about doctor
     */
    @Override
    public DoctorAllDataDTO getAllDoctorData(long id) {
        userService.getUserById(id);
        if(!userDAO.getUserById(id).get().getRole().equals(Role.DOCTOR.toString())){
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_DOCTOR_DETAILS.getErrorCode());
        }
        return detailsDAO.getAllDoctorData(id);
    }

    /**
     * Return all records for one client from db
     *
     * @param id client id
     * @return list of records for needed client
     */
    @Override
    public List<ClientRecordDTO> getClientRecord(long id) {
        getClientDetails(id);
        return detailsDAO.getClientRecord(id);
    }
}
