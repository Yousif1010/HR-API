package gov.iti.jets.persistence.dtos;

import lombok.Data;
import java.io.Serializable;

@Data
public class TitlesDto implements Serializable {
    int titleId;
    String titleName;
}