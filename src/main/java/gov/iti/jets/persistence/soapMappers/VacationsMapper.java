package gov.iti.jets.persistence.soapMappers;

import gov.iti.jets.persistence.soapDtos.VacationsDto;
import gov.iti.jets.persistence.entities.Vacations;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VacationsMapper {

    VacationsMapper INSTANCE = Mappers.getMapper( VacationsMapper.class );


    @Mapping(source = "employee.empId", target = "empId")
    VacationsDto ToVacationsDto(Vacations vacations);

    @Mapping(source = "empId", target = "employee.empId")
    Vacations ToVacations (VacationsDto vacationsDto);


    @Mapping(source = "empId", target = "employee.empId")
    List<Vacations> ToVacationsList(List<VacationsDto> vacationsDtoList);

    @Mapping(source = "employee.empId", target = "empId")
    List<VacationsDto> ToVacationsDtoList(List<Vacations> vacationsList);
}
