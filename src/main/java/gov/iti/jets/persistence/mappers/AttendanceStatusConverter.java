package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.enums.AttendanceStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AttendanceStatusConverter implements AttributeConverter<AttendanceStatus, String> {

    @Override
    public String convertToDatabaseColumn(AttendanceStatus attendanceStatus) {
        return attendanceStatus != null ? attendanceStatus.toString() : null;
    }

    @Override
    public AttendanceStatus convertToEntityAttribute(String dbData) {
        return dbData != null ? AttendanceStatus.valueOf(dbData) : null;
    }
}
