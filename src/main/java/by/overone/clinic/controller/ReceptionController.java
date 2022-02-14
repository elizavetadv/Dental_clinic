package by.overone.clinic.controller;

import by.overone.clinic.dto.SearchDTO;
import by.overone.clinic.service.ReceptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reception")
public class ReceptionController {
    private final ReceptionService receptionService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{id}")
    public void confirmRecord(@PathVariable long id) {
        receptionService.confirmRecord(id);
    }

    @GetMapping("/")
    public List<Time> getDoctorFreeTime(@RequestBody SearchDTO searchDTO) {
        return receptionService.getDoctorFreeTime(searchDTO.getDoctorType(), searchDTO.getDate());
    }
}
