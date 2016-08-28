package com.ssh.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssh.entity.Course;
import com.ssh.entity.Teacher;
import com.ssh.util.Takepage;

@Repository
public class CourseDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * 增加课程
	 */
	public void save(Course course) {
		sessionFactory.getCurrentSession().save(course);
	}

	/*
	 * 查看全部课程
	 */
	
	@SuppressWarnings("unchecked")
	public List<Course> allCourse(String coursetype) {
		String hql = "from Course  where coursetype=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, coursetype);
		List<Course> courselist = query.list();
		for (Course course2 : courselist) {
			Hibernate.initialize(course2.getTeacher());
			Hibernate.initialize(course2.getStudents());
		}
		return query.list();
	}
	/*
	 * 分页查看全部课程
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Takepage<Course> allpageCourse(String coursetype,Takepage page) {
		String hql = "from Course where coursetype=? order by id ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, coursetype);
		page.setTotalRecords(query.list().size());
		query.setFirstResult((page.getPageNo()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		List<Course> courselist = query.list();
		for (Course course2 : courselist) {
			Hibernate.initialize(course2.getTeacher());
			Hibernate.initialize(course2.getStudents());
		}
		page.setPsize(query.list().size());
		page.setList(query.list());
		return page;
	}
	/*
	 * 查看全部选修了的课程
	 */
	
	@SuppressWarnings("unchecked")
	public List<Course> findCourse(int id) {
		String hql = "select c from Course c inner join c.students as s   where s.id=? ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		//query.setString(1, coursetype);
		
		List<Course> courselist = query.list();
		for (Course course2 : courselist) {
			Hibernate.initialize(course2.getTeacher());
			Hibernate.initialize(course2.getStudents());
		}
		return query.list();
	}
	
	/*
	 * 分页查看全部选修了的课程
	 */
	
	@SuppressWarnings("unchecked")
	public Takepage<Course> findpageCourse(int id,Takepage page) {
		String hql = "select c from Course c inner join c.students as s   where s.id=? ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0,id);
		page.setTotalRecords(query.list().size());
		query.setFirstResult((page.getPageNo()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		List<Course> courselist = query.list();
		for (Course course2 : courselist) {
			Hibernate.initialize(course2.getTeacher());
			Hibernate.initialize(course2.getStudents());
		}
		page.setPsize(query.list().size());
		page.setList(query.list());
		return page;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Course oneCourse(int id) {
		String hql = "from Course  where id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		Course course = (Course) query.uniqueResult();
		Hibernate.initialize(course.getStudents());
		return (Course) query.uniqueResult();

	}
	/**
	 * 更新课程数据
	 * @param course
	 */
	public void updateCourse(Course course){
		sessionFactory.getCurrentSession().update(course);
	}
	/**
	 * 退课，删除操作
	 * @param id
	 */
	public void deleteCourse(int id){
		Course course = oneCourse(id);
		course.setStudents(null);
		sessionFactory.getCurrentSession().delete(course);
	}
	/**
	 * 分页查找
	 */
	/*public List pageAllCourse(String coursetype ,Object[] pro){
		String hql = "from Course where coursetype=? order by courseid limit ?,? ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, coursetype);
		query.setInteger(1, arg1);
		return query.list();
	}*/
}