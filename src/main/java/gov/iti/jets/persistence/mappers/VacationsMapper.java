package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.dtos.VacationsDto;
import gov.iti.jets.persistence.entities.Vacations;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VacationsMapper {

    VacationsMapper INSTANCE = Mappers.getMapper( VacationsMapper.class );

    VacationsDto ToVacationsDto(Vacations vacations);
    Vacations ToVacations (VacationsDto vacationsDto);

    List<Vacations> ToVacationsList(List<VacationsDto> vacationsDtoList);

    List<VacationsDto> ToVacationsDtoList(List<Vacations> vacationsList);
}
