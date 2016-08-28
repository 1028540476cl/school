package com.ssh.entity;

import java.util.Set;

/**
 * @author CL
 *
 */
public class Course {
	private int id;         //课程id
	private String coursename;		//课程名字
	private String courseclass;
	private String coursetime;
	private Float coursebord;
	private Integer coursecount;
	private Integer coursesy;
	private Integer coursepeople;
	private String cotein;
	private String coursetype;
	private String type;
	private Teacher teacher;//一门课程由一个老师上
	private Set<Student> students;//一门课程有多个学生学
	
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(int id, String coursename, String courseclass,
			String coursetime, Float coursebord, Integer coursecount,
			Integer coursesy, Integer coursepeople, String cotein,
			String coursetype,String type, Teacher teacher, Set<Student> students) {
		super();
		this.id = id;
		this.coursename = coursename;
		this.courseclass = courseclass;
		this.coursetime = coursetime;
		this.coursebord = coursebord;
		this.coursecount = coursecount;
		this.coursesy = coursesy;
		this.coursepeople = coursepeople;
		this.cotein = cotein;
		this.coursetype = coursetype;
		this.type = type;
		this.teacher = teacher;
		this.students = students;
	}
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCourseclass() {
		return courseclass;
	}

	public void setCourseclass(String courseclass) {
		this.courseclass = courseclass;
	}

	public String getCoursetime() {
		return coursetime;
	}

	public void setCoursetime(String coursetime) {
		this.coursetime = coursetime;
	}

	public Float getCoursebord() {
		return coursebord;
	}

	public void setCoursebord(Float coursebord) {
		this.coursebord = coursebord;
	}

	public Integer getCoursecount() {
		return coursecount;
	}

	public void setCoursecount(Integer coursecount) {
		this.coursecount = coursecount;
	}

	public Integer getCoursesy() {
		return coursesy;
	}

	public void setCoursesy(Integer coursesy) {
		this.coursesy = coursesy;
	}

	public Integer getCoursepeople() {
		return coursepeople;
	}

	public void setCoursepeople(Integer coursepeople) {
		this.coursepeople = coursepeople;
	}

	public String getCotein() {
		return cotein;
	}

	public void setCotein(String cotein) {
		this.cotein = cotein;
	}

	public String getCoursetype() {
		return coursetype;
	}

	public void setCoursetype(String coursetype) {
		this.coursetype = coursetype;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", coursename=" + coursename
				+ ", courseclass=" + courseclass + ", coursetime=" + coursetime
				+ ", coursebord=" + coursebord + ", coursecount=" + coursecount
				+ ", coursesy=" + coursesy + ", coursepeople=" + coursepeople
				+ ", cotein=" + cotein + ", coursetype=" + coursetype
				+ ", teacher=" + teacher + ", students=" + students + "]";
	}
	
	
	

}
