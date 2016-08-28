package com.ssh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssh.dao.CourseDao;
import com.ssh.entity.Course;
import com.ssh.util.Takepage;


@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;
    
   
public CourseDao getCourseDao() {
		return courseDao;
	}
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	/*
	 * 增加课程
	 */
	public void save(Course course) {
		courseDao.save(course);
	}

	/*
	 * 查看全部课程
	 */
	public List<Course> allCourse(String coursetype) {
		return courseDao.allCourse(coursetype);
	}
	/*
	 *  分页查看全部课程
	 */
	public Takepage<Course> allpageCourse(String coursetype,Takepage page) {
		return courseDao.allpageCourse(coursetype,page);
	}
	/*
	 * 查看已经选了的课程
	 */
	public List<Course> findCourse(int id) {
		return courseDao.findCourse(id);
	}
	
	/*
	 * 分页查看已经选了的课程
	 */
	public Takepage<Course> findpageCourse(int id,Takepage page) {
		return courseDao.findpageCourse(id,page);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Course oneCourse(int id) {
		return courseDao.oneCourse(id);
	}

	/*
	 * 修改一个课程的信息
	 */
	public void updateCourse(Course course) {
		 courseDao.updateCourse(course);
	}

	/*
	 * 删除一个课程
	 */
	public void delCourse(Integer id) {
		courseDao.deleteCourse(id);
	}

}