package gov.iti.jets.persistence.entities;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="department",catalog="hr")
public class Department  implements java.io.Serializable {

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="department_no", unique=true, nullable=false)
     private Integer departmentNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="manager_id")
     private Employee employee;

    @Column(name="department_name", length=100)
     private String departmentName;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="department")
     private Set<Employee> employees = new HashSet<Employee>(0);

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

    public Department(Employee employee, String departmentName, Set<Employee> employees) {
       this.employee = employee;
       this.departmentName = departmentName;
       this.employees = employees;
    }
    public Department(Employee employee, String departmentName){
        this.employee = employee;
        this.departmentName = departmentName;
    }
    public Department(String departmentName){
        this.departmentName = departmentName;
    }

}


