package com.yumyapps.fakeoperator.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/employees")
public class EmployeeController {


    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> findAllEmployees() {
        return new ResponseEntity<>(employeeService.findAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findbyId(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.FOUND);
    }


    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }


    @PostMapping("/add-address")
    public ResponseEntity<Employee> addAddress(@RequestParam(name = "id") Long id,
                                               @RequestBody Address address) {
        return new ResponseEntity<>(employeeService.assignNewAddressToEmployee(id, address), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove-address")
    public ResponseEntity<String> removeAddress(@RequestBody Address address) {
        employeeService.removeAddressById(address.getId());
        return new ResponseEntity<>("Address Removed", HttpStatus.CREATED);
    }

    @GetMapping(path = "/find-address")
    private ResponseEntity<Address> findAddress(@RequestParam("id") Long id) {
        return new ResponseEntity<>(employeeService.findAddressById(id), HttpStatus.FOUND);
    }


    @GetMapping(path = "/find")
    private ResponseEntity<List<Employee>> findByDate(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        System.out.println(dateTime);
        return new ResponseEntity<>(employeeRepository.findByDateTime(dateTime), HttpStatus.FOUND);
    }

    @GetMapping(path = "/between")
    private ResponseEntity<List<Employee>> findBetweenDates(
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {

        return new ResponseEntity<>(employeeRepository.findByDateTimeBetween(startDateTime, endDateTime), HttpStatus.FOUND);
    }

}