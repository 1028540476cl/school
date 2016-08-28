package com.ssh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssh.dao.StudentDao;
import com.ssh.entity.Student;


@Service
public class StudentService {

    @Autowired
    private StudentDao studentDAO;
    
    
    public StudentDao getStudentDAO() {
		return studentDAO;
	}


	public void setStudentDAO(StudentDao studentDAO) {
		this.studentDAO = studentDAO;
	}

/*
 * 增加学生
 */
	public void save(Student student){
      studentDAO.save(student);
        
    }
	/*
	 * 查看全部学生
	 */
	public List allStudent(){
		return studentDAO.allStudent();
	}
	/*
	 * 查看一个学生
	 */
	public Student oneStudent(String studentid,String password){
		return studentDAO.oneStudent(studentid,password);
	}
	/*
	 * 查看选了什么课程
	 */
	public List findCourse(int id){
		return studentDAO.findCourse(id);
	}
   /* 
     * 更新一个学生信息
     
	public boolean updateStudent(Student student){
		return studentDAO.updateStudent(student);
	}
	
	 * 删除一个学生的信息
	 
	public boolean delStudent(String studentid){
		return studentDAO.delStudent(studentid);
	}*/
}