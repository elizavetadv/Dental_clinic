package by.overone.clinic.controller;

import by.overone.clinic.dao.ReceptionDAO;
import by.overone.clinic.dto.SearchDTO;
import by.overone.clinic.model.Reception;
import by.overone.clinic.service.ReceptionService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reception")
public class ReceptionController {
    private final ReceptionService receptionService;

    @PostMapping("/confirm/{id}")
    public void confirmRecord(@PathVariable long id) {
        receptionService.confirmRecord(id);
    }

    @GetMapping("/")
    public List<Time> getDoctorFreeTime(@RequestBody SearchDTO searchDTO) {
        return receptionService.getDoctorFreeTime(searchDTO.getDoctorType(), searchDTO.getDate());
    }

    @Transactional
    @PatchMapping("/")
    public void updateRecordDone() {
        receptionService.updateRecordDone();
    }

    private final ReceptionDAO receptionDAO;
    @GetMapping("/r/{id}")
    public Reception getReception(@PathVariable long id){
        return receptionDAO.getById(id);
    }
}
