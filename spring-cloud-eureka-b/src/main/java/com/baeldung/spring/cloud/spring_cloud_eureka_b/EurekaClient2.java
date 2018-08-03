package com.baeldung.spring.cloud.spring_cloud_eureka_b;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Student;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClient2 {
	public static void main(String[] args) {
		SpringApplication.run(EurekaClient2.class, args);
	}

	@RequestMapping(value = "/greeting")
	public String greeting() {
		return "Hello from EurekaClient!";
	}

	//Returning single students
	@RequestMapping(value = "/studentjson", produces = "application/json")
	public Student getDetailsJson() {
		Student stu1 = new Student(1, "Jatin");
		return stu1;
	}

	@RequestMapping(value = "/studentxml", produces = "application/xml")
	public Student getDetailsXml1() {
		Student stu1 = new Student(1, "Jatin");
		return stu1;
	}

	// by default xml response will come
	@RequestMapping(value = "/student")
	public Student getDetailsXml2() {
		Student stu1 = new Student(1, "Jatin");
		return stu1;
	}

	
	
	
	//Returning List of students
	@RequestMapping(value = "/allstudentjson", produces = "application/json")
	public List<Student> getAllDetailsJson() {
		Student stu1 = new Student(1, "Jatin");
		Student stu2 = new Student(2, "Yatin");
		List<Student> listStudent = new ArrayList<Student>();
		listStudent.add(stu1);
		listStudent.add(stu2);
		
		return listStudent;
	}

	@RequestMapping(value = "/allstudentxml", produces = "application/xml")
	public List<Student> getAllDetailsXml1() {
		Student stu1 = new Student(1, "Jatin");
		Student stu2 = new Student(2, "Yatin");
		List<Student> listStudent = new ArrayList<Student>();
		listStudent.add(stu1);
		listStudent.add(stu2);
		
		return listStudent;
	}

	// by default xml response will come
	@RequestMapping(value = "/allstudent")
	public List<Student> getAllDetailsXml2() {
		Student stu1 = new Student(1, "Jatin");
		Student stu2 = new Student(2, "Yatin");
		List<Student> listStudent = new ArrayList<Student>();
		listStudent.add(stu1);
		listStudent.add(stu2);
		
		return listStudent;
	}

}
