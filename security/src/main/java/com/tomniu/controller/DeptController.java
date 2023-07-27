package com.tomniu.controller;  
  
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.tomniu.model.Department;
import com.tomniu.model.DeptJson;
import com.tomniu.model.DeptRepository;
  
/** 
 * <p>User: Tom Niu
 * <p>Version: 1.0 
 */  

@RestController
public class DeptController {
	
	@Autowired
	private DeptRepository deptRepo;
	//-------------------Retrieve All Departments------------------------------
	@RequestMapping(value="/dept", method = RequestMethod.GET)
    public ResponseEntity<List<DeptJson>> listAllDepts() {
        System.out.println("Hello");
		Iterable<Department> depts = deptRepo.findAll();
		List<DeptJson> deptJsons = new ArrayList<DeptJson>();
        if(depts==null){
            return new ResponseEntity<List<DeptJson>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        } else {
        	for (Department dept: depts) {
        		DeptJson deptJson = new DeptJson(dept, dept.getEmps());
        		deptJsons.add(deptJson);
        	}
        	return new ResponseEntity<List<DeptJson>>(deptJsons, HttpStatus.OK);
        }
    }
	//-------------------Retrieve One Departments by Dept Id-------------------  
    @RequestMapping(value = "/dept/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeptJson> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);

    	Department dept = deptRepo.findOne(id);   	
        if (dept == null) {
            System.out.println("dept with id " + id + " not found");
            return new ResponseEntity<DeptJson>(HttpStatus.NOT_FOUND);
        } else {
	        System.out.println(dept.getDeptName());
	        System.out.println(dept.getEmps().size());
	        DeptJson deptInfo = new DeptJson(dept, dept.getEmps());
	        return new ResponseEntity<DeptJson>(deptInfo, HttpStatus.OK);
        }
    }
	//-------------------Create new Department---------------------------------   
    @RequestMapping(value = "/dept", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody DeptJson deptJson,    UriComponentsBuilder ucBuilder) {

        Department dept = deptJson.getDept();
        System.out.println("Creating Dept " + deptJson.getDept());
        dept = deptRepo.save(dept);
        System.out.println("saved Dept " + dept);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dept/{id}").buildAndExpand(dept.getDeptId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
} 
