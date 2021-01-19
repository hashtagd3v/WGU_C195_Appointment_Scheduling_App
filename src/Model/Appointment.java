package Model;

import java.time.LocalDateTime;

public class Appointment {

    private int appointmentId;
    private String title;
    private String desc;
    private String location;
    private String type;
    //FIXME: start and end date/time, what type?
    private LocalDateTime start;
    private LocalDateTime end;
    //TODO: Find out if we need create date and create by; do we need to include contact, user and customer ID???

}
