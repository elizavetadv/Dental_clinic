package by.overone.clinic.controller;

import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.model.Record;
import by.overone.clinic.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/records")
public class RecordController {
    private final RecordService recordService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void addRecord(@PathVariable long id, @Valid @RequestBody RecordDTO recordDTO) {
        recordService.makeRecord(id, recordDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable long id) {
        recordService.deleteRecord(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public void updateRecord(@PathVariable long id, @RequestBody RecordDTO recordDTO) {
        recordService.updateRecord(id, recordDTO);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping
    public List<RecordDTO> getAllRecords() {
        return recordService.getAllRecords();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public Record getRecordById(@PathVariable long id) {
        return recordService.getRecordById(id);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/client/{id}")
    public List<RecordDTO> getRecordByClientId(@PathVariable long id) {
        return recordService.getRecordByClientId(id);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/doctor/{id}")
    public List<RecordDTO> getRecordByDoctorId(@PathVariable long id) {
        return recordService.getRecordByDoctorId(id);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/status/{status}")
    public List<Record> getRecordsByStatus(@PathVariable String status) {
        return recordService.getRecordByStatus(status);
    }
}
