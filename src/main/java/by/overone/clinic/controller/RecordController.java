package by.overone.clinic.controller;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.exception.DAOIncorrectDataException;
import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.model.Record;
import by.overone.clinic.service.RecordService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/records")
public class RecordController {
    private final RecordService recordService;
    private final ModelMapper modelMapper;

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

    @GetMapping("/{id}")
    public List<RecordDTO> getRecord(@PathVariable long id,
                                     @RequestParam(name = "role", required = false, defaultValue = "") String str) {
        List<RecordDTO> recordDTOS = new ArrayList<>();
        if (str.isEmpty()) {
            recordDTOS.add(modelMapper.map(recordService.getRecordById(id), RecordDTO.class));
            return recordDTOS;
        } else if (str.equals("client")) {
            return recordService.getRecordByClientId(id);
        } else if (str.equals("doctor")) {
            return recordService.getRecordByDoctorId(id);
        } else {
            throw new DAOIncorrectDataException(ExceptionCode.INCORRECT_QUERY_DATA.getErrorCode());
        }
    }

    @GetMapping
    public List<RecordDTO> getRecordsByStatus(@RequestParam(name = "status", required = false, defaultValue = "") String status) {
        if (status.isEmpty()) {
            return recordService.getAllRecords();
        } else {
            return recordService.getRecordByStatus(status);
        }
    }
}
