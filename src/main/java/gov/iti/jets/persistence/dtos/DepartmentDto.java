package gov.iti.jets.persistence.dtos;

import lombok.Data;
import java.io.Serializable;

@Data
public class DepartmentDto implements Serializable {
    Integer departmentNo;
    String departmentName;
    String employeeName;
}