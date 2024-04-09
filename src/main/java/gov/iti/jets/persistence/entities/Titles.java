package gov.iti.jets.persistence.entities;

import jakarta.persistence.*;
import static jakarta.persistence.GenerationType.IDENTITY;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name="titles",catalog="hr")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class Titles  implements java.io.Serializable {

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="title_id", unique=true, nullable=false)
     private Integer titleId;

    @Column(name="title_name", length=100)
     private String titleName;

    public Titles(String titleName) {
       this.titleName = titleName;
    }

}


