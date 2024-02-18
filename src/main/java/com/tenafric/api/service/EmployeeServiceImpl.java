package com.tenafric.api.service;


import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.tenafric.api.converter.EmployeeConverter;
import com.tenafric.api.dto.EmployeeDto;
import com.tenafric.api.entity.Employee;
import com.tenafric.api.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeConverter.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeConverter.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee not exists with a given id : " + employeeId)
                );

        return EmployeeConverter.mapToEmployeeDto(existingEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> EmployeeConverter.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee not exists with a given id : " + employeeDto.getId())
                );

        // convert EmployeeDto to Employee JPA entity
        Employee employee = EmployeeConverter.mapToEmployee(employeeDto);
        return EmployeeConverter.mapToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee not exists with a given id : " + employeeId)
                );

        employeeRepository.deleteById(employeeId);
    }
}
