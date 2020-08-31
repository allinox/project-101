package no.acntech.project101.employee;


import javax.annotation.Generated;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;





    public Employee() {
        // Hibernate
    }

    public Employee(final String firstName, final String lanstName, final LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lanstName;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId(){ return this.id; }

    public String getFirstName(){ return this.firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
