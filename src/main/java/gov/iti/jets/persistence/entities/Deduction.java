package gov.iti.jets.persistence.entities;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="deduction",catalog="hr")
public class Deduction  implements java.io.Serializable {

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="deduction_id", unique=true, nullable=false)
     private Integer deductionId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employee_id", nullable=false)
     private Employee employee;

    @Column(name="amount", nullable=false, precision=10, scale=2)
     private BigDecimal amount;

    @Column(name="reason", length=255)
     private String reason;

    @Temporal(TemporalType.DATE)
    @Column(name="date", nullable=false, length=10)
     private Date date;

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
    public Deduction(Employee employee, BigDecimal amount, Date date) {
        this.employee = employee;
        this.amount = amount;
        this.date = date;
    }
    public Deduction(Employee employee, BigDecimal amount, String reason, Date date) {
       this.employee = employee;
       this.amount = amount;
       this.reason = reason;
       this.date = date;
    }

}


