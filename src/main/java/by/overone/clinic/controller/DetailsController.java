package by.overone.clinic.controller;

import by.overone.clinic.dto.*;
import by.overone.clinic.service.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/details")
public class DetailsController {
    private final DetailsService detailsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void addClientDetails(@PathVariable long id, @Valid @RequestBody ClientDetailsDTO clientDetailsDTO) {
        detailsService.addClientDetails(id, clientDetailsDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public void updateClientDetails(@PathVariable long id, @Valid @RequestBody ClientDetailsDTO clientDetailsDTO) {
        detailsService.updateClientDetails(id, clientDetailsDTO);
    }

    @GetMapping("/{id}")
    public ClientDetailsDTO getClientDetails(@PathVariable long id) {
        return detailsService.getClientDetails(id);
    }

    @GetMapping("/{id}/info")
    public ClientAllDataDTO getAllClientData(@PathVariable long id) {
        return detailsService.getAllClientData(id);
    }

    @GetMapping("/{id}/records")
    public List<ClientRecordDTO> getClientRecords(@PathVariable long id) {
        return detailsService.getClientRecord(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/{id}")
    public void addDoctorDetails(@PathVariable long id, @Valid @RequestBody DoctorDetailsDTO doctorDetailsDTO) {
        detailsService.addDoctorDetails(id, doctorDetailsDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/admin/{id}")
    public void updateDoctorDetails(@PathVariable long id, @Valid @RequestBody DoctorDetailsDTO doctorDetailsDTO) {
        detailsService.updateDoctorDetails(id, doctorDetailsDTO);
    }

    @GetMapping("/doc/{id}")
    public DoctorDetailsDTO getDoctorDetails(@PathVariable long id) {
        return detailsService.getDoctorDetails(id);
    }

    @GetMapping("/doc/")
    public List<DoctorDetailsDTO> getDoctorDetailsByType(@RequestParam(name = "type", required = true) String type) {
        return detailsService.getDoctorDetailsByType(type);
    }

    @GetMapping("/doc/{id}/info")
    public DoctorAllDataDTO getAllDoctorData(@PathVariable long id) {
        return detailsService.getAllDoctorData(id);
    }
}
