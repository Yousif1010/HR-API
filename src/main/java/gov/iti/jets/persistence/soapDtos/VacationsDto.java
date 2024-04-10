package gov.iti.jets.persistence.soapDtos;

import gov.iti.jets.persistence.enums.VacationsStatus;
import gov.iti.jets.persistence.enums.VacationsType;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class VacationsDto implements Serializable {
    Integer vacationId;
    VacationsType type;
    Date startDate;
    Date endDate;
    VacationsStatus status;
    String reason;
    private String employeeName;
    private  Integer empId;

}