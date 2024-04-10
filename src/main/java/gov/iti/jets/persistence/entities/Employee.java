package gov.iti.jets.persistence.entities;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee",catalog="hr")
public class Employee  implements java.io.Serializable {

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="emp_id", unique=true, nullable=false)
     private Integer empId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="department_id")
     private Department department;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="manager_id")
     private Employee employee;

    @Column(name="first_name", nullable=false, length=50)
     private String firstName;

    @Column(name="last_name", nullable=false, length=50)
     private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name="dob", nullable=false, length=10)
     private Date dob;

    @Column(name="title_id", nullable=false)
     private Integer titleId;

    @Column(name="email", nullable=false, length=100)
     private String email;

    @Temporal(TemporalType.DATE)
    @Column(name="hire_date", nullable=false, length=10)
     private Date hireDate;

    @Column(name="address_zone", nullable=false, length=100)
     private String addressZone;

    @Column(name="address_city", nullable=false, length=100)
     private String addressCity;

    @Column(name="address_country", nullable=false, length=100)
     private String addressCountry;

    @Column(name="salary", nullable=false, precision=10, scale=2)
     private BigDecimal salary;

    @Column(name="phone", nullable=false, length=20)
     private String phone;

    @Column(name="is_deleted", nullable=false)
     private boolean isDeleted;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="employee")
     private Set<Department> departments = new HashSet<Department>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="employee" , cascade = CascadeType.ALL)
     private Set<Vacations> vacationses = new HashSet<Vacations>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="employee" , cascade = CascadeType.ALL)
     private Set<Deduction> deductions = new HashSet<Deduction>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="employee" , cascade = CascadeType.ALL)
     private Set<Bonus> bonuses = new HashSet<Bonus>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="employee")
     private Set<Employee> employees = new HashSet<Employee>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="employee" , cascade = CascadeType.ALL)
     private Set<Attendance> attendances = new HashSet<Attendance>(0);

    @Transient
    private String departmentName;
    @Transient
    private String managerName;

    @PostLoad
    private void postLoad() {
        if(employee != null){
            managerName = employee.getFirstName() + " " + employee.getLastName();
        }
        if(employee ==null){
            managerName="none";
        }
        if(department != null){
            departmentName = department.getDepartmentName();
        }
        if(department==null){
            departmentName="none";
        }
    }

//    @PrePersist
//    public  void prePersist(){
//        if(hireDate==null){
//            hireDate=new Date();
//        }
//
//    }

    public Employee(String firstName, String lastName, Date dob, int titleId, String email, Date hireDate, String addressZone, String addressCity, String addressCountry, BigDecimal salary, String phone, boolean isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.titleId = titleId;
        this.email = email;
        this.hireDate = hireDate;
        this.addressZone = addressZone;
        this.addressCity = addressCity;
        this.addressCountry = addressCountry;
        this.salary = salary;
        this.phone = phone;
        this.isDeleted = isDeleted;
    }
    public Employee(Department department, Employee employee, String firstName, String lastName, Date dob, int titleId, String email, Date hireDate, String addressZone, String addressCity, String addressCountry, BigDecimal salary, String phone, boolean isDeleted, Set<Department> departments, Set<Vacations> vacationses, Set<Deduction> deductions, Set<Bonus> bonuses, Set<Employee> employees, Set<Attendance> attendances) {
       this.department = department;
       this.employee = employee;
       this.firstName = firstName;
       this.lastName = lastName;
       this.dob = dob;
       this.titleId = titleId;
       this.email = email;
       this.hireDate = hireDate;
       this.addressZone = addressZone;
       this.addressCity = addressCity;
       this.addressCountry = addressCountry;
       this.salary = salary;
       this.phone = phone;
       this.isDeleted = isDeleted;
       this.departments = departments;
       this.vacationses = vacationses;
       this.deductions = deductions;
       this.bonuses = bonuses;
       this.employees = employees;
       this.attendances = attendances;
    }
    public void markAsDeleted() {
        this.isDeleted = true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(empId, employee.empId);
    }

    public void updateEmployee(Employee employee) {
        if (employee.getFirstName() != null) {
            this.firstName = employee.getFirstName();
        }
        if (employee.getLastName() != null) {
            this.lastName = employee.getLastName();
        }
        if (employee.getDob() != null) {
            this.dob = employee.getDob();
        }
        if (employee.getTitleId() != null) {
            this.titleId = employee.getTitleId();
        }
        if (employee.getEmail() != null) {
            this.email = employee.getEmail();
        }
        if (employee.getAddressZone() != null) {
            this.addressZone = employee.getAddressZone();
        }
        if (employee.getAddressCity() != null) {
            this.addressCity = employee.getAddressCity();
        }
        if (employee.getAddressCountry() != null) {
            this.addressCountry = employee.getAddressCountry();
        }
        if (employee.getSalary() != null) {
            this.salary = employee.getSalary();
        }
        if (employee.getPhone() != null) {
            this.phone = employee.getPhone();
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", titleId=" + titleId +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", addressZone='" + addressZone + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressCountry='" + addressCountry + '\'' +
                ", salary=" + salary +
                ", phone='" + phone + '\'' +
                '}';
    }
}


