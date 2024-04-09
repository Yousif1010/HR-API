package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.dtos.BonusDto;
import gov.iti.jets.persistence.entities.Bonus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BonusMapper {
    BonusMapper INSTANCE = Mappers.getMapper(BonusMapper.class);
    BonusDto toBonusDto(Bonus bonus);
    Bonus toBonus(BonusDto bonusDto);

    List<BonusDto> toBonusDtoList(List<Bonus> bonusList);

    List<Bonus> toBonusList(List<BonusDto> bonusDtoList);
}
