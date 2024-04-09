package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.dtos.EmployeeDto;
import gov.iti.jets.persistence.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper  INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee toEmployee(EmployeeDto employeeDto);

    EmployeeDto toEmployeeDto(Employee employee);

    List<Employee> toEmployeeList(List<EmployeeDto> employeeDtoList);

    List<EmployeeDto> toEmployeeDtoList(List<Employee> employeeList);
}
