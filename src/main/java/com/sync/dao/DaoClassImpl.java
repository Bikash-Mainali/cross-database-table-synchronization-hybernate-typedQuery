package com.sync.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sync.controller.Controller;
import com.sync.model.Laptop;
import com.sync.model.Student;

@Service
public class DaoClassImpl implements DaoClass {
	Logger logger = LoggerFactory.getLogger(Controller.class);

	String sql = "";
	String sql1 = "";

	@Override
	public void save(Student student) {
		Session session = com.sync.util.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		session.saveOrUpdate(student);
		tr.commit();
		session.close();

	}

	@Override
	public void save(Laptop laptop) {

		Session session1 = com.sync.util.HibernateUtilLaptop.getSession();
		Transaction tr1 = session1.beginTransaction();
		session1.saveOrUpdate(laptop);
		tr1.commit();
		session1.close();
	}

	@Override
	public List<Laptop> findLaptopUsers() {
		Session session1 = com.sync.util.HibernateUtilLaptop.getSession();
		//Transaction tr1 = session1.beginTransaction();
		sql = " SELECT l FROM Laptop l";
		// TypedQuery
		return session1.createQuery(sql, Laptop.class).getResultList();
	}

	@Override
	public List<Student> findAll() {
		Session session = com.sync.util.HibernateUtil.getSession();
		//Transaction tr = session.beginTransaction();
		sql = " SELECT s FROM Student s";
		// TypedQuery
		return session.createQuery(sql, Student.class).getResultList();
	}

	@Override
	public Student findByRoll(int rollNo) {

		Session session = com.sync.util.HibernateUtil.getSession();
		//Transaction tr = session.beginTransaction();
		sql = " SELECT s FROM Student s where s.rollNo=" + rollNo + "";
		// Query
		// Query query =session.createQuery(sql);
		return (Student) session.createQuery(sql).getSingleResult();

	}

	@Override
	public void delete() {
		Session session = com.sync.util.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		session.createQuery("DELETE FROM Student s").executeUpdate();
		tr.commit();
		session.close();
	}

	@Override
	public void deleteByRollNo(int rollNo) {
		Session session = com.sync.util.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		session.createQuery("DELETE FROM Student s WHERE s.rollNo=:roll").setParameter("roll", rollNo).executeUpdate();
		tr.commit();
		session.close();

	}

	@Override
	public void update(Student student, int r) {
		logger.info("........................test");
		Session session = com.sync.util.HibernateUtil.getSession();
		Transaction tr = session.beginTransaction();
		sql = "UPDATE Student  s SET s.name=:n WHERE s.rollNo=" + r + "";
		session.createQuery(sql).setParameter("n", student.getName()).executeUpdate();
		tr.commit();

		// sql1="UPDATE Student s SET l.name=s.name FROM Student s INNER JOIN Laptop l
		// ON s.rollNo = l.lid ";
		// sql="UPDATE Student s SET s.name="+student.getName()+" WHERE s.rollNo="+r+"";

		sql1 = "UPDATE Laptop l SET l.name=:n WHERE l.lid=" + r + "";
		Session session1 = com.sync.util.HibernateUtilLaptop.getSession();
		Transaction tr1 = session1.beginTransaction();
		session1.createQuery(sql1).setParameter("n", student.getName()).executeUpdate();
		tr1.commit();
		session1.close();
	}

}
