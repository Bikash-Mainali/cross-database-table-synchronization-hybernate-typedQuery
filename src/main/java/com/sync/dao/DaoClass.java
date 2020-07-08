package com.sync.dao;

import java.util.List;

import com.sync.model.Laptop;
import com.sync.model.Student;

public interface DaoClass {	
	public void save(Student student);
	public void save(Laptop laptop);
	public List<Student> findAll();
	public List<Laptop> findLaptopUsers();
	public void delete();
	public void deleteByRollNo(int rollNo);
	public Student findByRoll(int rollNo);
	public void update(Student student,int r);
}
