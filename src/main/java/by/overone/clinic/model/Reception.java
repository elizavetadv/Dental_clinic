package by.overone.clinic.model;

import lombok.Data;

@Data
public class Reception {
    private long idReception;
    private long record_id_record;
    private long clientId;
    private long doctorId;
}
