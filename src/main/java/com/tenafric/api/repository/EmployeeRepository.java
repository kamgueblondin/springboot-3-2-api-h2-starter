package com.tenafric.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tenafric.api.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
