package gov.iti.jets.persistence.soapDtos;

import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data

public class TitlesDto implements Serializable {
    int titleId;
    String titleName;

}