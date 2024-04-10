package gov.iti.jets.persistence.soapMappers;

import gov.iti.jets.persistence.soapDtos.DeductionDto;
import gov.iti.jets.persistence.entities.Deduction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeductionMapper {
    DeductionMapper  INSTANCE = Mappers.getMapper( DeductionMapper.class );

    @Mapping(source = "employee.empId", target = "empId")
    DeductionDto ToDeductionDto(Deduction deduction);
    @Mapping(source = "empId", target = "employee.empId")
    Deduction ToDeduction(DeductionDto deductionDto);

    @Mapping(source = "empId", target = "employee.empId")
    List<Deduction> ToDeductionList(List<DeductionDto> deductionDtoList);

    @Mapping(source = "employee.empId", target = "empId")
    List<DeductionDto> ToDeductionDtoList(List<Deduction> deductionList);
}
