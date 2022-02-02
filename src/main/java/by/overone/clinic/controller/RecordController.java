package by.overone.clinic.controller;

import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.model.Record;
import by.overone.clinic.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/records")
public class RecordController {
    private final RecordService recordService;

    @PostMapping("/{id}")
    public void addRecord(@PathVariable long id, @Valid @RequestBody RecordDTO recordDTO) {
        recordService.makeRecord(id, recordDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable long id) {
        recordService.deleteRecord(id);
    }

    @PatchMapping("/{id}")
    public void updateRecord(@PathVariable long id, @RequestBody RecordDTO recordDTO) {
        recordService.updateRecord(id, recordDTO);
    }

    @GetMapping
    public List<RecordDTO> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public Record getRecordById(@PathVariable long id) {
        return recordService.getRecordById(id);
    }

    @GetMapping("/client/{id}")
    public List<RecordDTO> getRecordByClientId(@PathVariable long id) {
        return recordService.getRecordByClientId(id);
    }

    @GetMapping("/doctor/{id}")
    public List<RecordDTO> getRecordByDoctorId(@PathVariable long id) {
        return recordService.getRecordByDoctorId(id);
    }

    @GetMapping("/status/{status}")
    public List<Record> getRecordsByStatus(@PathVariable String status) {
        return recordService.getRecordByStatus(status);
    }
}
