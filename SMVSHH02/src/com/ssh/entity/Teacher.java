package com.ssh.entity;

import java.util.Set;

/**
 * @author CL
 * 
 */
public class Teacher {
	private int id;// 教师的id
	private String teacherid;//教师编号
	private String name;// 教师的姓名
	private String nameclass;// 教师的学院
	private String picture;// 教师的图片
	private Set<Student> students;// 教师教班级的学生
	private Set<Course> courses;//老师教的课程
	
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameclass() {
		return nameclass;
	}
	public void setNameclass(String nameclass) {
		this.nameclass = nameclass;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", teacherid=" + teacherid + ", name="
				+ name + ", nameclass=" + nameclass + ", picture=" + picture
				+ ", students=" + students + ", courses=" + courses + "]";
	}


	
	
}