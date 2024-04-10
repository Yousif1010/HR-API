package gov.iti.jets.persistence.dtos;

import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data

public class DepartmentDto implements Serializable {
    Integer departmentNo;
    String departmentName;
    String employeeName;
//    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
//    private List<Link> link = new ArrayList<>();
    private  Integer empId;
}