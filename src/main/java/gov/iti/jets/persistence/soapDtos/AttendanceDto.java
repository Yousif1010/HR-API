package gov.iti.jets.persistence.soapDtos;

import gov.iti.jets.persistence.enums.AttendanceStatus;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

public class AttendanceDto implements Serializable {
    private int attendanceId;
    private Date date;
    private Date checkInTime;
    private Date checkOutTime;
    private AttendanceStatus status;
    private String employeeName;



    private  Integer empId;

}