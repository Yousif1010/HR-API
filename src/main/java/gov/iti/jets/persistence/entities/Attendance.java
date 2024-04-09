package gov.iti.jets.persistence.entities;

import gov.iti.jets.persistence.enums.AttendanceStatus;
import jakarta.persistence.*;
import static jakarta.persistence.GenerationType.IDENTITY;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="attendance",catalog="hr")
public class Attendance  implements java.io.Serializable {

     @Id @GeneratedValue(strategy=IDENTITY)
     @Column(name="attendance_id", unique=true, nullable=false)
     private Integer attendanceId;

     @ManyToOne(fetch=FetchType.LAZY)
     @JoinColumn(name="emp_id", nullable=false)
     private Employee employee;

     @Temporal(TemporalType.DATE)
     @Column(name="date", nullable=false, length=10)
     private Date date;

     @Temporal(TemporalType.TIMESTAMP)
     @Column(name="check_in_time", length=8)
     private Date checkInTime;

     @Temporal(TemporalType.TIMESTAMP)
     @Column(name="check_out_time", length=8)
     private Date checkOutTime;

     @Column(name="status", length=7)
     private AttendanceStatus status;

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

    public Attendance(Employee employee, Date date) {
        this.employee = employee;
        this.date = date;
    }
    public Attendance(Employee employee, Date date, Date checkInTime, Date checkOutTime, AttendanceStatus status) {
       this.employee = employee;
       this.date = date;
       this.checkInTime = checkInTime;
       this.checkOutTime = checkOutTime;
       this.status = status;
    }


}


