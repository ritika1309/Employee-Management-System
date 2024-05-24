package com.project.EmployeeManagementSystem.service;

import com.project.EmployeeManagementSystem.entity.Employee;
import com.project.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void addEmp(Employee e) {
        employeeRepository.save(e);
    }

    public List<Employee> getAllEmp() {
        return employeeRepository.findAll();
    }

    public Employee getEMpById(int id) {
        Optional<Employee> e = employeeRepository.findById(id);
        if (e.isPresent()) {
            return e.get();
        }
        return null;
    }

    public void deleteEMp(int id) {
        employeeRepository.deleteById(id);
    }

    public Page<Employee> getEMpByPaginate(int currentPage, int size) {
        Pageable p = PageRequest.of(currentPage, size);
        return employeeRepository.findAll(p);
    }
}
