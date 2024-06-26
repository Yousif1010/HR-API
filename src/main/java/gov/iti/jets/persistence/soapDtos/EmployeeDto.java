package gov.iti.jets.persistence.soapDtos;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data

public class EmployeeDto implements Serializable {
    Integer empId;
    String firstName;
    String lastName;
    Date dob;
    Integer titleId;
    String email;
    String username;
    String password;
    Date hireDate;
    String addressZone;
    String addressCity;
    String addressCountry;
    BigDecimal salary;
    String phone;
    private String managerName;
    private String departmentName;
    private Integer managerId;
    private Integer departmentId;
}