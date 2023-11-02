package test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.EmployeeGroup;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "secondname")
    private String secondName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "employment_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date employmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_group")
    private EmployeeGroup employmentGroup;

    @Column(name="employee_lead_id", nullable=true)
    private int employeeLead;

    private int salary;
}
