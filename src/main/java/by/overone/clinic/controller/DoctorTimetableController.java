package by.overone.clinic.controller;

import by.overone.clinic.dto.DocTimetableDTO;
import by.overone.clinic.service.DoctorTimetableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}/{day}")
    public List<DocTimetableDTO> getRecordByDay(@PathVariable long id, @PathVariable int day) {
        return doctorTimetableService.getRecordsByDay(id, day);
    }

    @GetMapping("/{id}/m/{month}")
    public List<DocTimetableDTO> getRecordByMonth(@PathVariable long id, @PathVariable int month) {
        return doctorTimetableService.getRecordsByMonth(id, month);
    }

    @GetMapping("/{id}/y/{year}")
    public List<DocTimetableDTO> getRecordByYear(@PathVariable long id, @PathVariable int year) {
        return doctorTimetableService.getRecordsByYear(id, year);
    }

    @GetMapping("/{id}/all")
    public List<DocTimetableDTO> getAllByDoctorId(@PathVariable long id) {
        return doctorTimetableService.getAllByDoctorId(id);
    }
}
