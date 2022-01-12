package by.overone.clinic.controller;

import by.overone.clinic.dao.DetailsDAO;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.model.DoctorDetails;
import by.overone.clinic.service.DetailsService;
import by.overone.clinic.service.UserService;
import by.overone.clinic.util.validation.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/details")
public class DetailsController {
    private final DetailsService detailsService;
    private final DetailsDAO detailsDAO;

    @Transactional
    @PostMapping("/addClientDetails/{id}")
    public void addClientDetails(@PathVariable long id, @RequestBody ClientDetails clientDetails) throws ValidationException {
        detailsService.addClientDetails(id, clientDetails);
    }

    @Transactional
    @PostMapping("/addDoctorDetails/{id}")
    public void addDoctorDetails(@PathVariable long id, @RequestBody DoctorDetails doctorDetails) throws ValidationException {
        detailsService.addDoctorDetails(id, doctorDetails);
    }

    @PatchMapping("/updateClientDetails")
    public void updateClientDetails(@RequestBody ClientDetails clientDetails) throws ValidationException{
        detailsService.updateClientDetails(clientDetails);
    }

    //updateDoctorDetails

    @GetMapping("/getClientDetails/{id}")
    public ClientDetails getClientDetails(@PathVariable long id){
        return detailsService.getClientDetails(id);
    }

    @GetMapping("/getDoctorDetails/{id}")
    public DoctorDetails getDoctorDetails(@PathVariable long id){
        return detailsService.getDoctorDetails(id);
    }

}
