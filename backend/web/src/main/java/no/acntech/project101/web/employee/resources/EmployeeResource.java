package no.acntech.project101.web.employee.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.acntech.project101.company.Company;
import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.service.EmployeeService;
import no.acntech.project101.web.company.resources.converter.CompanyConverter;
import no.acntech.project101.web.employee.resources.converter.EmployeeConverter;
import no.acntech.project101.web.employee.resources.converter.EmployeeDtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("employees")
public class EmployeeResource {

    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;
    private final EmployeeConverter employeeConverter;

    public EmployeeResource(EmployeeService employeeService, EmployeeDtoConverter employeeDtoConverter, EmployeeConverter employeeConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
        this.employeeConverter = employeeConverter;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {

        final List<Employee> employees = employeeService.findAll();
        final List<EmployeeDto> employeeDtos = employees.stream().
                map(employeeDtoConverter::convert).
                collect(Collectors.toList());


        return ResponseEntity.ok(employeeDtos);
    }
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable final Long id) {
        final Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()){
            final EmployeeDto employeeDto = employeeDtoConverter.convert(employee.get());
            return ResponseEntity.ok(employeeDto);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping
    public ResponseEntity createEmployee(@RequestBody final EmployeeDto employeeDto) {
        final Employee employee = employeeConverter.convert(employeeDto);
        final Employee saved = employeeService.save(employee);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("./{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable final Long id) {
        final Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()){
            employeeService.delete(id);
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("{id}")
    public ResponseEntity updateEmployee(@PathVariable final Long id, @RequestBody final EmployeeDto employeeDto) {
        final Optional<Employee> dbEmployee = employeeService.findById(id);

        if (dbEmployee.isPresent()){
            Employee existingEmployee = dbEmployee.get();
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setLastName(employeeDto.getLastName());
            existingEmployee.setDateOfBirth(employeeDto.getDateOfBirth());

            Employee saved = employeeService.save(existingEmployee);

            final EmployeeDto converted = employeeDtoConverter.convert(saved);
            return ResponseEntity.ok(converted);
        } else{
            return ResponseEntity.notFound().build();
        }

    }
}
