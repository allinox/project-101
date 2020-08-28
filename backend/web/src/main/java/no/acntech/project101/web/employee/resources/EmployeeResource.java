package no.acntech.project101.web.employee.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:3000")
//TODO This is a REST controler and should receive request on path employees
@RestController
@RequestMapping("employees")
public class EmployeeResource {

    //TODO The constructor needs to accept the required dependencies and assign them to class variables
    /*public EmployeeResource() {
    }*/
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        //TODO create a GET endpoint find all employees in the database and return them
        EmployeeDto employee1 = new EmployeeDto(
                1l,
                "Alex",
                "Vedeler",
                LocalDate.of(1995,4,4),
                4l);
        EmployeeDto employee2 = new EmployeeDto(
                2l,
                "Tonja",
                "Joseph",
                LocalDate.of(1996,8,21),
                5l);

        List employeeList = new ArrayList<EmployeeDto>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        return ResponseEntity.ok(employeeList);
    }
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById() {
        // TODO create a GET endpoint that fetches a spesific employee based on its ID
        EmployeeDto employee = new EmployeeDto(
                1l,
                "Alex",
                "Vedeler",
                LocalDate.of(1995,4,4),
                4l);

        return ResponseEntity.ok(employee);
    }
    @PostMapping()
    public ResponseEntity createEmployee() {
        //TODO Create a POST endpoint that accepts an employeeDTO and saves it in the database
        final EmployeeDto saved = new EmployeeDto(
                1l,
                "Alex",
                "Vedeler",
                LocalDate.of(1995,4,4),
                4l);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("./{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(final Long id) {
        // TODO Create a DELETE endpoint that deletes a specific employee

        return ResponseEntity.accepted().build();
    }
    @PatchMapping("{id}")
    public ResponseEntity updateEmployee() {
        //TODO Create a PATCH endpoint that updates an employee with new values
        EmployeeDto employee = new EmployeeDto(
                1l,
                "Alex",
                "Vedeler",
                LocalDate.of(1995,4,4),
                4l);
        return ResponseEntity.ok(employee);
    }
}
