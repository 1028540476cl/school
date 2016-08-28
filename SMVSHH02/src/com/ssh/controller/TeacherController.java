package com.ssh.controller;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssh.entity.Student;
import com.ssh.entity.Teacher;
import com.ssh.service.StudentService;
import com.ssh.service.TeacherService;


@Controller
@RequestMapping("teacher")
public class TeacherController {
	
	@Autowired
    private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@RequestMapping(params="tostudent")
    public void sTLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List teacherlist =teacherService.allTeacher();
        request.getSession().setAttribute("teacherlist", teacherlist);
        response.sendRedirect("student.do?to");
    }
	@RequestMapping(params="to")
    public String toLogin(HttpServletRequest request,ModelMap  model){
		model.put("teacher", null);
        return "teacher-add";
    }
	
	/*@RequestMapping(params="save")
    public String save(HttpServletRequest request,ModelMap  model) throws Exception{
		Teacher teacher = new Teacher();
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		String job = new String(request.getParameter("job").getBytes("ISO-8859-1"),"utf-8");
		String classs = new String(request.getParameter("classs").getBytes("ISO-8859-1"),"utf-8");
		String file =  new String(request.getParameter("picture").getBytes("ISO-8859-1"),"utf-8");
		teacher.setClasss(classs);
		teacher.setJob(job);
		teacher.setName(name);
		teacher.setPicture(file);
		teacherService.saveTeacher(teacher);
		model.put("message", "添加信息成功");
		return allTeacher( request,model); 
    	}
	@RequestMapping(params="all")
	public String allTeacher(HttpServletRequest request,ModelMap  model){
	    	List teacherlist =teacherService.allTeacher();
	        model.put("teacherlist", teacherlist);
	        
	        return "teacher";
	    }
	@RequestMapping(params="one")
	public String oneTeacher(@RequestParam("id")Integer id,HttpServletRequest request,ModelMap model){
		Teacher teacher = teacherService.oneTeacher(id);
		model.put("teacher", teacher);
		return "teacher-add";
	}
	@RequestMapping(params="update")
	public String updateStudent(HttpServletRequest request,ModelMap model) throws Exception{
		Teacher teacher = new Teacher();
		String id =request.getParameter("id");
		int id1 = Integer.parseInt(id);
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		String job = new String(request.getParameter("job").getBytes("ISO-8859-1"),"utf-8");
		String classs = new String(request.getParameter("classs").getBytes("ISO-8859-1"),"utf-8");
		String file =  new String(request.getParameter("picture").getBytes("ISO-8859-1"),"utf-8");
		teacher.setClasss(classs);
		teacher.setJob(job);
		teacher.setName(name);
		teacher.setPicture(file);
		teacher.setId(id1);
		boolean count = teacherService.updateStudent(teacher);
		if(count){
			model.put("message", "更新信息成功");
			return allTeacher( request, model);
		}else{
			model.put("message", "更新信息失败");
			return "error";
		}
	}
	
	@RequestMapping(params="del")
	public String delTeacher(@RequestParam("id")Integer id,HttpServletRequest request,ModelMap model){
	Teacher	teacher = teacherService.oneTeacher(id);
	
	Set<Student> student = teacher.getStudents();
	for (Student student2 : student) {
		studentService.delStudent(student2.getStudentid());
	}
		boolean count = teacherService.delTeacher(id);
		if(count){
			model.put("message", "删除信息成功");
			return allTeacher(request, model);
		}else{
			model.put("message", "删除太频繁，失败，请重试");
			return "error";
		}
	}*/
    
}