package by.overone.clinic.model;

import lombok.Data;

@Data
public class Reception {
    private long id_reception;
    private long record_id_record;
    private long client_id;
    private long doctor_id;
}
