package by.overone.clinic.controller;

import by.overone.clinic.dao.DetailsDAO;
import by.overone.clinic.dto.ClientAllDataDTO;
import by.overone.clinic.dto.DoctorAllDataDTO;
import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.model.DoctorDetails;
import by.overone.clinic.service.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/details")
public class DetailsController {
    private final DetailsService detailsService;
    private final DetailsDAO detailsDAO;

    @Transactional
    @PostMapping("/addClient/{id}")
    public void addClientDetails(@PathVariable long id, @Valid @RequestBody ClientDetails clientDetails){
        detailsService.addClientDetails(id, clientDetails);
    }

    @Transactional
    @PostMapping("/addDoctor/{id}")
    public void addDoctorDetails(@PathVariable long id, @Valid @RequestBody DoctorDetails doctorDetails){
        detailsService.addDoctorDetails(id, doctorDetails);
    }

    @PatchMapping("/updateClient")
    public void updateClientDetails(@Valid @RequestBody ClientDetails clientDetails){
        detailsService.updateClientDetails(clientDetails);
    }

    @PatchMapping("/updateDoctor")
    public void updateDoctorDetails(@Valid @RequestBody DoctorDetails doctorDetails){
        detailsService.updateDoctorDetails(doctorDetails);
    }

    @GetMapping("/getClient/{id}")
    public ClientDetails getClientDetails(@PathVariable long id){
        return detailsService.getClientDetails(id);
    }

    @GetMapping("/getDoctor/{id}")
    public DoctorDetails getDoctorDetails(@PathVariable long id){
        return detailsService.getDoctorDetails(id);
    }

    @GetMapping("/getDoctor/type/{type}")
    public List<DoctorDetails> getDoctorDetailsByType(@PathVariable String type){
        return detailsService.getDoctorDetailsByType(type);
    }

    @GetMapping("/getClient/info/{id}")
    public ClientAllDataDTO getAllClientData(@PathVariable long id){
        return detailsService.getAllClientData(id);
    }

    @GetMapping("/getDoctor/info/{id}")
    public DoctorAllDataDTO getAllDoctorData(@PathVariable long id){
        return detailsService.getAllDoctorData(id);
    }
}
