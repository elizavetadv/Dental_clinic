package by.overone.clinic.controller;

import by.overone.clinic.dto.DocTimetableDTO;
import by.overone.clinic.service.DoctorTimetableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/doctor/timetable")
public class DoctorTimetableController {
    private final DoctorTimetableService doctorTimetableService;

    @GetMapping("/{id}")
    public DocTimetableDTO getRecordById(@PathVariable long id) {
        return doctorTimetableService.getRecordById(id);
    }

    @GetMapping("/{id}/all")
    public List<DocTimetableDTO> getAllByDoctorId(@PathVariable long id) {
        return doctorTimetableService.getAllByDoctorId(id);
    }

    @GetMapping("/date")
    public List<DocTimetableDTO> getRecordByDate(@RequestParam(name="id") int id,
                                                 @RequestParam(name="day", required = false, defaultValue = "0") int day,
                                                 @RequestParam(name="month", required = false, defaultValue = "0") int month,
                                                 @RequestParam(name="year", required = false, defaultValue = "0") int year) {

        return doctorTimetableService.getRecordByDate(id, day, month, year);
    }
}
