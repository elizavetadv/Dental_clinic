package by.overone.clinic.controller;

import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.dto.RecordUpdatedDTO;
import by.overone.clinic.model.Record;
import by.overone.clinic.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/records")
public class RecordController {
    private final RecordService recordService;
//    private final RecordDAO recordDAO;

    @PostMapping("/add/{id}")
    public void addRecord(@PathVariable long id, @RequestBody RecordDTO recordDTO) {
        recordService.makeRecord(id, recordDTO);
    }

    @PatchMapping("/delete/{id}")
    public void deleteRecord(@PathVariable long id) {
        recordService.deleteRecord(id);
    }

    @PatchMapping("/update")
    public void updateRecord(@RequestBody RecordUpdatedDTO recordUpdatedDTO) {
        recordService.updateRecord(recordUpdatedDTO);
    }

    @GetMapping
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public Record getRecordById(@PathVariable long id) {
        return recordService.getRecordById(id);
    }

    @GetMapping("/client/{id}")
    public Record getRecordByClientId(@PathVariable long id) {
        return recordService.getRecordByClientId(id);
    }

    @GetMapping("/doctor/{id}")
    public Record getRecordByDoctorId(@PathVariable long id) {
        return recordService.getRecordByDoctorId(id);
    }

    @GetMapping("/status/{status}")
    public List<Record> getRecordsByStatus(@PathVariable String status) {
        return recordService.getRecordByStatus(status);
    }
}
