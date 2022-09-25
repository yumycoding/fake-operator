package com.yumyapps.fakeoperator.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    public Employee saveEmployee(Employee employee) {
        employee.getAddresses().forEach(emp -> emp.setEmployee(employee));
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long empId) {
        return employeeRepository.findById(empId).orElseThrow(EntityNotFoundException::new);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee assignNewAddressToEmployee(Long empId, Address address) {
        var emp = employeeRepository.findById(empId).orElseThrow(EntityNotFoundException::new);
        emp.addAddress(address);
        return employeeRepository.save(emp);
    }

    public Employee removeAddressToEmployee(Long empId, Address address) {
        var emp = employeeRepository.findById(empId).orElseThrow(EntityNotFoundException::new);
        emp.removeAddress(address);
        return employeeRepository.save(emp);
    }

    public void removeAddressById(Long id) {
        addressRepository.findById(id).ifPresent(addressRepository::delete);
    }

    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
