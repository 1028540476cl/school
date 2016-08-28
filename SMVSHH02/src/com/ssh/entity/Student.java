package com.ssh.entity;

import java.util.Set;

/**
 * @author CL
 *
 */
/**
 * @author CL
 *
 */
public class Student{
	private Integer id;
	private String studentid;
	private String name;
	private String psw;
	private String sex;
	private String studentclass;
	private String date;
	private Set<Teacher> teachers;//上课表中学生选了什么老师的课
	private Set<Course> courses;//学生选修的多门课程
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Student(Integer id, String studentid, String name, String psw,
			String sex, String studentclass, String date,
			Set<Teacher> teachers, Set<Course> courses) {
		super();
		this.id = id;
		this.studentid = studentid;
		this.name = name;
		this.psw = psw;
		this.sex = sex;
		this.studentclass = studentclass;
		this.date = date;
		this.teachers = teachers;
		this.courses = courses;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStudentclass() {
		return studentclass;
	}
	public void setStudentclass(String studentclass) {
		this.studentclass = studentclass;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentid=" + studentid + ", name="
				+ name + ", psw=" + psw + ", sex=" + sex + ", studentclass="
				+ studentclass + ", date=" + date + ", teachers=" + teachers
				+ ", courses=" + courses + "]";
	}
	
	
}
