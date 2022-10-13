package com.yumyapps.fakeoperator.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDateTime(LocalDateTime dateTime);

    List<Employee> findByDateTimeBetween(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);



}