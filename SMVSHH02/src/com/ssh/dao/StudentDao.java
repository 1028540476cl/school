package com.ssh.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssh.entity.Course;
import com.ssh.entity.Student;


@Repository
public class StudentDao {
    
	 @Autowired
    private SessionFactory sessionFactory;

	 /*
	  * 增加学生-->课程（增加学生关联到增加课程进去）
	  */
	public void save(Student student){
		sessionFactory.getCurrentSession().save(student);  
    }
	/*
	 * 查看全部学生
	 */
	@SuppressWarnings("unchecked")
	public List allStudent(){
		String hql="from Student";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Student> studentList = query.list();
		String hql1 = "from Course";
		
		return studentList;

	}
	/*
	 * 查找一个学生按hql数据查找
	 * ;//get跟load的区别1）  get采用立即加载方式，而load采用延迟加载
	 *					2）get立即向数据库发送查询请求，而load是向数据库发送一个代理，当使用到的时候才发送请求
	 *					3）如果数据库没有对应的记录，get返回null，而load返回ObjectNotFoundException；
	*/
	public Student oneStudent(String  studentid,String password){
		
		String hql = "from Student  where studentid=? and psw=?";  
		 Query query = sessionFactory.getCurrentSession().createQuery(hql);  
		  query.setString(0, studentid);
		  query.setString(1, password);
		  return (Student)query.uniqueResult();

	}
	/*
	 * 查找选修的课程
	 */
	@SuppressWarnings("unchecked")
	public List findCourse(int id){
		String hql = "from Student where id=?";
		 Query query = sessionFactory.getCurrentSession().createQuery(hql);  
		  query.setInteger(0, id);
		  List<Student> studntlist = query.list();
			for (Student student : studntlist) {
				Hibernate.initialize(student.getCourses());
				Hibernate.initialize(student.getTeachers());
			}
		  return query.list();
	}
/*	
	 * 修改一个学生的信息
	 
	public boolean updateStudent(Student student){
		
		 String hql = "update Student s set s.name=?,s.psw=?,s.age=?,s.sex=? where s.studentid=?";  
	        Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	        query.setString(0, student.getName());  
	        query.setString(1, student.getPsw());  
	        query.setInteger(2, student.getAge());  
	        query.setString(3, student.getSex());   
	        query.setString(4, student.getStudentid());
	        return (query.executeUpdate() > 0); 
	}
	
	 * 删除一个学生
	 
	public boolean delStudent(String studentid){
		boolean count=false;
		Student student = (Student) sessionFactory.getCurrentSession().get(Student.class, studentid);
		if(student !=null){
			sessionFactory.getCurrentSession().delete(student);
			count =true;
		}
		return count;
	}
    */
}