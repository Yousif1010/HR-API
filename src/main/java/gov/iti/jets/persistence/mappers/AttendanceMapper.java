package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.dtos.AttendanceDto;
import gov.iti.jets.persistence.entities.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttendanceMapper extends GenericMapper<AttendanceDto, Attendance> {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);
}

