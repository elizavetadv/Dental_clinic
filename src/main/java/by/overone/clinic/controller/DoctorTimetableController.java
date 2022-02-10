package by.overone.clinic.controller;

import by.overone.clinic.dto.DocTimetableDTO;
import by.overone.clinic.service.DoctorTimetableService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/doctor/timetable")
public class DoctorTimetableController {
    private final DoctorTimetableService doctorTimetableService;

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public DocTimetableDTO getRecordById(@PathVariable long id) {
        return doctorTimetableService.getRecordById(id);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}/{day}")
    public List<DocTimetableDTO> getRecordByDay(@PathVariable long id, @PathVariable int day) {
        return doctorTimetableService.getRecordsByDay(id, day);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}/m/{month}")
    public List<DocTimetableDTO> getRecordByMonth(@PathVariable long id, @PathVariable int month) {
        return doctorTimetableService.getRecordsByMonth(id, month);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}/y/{year}")
    public List<DocTimetableDTO> getRecordByYear(@PathVariable long id, @PathVariable int year) {
        return doctorTimetableService.getRecordsByYear(id, year);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}/all")
    public List<DocTimetableDTO> getAllByDoctorId(@PathVariable long id) {
        return doctorTimetableService.getAllByDoctorId(id);
    }
}
