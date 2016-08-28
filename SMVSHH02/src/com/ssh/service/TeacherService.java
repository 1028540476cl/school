package com.ssh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssh.dao.TeacherDao;
import com.ssh.entity.Teacher;


@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;
    
    
    public TeacherDao getTeacherDao() {
		return teacherDao;
	}
	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	
	
	public void saveTeacher(Teacher teacher){
		teacherDao.saveTeacher(teacher);  
   }
	
	public List allTeacher(){
		return teacherDao.allTeacher();
	}
	
	/*public Teacher oneTeacher(int id){
		return teacherDao.oneTeacher(id);

	}
	public boolean updateStudent(Teacher teacher){
		return teacherDao.updateStudent(teacher);
	}
	
	 * 删除一个老师
	 
	public boolean delTeacher(Integer id){
		return teacherDao.delTeacher(id);
	}*/
   
}