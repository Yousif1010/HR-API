package gov.iti.jets.persistence.mappers;


import gov.iti.jets.persistence.enums.VacationsStatus;
import gov.iti.jets.persistence.enums.VacationsType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class VacationsTypeConverter implements AttributeConverter<VacationsType, String> {
    @Override
    public String convertToDatabaseColumn(VacationsType VacationsType) {
        return VacationsType != null ? VacationsType.toString() : null;
    }

    @Override
    public VacationsType convertToEntityAttribute(String dbData) {
        return dbData != null ? VacationsType.valueOf(dbData) : null;
    }
}
