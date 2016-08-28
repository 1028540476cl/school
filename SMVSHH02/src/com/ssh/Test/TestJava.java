package com.ssh.Test;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ssh.entity.Student;
import com.ssh.entity.Teacher;
public class TestJava{
    public static void main(String[] args) {
        FileSystemXmlApplicationContext ac = 
                new FileSystemXmlApplicationContext("src\\applicationContext.xml");  
       Student student = (Student) ac.getBean("student"); 
        System.out.println(student.getName());
    }
    @Test
    public void testTeacher(){
    	FileSystemXmlApplicationContext ac = 
                new FileSystemXmlApplicationContext("src\\applicationContext.xml"); 
    	Teacher te =  (Teacher) ac.getBean("teacher");
    	System.out.println(te.getName());
    }
    
}