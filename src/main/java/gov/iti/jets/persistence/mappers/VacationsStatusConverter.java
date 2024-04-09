package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.enums.VacationsStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class VacationsStatusConverter implements AttributeConverter<VacationsStatus, String> {
    @Override
    public String convertToDatabaseColumn(VacationsStatus vacationsStatus) {
        return vacationsStatus != null ? vacationsStatus.toString() : null;
    }

    @Override
    public VacationsStatus convertToEntityAttribute(String dbData) {
        return dbData != null ? VacationsStatus.valueOf(dbData) : null;
    }
}
