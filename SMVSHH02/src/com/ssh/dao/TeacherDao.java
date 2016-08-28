package com.ssh.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssh.entity.Teacher;
@Repository
public class TeacherDao {
	@Autowired
    private SessionFactory sessionFactory;
	 /*
	  * 增加老师
	  */
	public void saveTeacher(Teacher teacher) {
		sessionFactory.getCurrentSession().save(teacher);  
   }
	/*
	 * 查看全部老师
	 */
	public List allTeacher(){
		String hql="from Teacher";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	/*
	 * 查找一个老师按hql数据查找
	 * ;//get跟load的区别1）  get采用立即加载方式，而load采用延迟加载
	 *					2）get立即向数据库发送查询请求，而load是向数据库发送一个代理，当使用到的时候才发送请求
	 *					3）如果数据库没有对应的记录，get返回null，而load返回ObjectNotFoundException；
	
	public Teacher oneTeacher(int id){
		
		String hql = "from Teacher  where id=?";  
		 Query query = sessionFactory.getCurrentSession().createQuery(hql);  
		  query.setInteger(0, id);
		  return (Teacher)query.uniqueResult();

	}
	
	 * 修改一个老师的信息
	 
	public boolean updateStudent(Teacher teacher){
		
		 String hql = "update Teacher t set t.name=?,t.job=?,t.classs=?,t.picture=? where t.id=? ";  
	        Query query = sessionFactory.getCurrentSession().createQuery(hql);  
	        query.setString(0, teacher.getName());  
	        query.setString(1, teacher.getJob());  
	        query.setString(2,teacher.getClasss());  
	        query.setString(3, teacher.getPicture());   
	        query.setInteger(4,teacher.getId());
	        return (query.executeUpdate() > 0); 
	}
	
	 * 删除一个老师
	 
	public boolean delTeacher(Integer id){
		String hql="delete Teacher where id ="+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (query.executeUpdate()>0);
	}
   */
	
}
