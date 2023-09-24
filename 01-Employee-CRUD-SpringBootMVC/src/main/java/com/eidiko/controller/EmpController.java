package com.eidiko.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eidiko.model.Employee;
import com.eidiko.service.IEmployeeMgmtService;

@Controller
public class EmpController {
	@Autowired
	IEmployeeMgmtService empService;// will inject dao from XML file

	@GetMapping("/")
	public String showHomePage() {
		return "index";
	}

	@GetMapping("/empform")
	public String showform(@ModelAttribute("emp") Employee emp) {
		return "empform";
	}

	@PostMapping("/empform")
	public String save(@ModelAttribute("emp") Employee emp) {
		empService.save(emp);
		return "redirect:/viewemp";// will redirect to viewemp request mapping
	}

	@GetMapping("/viewemp")
	public String viewemp(Map<String, Object> map) {
		List<Employee> empsList = empService.getEmployees();
		map.put("empsList", empsList);
		return "viewemp";
	}

	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable int id, Model m) {
		Employee emp = empService.getEmpById(id);
		m.addAttribute("command", emp);
		return "empeditform";
	}

	@PostMapping("/editsave")
	public String editsave(@ModelAttribute("emp") Employee emp) {
		empService.update(emp,emp.getId());
		return "redirect:/viewemp";
	}

	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		empService.delete(id);
		return "redirect:/viewemp";
	}
}
