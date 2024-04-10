package gov.iti.jets.persistence.soapMappers;

import gov.iti.jets.persistence.soapDtos.EmployeeDto;
import gov.iti.jets.persistence.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper  INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "departmentId", target = "department.departmentNo")
    @Mapping(source = "managerId", target = "employee.empId")
    Employee toEmployee(EmployeeDto employeeDto);

    @Mapping(source = "department.departmentNo", target = "departmentId")
    @Mapping(source = "employee.empId", target = "managerId")
    EmployeeDto toEmployeeDto(Employee employee);

    @Mapping(source = "departmentId", target = "department.departmentNo")
    @Mapping(source = "managerId", target = "employee.empId")
    List<Employee> toEmployeeList(List<EmployeeDto> employeeDtoList);

    @Mapping(source = "department.departmentNo", target = "departmentId")
    @Mapping(source = "employee.empId", target = "managerId")
    List<EmployeeDto> toEmployeeDtoList(List<Employee> employeeList);
}
