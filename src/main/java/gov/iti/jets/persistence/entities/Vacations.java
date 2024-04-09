package gov.iti.jets.persistence.entities;

import gov.iti.jets.persistence.enums.VacationsStatus;
import gov.iti.jets.persistence.enums.VacationsType;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Data
@NoArgsConstructor
@Entity
@Table(name="vacations",catalog="hr")
public class Vacations  implements java.io.Serializable {
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="vacation_id", unique=true, nullable=false)
     private Integer vacationId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_id", nullable=false)
     private Employee employee;

    @Column(name="type", nullable=false, length=6)
     private VacationsType type;

    @Temporal(TemporalType.DATE)
    @Column(name="start_date", nullable=false, length=10)
     private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name="end_date", nullable=false, length=10)
     private Date endDate;

    @Column(name="status", length=8)
     private VacationsStatus status;

    @Column(name="reason", length=255)
     private String reason;

    @Transient
    private String employeeName;

    @PostLoad
    private void postLoad() {
        if(employee != null){
            employeeName = employee.getFirstName() + " " + employee.getLastName();
        }
        if(employee==null){
            employeeName="none";
        }
    }

    public Vacations(Employee employee, VacationsType type, Date startDate, Date endDate) {
        this.employee = employee;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Vacations(Employee employee, VacationsType type, Date startDate, Date endDate, VacationsStatus status, String reason) {
       this.employee = employee;
       this.type = type;
       this.startDate = startDate;
       this.endDate = endDate;
       this.status = status;
       this.reason = reason;
    }


}


