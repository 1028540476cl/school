package com.ssh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssh.entity.Course;
import com.ssh.entity.Student;
import com.ssh.service.CourseService;
import com.ssh.util.Takepage;


@Controller
@RequestMapping("course")
public class CourseController {
	
	@Autowired
	//@Resource(name="courseService")
    private CourseService courseService;
	/**
	 * 选课
	 * @param request
	 * @param va
	 * @param course
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="save")
    public String save(HttpServletRequest request,String va,Course course) throws Exception{
		int id = Integer.parseInt(va);
		course =  courseService.oneCourse(id);
		String coursetype = null;
		if(course.getCoursetype()==null||course.getCoursetype()==""){
			 coursetype="7";
		}else{
			 coursetype = course.getCoursetype();
		}
		if( course.getCoursesy()<=0){
			 request.setAttribute("message", "该课程已经选满了，不可以再选！！");
			 return allcourse(request,coursetype);
		}
		Student student = (Student) request.getSession().getAttribute("student");
		int id1 = student.getId();
		List<Course> findlist = courseService.findCourse(id1);
		for (Course course2 : findlist) {
			if(course2.getCoursename().equals(course.getCoursename())&&course2.getCoursetime().equals(course.getCoursetime())){
				 request.setAttribute("message", "该课程你已经选过，不能重复选！！");
				 
				 return allcourse(request,coursetype);
			}
		}
		
		course.setCoursesy(course.getCoursesy()-1);
		course.setCoursepeople(course.getCoursepeople()+1);
		courseService.updateCourse(course);
		course.setType(course.getCoursetype());
		course.setCoursetype(null);
		Student s =(Student) request.getSession().getAttribute("student");
		course.getStudents().add(s);
		courseService.save(course);
    		 request.setAttribute("message", "添加信息成功");
    		 return allcourse(request,coursetype);
    	}
	/**
	 * 查看全部课程
	 * @param request
	 * @param coursetype
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params="all")
	public String allcourse(HttpServletRequest request,String coursetype) {
		String pageNo1 = request.getParameter("pageNo");
		if(pageNo1==null||pageNo1==""){
			pageNo1="1";
		}
		int pageNo = Integer.parseInt(pageNo1);
		Takepage page = new Takepage<Course>();
		page.setPageNo(pageNo);
		page.setPageSize(2);
		if(coursetype==null||coursetype==""){
			 return "html";
		}else{
			if("7".equals(coursetype)){//查找已经选了的课程
				Student student = (Student) request.getSession().getAttribute("student");
				int id = student.getId();
				 page  = courseService.findpageCourse(id,page);
			}else{//查找其他全部课程
				page = courseService.allpageCourse(coursetype,page);
			}
	    	request.setAttribute("page", page);
	    	request.setAttribute("pagetype", coursetype);
	        int type=Integer.parseInt(coursetype);
			if(type==1){
				coursetype = "体育任选[体育]";
			}if(type==2){
				coursetype = "必修";
			}if(type==3){
				coursetype = "专选";
			}if(type==4){
				coursetype = "必修分组";
			}if(type==5){
				coursetype = "专选分组";
			}if(type==6){
				coursetype = "任选[公选]";
			}if(type==7){
				coursetype = "已选课程";
			}
			request.setAttribute("coursetype", coursetype);
	        return "courselist";
		}
	    }
	/**
	 * 查看具体的课程
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params="one")
	public String oneCourse(@RequestParam("id")Integer id,HttpServletRequest request,ModelMap model){
		Course course = courseService.oneCourse(id);
		model.put("course", course);
		return "course-add";
	}
	/**
	 * ajax查找选了重复的课程
	 * @throws IOException 
	 */
	@RequestMapping(params="chongfu")
	public void oneCourse(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String va = request.getParameter("type");
		response.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(va);
		if (id==0){
			return ;
		}
		boolean flage = false;
		Course course =  courseService.oneCourse(id);
		Student student = (Student) request.getSession().getAttribute("student");
		int id1 = student.getId();
		List<Course> findlist = courseService.findCourse(id1);
		for (Course course2 : findlist) {
			if(course2.getCoursename().equals(course.getCoursename())&&course2.getCoursetime().equals(course.getCoursetime())){
				 PrintWriter out = response.getWriter();
				 out.write("该课程已选过");
				 flage = true;
				 break;
			}
		if(course2.getCoursename().equals(course.getCoursename())==false){
			flage = false;
		}
	}
		if(!flage){
			PrintWriter out = response.getWriter();
			 out.write("该课程未选过");
		}
	}
	@RequestMapping(params="saveout")
	public String oneCourse(HttpServletRequest request,String va){
		int id = Integer.parseInt(va);
		Course course =  courseService.oneCourse(id);
		String coursetype = course.getCoursetype();
		if(course.getType()==null||course.getType()==""){
			request.setAttribute("message", "你未选择该课程，不需要退课！！");
			 return allcourse(request,coursetype);
		}
		int courseid = 0;
		if(coursetype==null||coursetype==""){
				coursetype="7";
				String type = course.getType();
				List<Course> courselist = courseService.allCourse(type);
				for (Course course2 : courselist) {
					if(course2.getCoursename().equals(course.getCoursename())&&course2.getCoursetime().equals(course.getCoursetime())){
						 courseid = course2.getId();
						 break;
					}
				}
				courseService.delCourse(id);
				course = courseService.oneCourse(courseid);
				course.setCoursesy(course.getCoursesy()+1);
				course.setCoursepeople(course.getCoursepeople()-1);
				courseService.updateCourse(course);
				request.setAttribute("message", "课程退选成功");
				 return allcourse(request,coursetype);
			
		}else{
			
			Student student = (Student) request.getSession().getAttribute("student");
			int id1 = student.getId();
			List<Course> findlist = courseService.findCourse(id1);
			for (Course course2 : findlist) {
				if(course2.getCoursename().equals(course.getCoursename())&&course2.getCoursetime().equals(course.getCoursetime())){
					 courseid = course2.getId();
					 break;
				}
			}
			course.setCoursesy(course.getCoursesy()+1);
			course.setCoursepeople(course.getCoursepeople()-1);
			courseService.updateCourse(course);
			courseService.delCourse(courseid);
			request.setAttribute("message", "课程退选成功");
			 return allcourse(request,coursetype);
		}
		
	}
/*	@RequestMapping(params="update")
	public String updateCourse(HttpServletRequest request,ModelMap model) throws Exception{
		Course course = new Course();
		String name=new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		
		course.setCoursename(name);
		
		boolean count = courseService.updateCourse(course);
		if(count){
			model.put("message", "更新信息成功");
			return allcourse( request, model);
		}else{
			model.put("message", "更新信息失败");
			return "error";
		}
	}
	
	@RequestMapping(params="del")
	public String delCourse(@RequestParam("id")Integer id,HttpServletRequest request,ModelMap model){
		
		boolean count = courseService.delCourse(id);
		if(count){
			model.put("message", "删除信息成功");
			return allcourse(request, model);
		}else{
			model.put("message", "删除太频繁，失败，请重试");
			return "error";
		}
	}*/
    
}