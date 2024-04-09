package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.dtos.DeductionDto;
import gov.iti.jets.persistence.entities.Deduction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface DeductionMapper {
    DeductionMapper  INSTANCE = Mappers.getMapper( DeductionMapper.class );

    DeductionDto ToDeductionDto(Deduction deduction);
    Deduction ToDeduction(DeductionDto deductionDto);

    List<Deduction> ToDeductionList(List<DeductionDto> deductionDtoList);

    List<DeductionDto> ToDeductionDtoList(List<Deduction> deductionList);
}
