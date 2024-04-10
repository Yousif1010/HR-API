package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.dtos.AttendanceDto;
import gov.iti.jets.persistence.entities.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttendanceMapper extends GenericMapper<AttendanceDto, Attendance> {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    @Mapping(source = "empId", target = "employee.empId")
    @Override
    Attendance toEntity(AttendanceDto dto);

    @Mapping(source = "employee.empId", target = "empId")
    @Override
    AttendanceDto toDto(Attendance entity);

    @Mapping(source = "empId", target = "employee.empId")
    @Override
    List<Attendance> toEntityList(List<AttendanceDto> dtoList);

    @Mapping(source = "employee.empId", target = "empId")
    @Override
    List<AttendanceDto> toDtoList(List<Attendance> entityList);
}

