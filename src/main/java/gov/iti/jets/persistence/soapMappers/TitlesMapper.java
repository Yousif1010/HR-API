package gov.iti.jets.persistence.soapMappers;

import gov.iti.jets.persistence.soapDtos.TitlesDto;
import gov.iti.jets.persistence.entities.Titles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TitlesMapper {

    TitlesMapper  INSTANCE = Mappers.getMapper(TitlesMapper.class);

    Titles toTitles(TitlesDto titlesDto);
    TitlesDto toTitlesDto(Titles titles);
    List<Titles> toTitlesList(List<TitlesDto> titlesDtoList);
    List<TitlesDto> toTitlesDtoList(List<Titles> titlesList);
}
