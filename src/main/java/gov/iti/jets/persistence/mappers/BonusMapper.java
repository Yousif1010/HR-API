package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.dtos.BonusDto;
import gov.iti.jets.persistence.entities.Bonus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BonusMapper {
    BonusMapper INSTANCE = Mappers.getMapper(BonusMapper.class);
    @Mapping(source = "employee.empId", target = "empId")
    BonusDto toBonusDto(Bonus bonus);
    @Mapping(source = "empId", target = "employee.empId")
    Bonus toBonus(BonusDto bonusDto);

    @Mapping(source = "employee.empId", target = "empId")
    List<BonusDto> toBonusDtoList(List<Bonus> bonusList);

    @Mapping(source = "empId", target = "employee.empId")
    List<Bonus> toBonusList(List<BonusDto> bonusDtoList);
}
