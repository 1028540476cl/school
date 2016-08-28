package com.ssh.controller;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssh.entity.Student;
import com.ssh.entity.Teacher;
import com.ssh.service.StudentService;


@Controller
@RequestMapping("student")
public class StudentController {
	
	@Autowired
    private StudentService studentService;
	/*
	 * 跳转到登陆界面
	 */
	@RequestMapping(params="tologin")
    public String Login(HttpServletRequest request,HttpServletResponse response,ModelMap  model) throws Exception{
		return "index";
	}
	/*
	 * 退出登录
	 */
	@RequestMapping(params="loginout")
    public String LoginOut(HttpServletRequest request,HttpServletResponse response,ModelMap  model) throws Exception{
		request.getSession().removeAttribute("student");
		return "index";
	}
	/*
	 * 登陆
	 */
	@RequestMapping(params="to")
    public String toLogin(HttpServletRequest request,HttpServletResponse response,ModelMap  model) throws Exception{
		String studentid=request.getParameter("studentid");
		String psw = request.getParameter("password");
		if(studentid==null||studentid==""||psw==""||psw==null){
			return Login(request,response,model);
		}
		String check = request.getParameter("check");
		if("y".equals(check)){
			Cookie nameCookie = new Cookie("username", URLEncoder.encode(studentid, "UTF-8"));
			// nameCookie=URLEncoder.encode(nameCookie,"UTF-8");
			// 设置Cookie的有效期为1天
			nameCookie.setMaxAge(60 * 60 * 24);
			Cookie pwdCookie = new Cookie("password", URLEncoder.encode(psw, "UTF-8"));
			pwdCookie.setMaxAge(60 * 60*24);
			// 设置Cookie的有效期为1天
			response.addCookie(nameCookie);
			response.addCookie(pwdCookie);
		}
		Student student = studentService.oneStudent(studentid,psw);
		if(student==null){
			request.setAttribute("message", "用户名或者密码错误");
			//request.setAttribute("totalRe",0);
			return "error";
		}
		request.getSession().setAttribute("student", student);
        return "check";
		}
	/*
	 *找出该学生已选的课程 
	 */
	@RequestMapping(params="course")
	public String checkCoure(HttpServletRequest request,Student student)throws Exception{
		student = (Student) request.getSession().getAttribute("student");
		int id = student.getId();
		List<Student> list = studentService.findCourse(id);
		request.setAttribute("courselist", list);
		request.setAttribute("coursetype", "已选课程");
		return "courselist";
	}
	@RequestMapping(params="save")
    public String save(HttpServletRequest request,ModelMap  model) throws Exception{
		
		return "add";
    	}
	@RequestMapping(params="all")
	public String allStudent(HttpServletRequest request,ModelMap  model){
	    	List<Student> studentlist =studentService.allStudent();
	        model.put("studentlist", studentlist);/*
	        for (Student student : studentlist) {
				System.out.println(student);
				 Set<Course> course= student.getCourses();
			       Iterator<Course> it = course.iterator();
			       while(it.hasNext()){
			    	   Course course1 = it.next();
			    	   System.out.println(course1);
			       }
			}
	      */
	       
	        return "check";
	    }
/*	@RequestMapping(params="one")
	public String oneStudnet(String studentid,HttpServletRequest request,ModelMap model){
		Student student = studentService.oneStudent(studentid);
		model.put("student", student);
		return "student-add";
	}
	@RequestMapping(params="update")
	public String updateStudent(HttpServletRequest request,ModelMap model) throws Exception{
		Student student = new Student();
		String name=new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		String psw = request.getParameter("psw");
		String age1 = request.getParameter("age");
		int age = Integer.parseInt(age1);
		String id1 = request.getParameter("id");
		int id = Integer.parseInt(id1);
		String sex=new String(request.getParameter("sex").getBytes("ISO-8859-1"),"utf-8");
		student.setAge(age);
		student.setName(name);
		student.setPsw(psw);
		student.setId(id);
		student.setSex(sex);
		boolean count = studentService.updateStudent(student);
		if(count){
			model.put("message", "更新信息成功");
			return allStudent( request, model);
		}else{
			model.put("message", "更新信息失败");
			return "error";
		}
	}
	
	@RequestMapping(params="del")
	public String delStudent(String studentid,HttpServletRequest request,ModelMap model){
		
		boolean count = studentService.delStudent(studentid);
		if(count){
			model.put("message", "删除信息成功");
			return allStudent(request, model);
		}else{
			model.put("message", "删除太频繁，失败，请重试");
			return "error";
		}
	}
    */
}