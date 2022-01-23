package by.overone.clinic.model;

import lombok.Data;

import java.sql.Time;

@Data
public class Record {
    private long id_record;
    private String doctorType;
    private String date;
    private Time time;
    private String status;
    private long user_user_id;
    private long doctor_id;
//    private String time;
//

//    public void parse(String data){
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//        try {
//            date = formatter.parse(data);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
}
