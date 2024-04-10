package gov.iti.jets.persistence.soapDtos;

import lombok.Data;

import java.io.Serializable;

@Data

public class DepartmentDto implements Serializable {
    Integer departmentNo;
    String departmentName;
    String employeeName;

    private  Integer empId;
}