package gov.iti.jets.persistence.dtos;

import gov.iti.jets.persistence.enums.VacationsStatus;
import gov.iti.jets.persistence.enums.VacationsType;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class VacationsDto implements Serializable {
    int vacationId;
    VacationsType type;
    Date startDate;
    Date endDate;
    VacationsStatus status;
    String reason;
    private String employeeName;

}