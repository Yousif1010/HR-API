package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.dtos.DepartmentDto;
import gov.iti.jets.persistence.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartmentMappper {
    DepartmentMappper  INSTANCE = Mappers.getMapper(DepartmentMappper.class);

    @Mapping(source = "employee.empId", target = "empId")
    DepartmentDto toDto(Department department);

    @Mapping(source = "empId", target = "employee.empId")
    Department toDepartment(DepartmentDto departmentDto);

    @Mapping(source = "employee.empId", target = "empId")
    List<DepartmentDto> toDepartmentDtoList(List<Department> departmentList);

    @Mapping(source = "empId", target = "employee.empId")
    List<Department> toDepartmentList(List<DepartmentDto> departmentDtoList);
}
