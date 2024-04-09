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
@Table(name="bonus",catalog="hr")
public class Bonus  implements java.io.Serializable {

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="bonus_id", unique=true, nullable=false)
     private Integer bonusId;

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


    public Bonus(Employee employee, BigDecimal amount, Date date) {
        this.employee = employee;
        this.amount = amount;
        this.date = date;
    }
    public Bonus(Employee employee, BigDecimal amount, String reason, Date date) {
       this.employee = employee;
       this.amount = amount;
       this.reason = reason;
       this.date = date;
    }

}


