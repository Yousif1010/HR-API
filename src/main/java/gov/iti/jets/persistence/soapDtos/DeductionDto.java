package gov.iti.jets.persistence.soapDtos;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data

public class DeductionDto implements Serializable {
    int deductionId;
    BigDecimal amount;
    String reason;
    Date date;
    private String employeeName;
    private Integer empId;
}