package gov.iti.jets.persistence.dtos;

import gov.iti.jets.persistence.enums.AttendanceStatus;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class AttendanceDto implements Serializable {
    private int attendanceId;
    private Date date;
    private Date checkInTime;
    private Date checkOutTime;
    private AttendanceStatus status;
    private String employeeName;

}