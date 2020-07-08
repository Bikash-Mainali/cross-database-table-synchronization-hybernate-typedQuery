package com.sync.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sync.dao.DaoClassImpl;
import com.sync.model.Laptop;
import com.sync.model.Student;

@RestController
@RequestMapping("/api")
public class Controller {
	Logger logger = LoggerFactory.getLogger(Controller.class);

	Laptop laptop = new Laptop();

	// cross database references are not implemented

	@Autowired
	private DaoClassImpl daoClassImpl;

	@PostMapping("/post")
	public ResponseEntity<Void> post(@RequestBody Student student) {
		logger.info("student name is:{}", student.getName());

		daoClassImpl.save(student);

		String name = student.getName();
		laptop.setName(name);
		laptop.setLid(student.getRollNo());
		daoClassImpl.save(laptop);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/getAllStudents")
	public List<Student> getStudents() {
		return daoClassImpl.findAll();
	}

	@GetMapping("/getAllLaptopUser")
	public List<Laptop> getLaptopUsers() {
		return daoClassImpl.findLaptopUsers();
	}

	@GetMapping("/getByRollNo/{rollNo}")
	public Student get(@PathVariable("rollNo") int rollNo) {

		return daoClassImpl.findByRoll(rollNo);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteByRoll() {
		daoClassImpl.delete();
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	@DeleteMapping("/deleteByRollNo/{rollNo}")
	public ResponseEntity<String> delete(@PathVariable("rollNo") int rollNo) {
		daoClassImpl.deleteByRollNo(rollNo);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	@PutMapping("/update/{rollNo}")
	public ResponseEntity<String> update(@PathVariable("rollNo") int rollNo, @RequestBody Student student) {
		logger.info("updating information");
		daoClassImpl.update(student, rollNo);
		return new ResponseEntity<>("updated", HttpStatus.OK);
	}

}