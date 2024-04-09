package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.dtos.DepartmentDto;
import gov.iti.jets.persistence.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartmentMappper {
    DepartmentMappper  INSTANCE = Mappers.getMapper(DepartmentMappper.class);

    DepartmentDto toDto(Department department);

    Department toDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> toDepartmentDtoList(List<Department> departmentList);

    List<Department> toDepartmentList(List<DepartmentDto> departmentDtoList);
}
