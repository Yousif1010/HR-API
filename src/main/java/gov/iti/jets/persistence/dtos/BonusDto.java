package gov.iti.jets.persistence.dtos;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class BonusDto implements Serializable {
    int bonusId;
    BigDecimal amount;
    String reason;
    Date date;
    private String employeeName;
    private Integer empId;
}