package com.project.EmployeeManagementSystem.controller;

import com.project.EmployeeManagementSystem.entity.Employee;
import com.project.EmployeeManagementSystem.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home(Model m) {
        return findPaginated(0, m);
    }
    @GetMapping("/addemp")
    public String addEmpForm() {
        return "add_emp";
    }
    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee e, HttpSession session) {
        employeeService.addEmp(e);
        session.setAttribute("msg", "Emplyoee Added Sucessfully..");
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m) {
        Employee e = employeeService.getEMpById(id);
        m.addAttribute("emp", e);
        return "edit";
    }
    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
        employeeService.addEmp(e);
        session.setAttribute("msg", "Emp Data Update Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEMp(@PathVariable int id, HttpSession session) {

        employeeService.deleteEMp(id);
        session.setAttribute("msg", "Emp Data Delete Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/page/{pageno}")
    public String findPaginated(@PathVariable int pageno, Model m) {

        Page<Employee> emplist = employeeService.getEMpByPaginate(pageno, 2);
        m.addAttribute("emp", emplist);
        m.addAttribute("currentPage", pageno);
        m.addAttribute("totalPages", emplist.getTotalPages());
        m.addAttribute("totalItem", emplist.getTotalElements());
        return "index";
    }


}
