package by.overone.clinic.model;

import lombok.Data;

@Data
public class Record {
    private long id_record;
    private String doctorType;
    private String data;
    private String time;
}
