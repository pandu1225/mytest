package com.phoenix.wss.vo;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import com.phoenix.frmwrk.base.ObjectVO;


/**
 * @author Kesava.B & Pandu.G
 */

@Entity
@Table(name = "EMPLOYEE_MASTER")
@Where(clause = "record_status = 1 ")

public class EmployeeVO extends ObjectVO {
//    private int NULL_VALUE = 0;


	private String FirstName = null;
    private String LastName = null;
    private String Email = null;
    private String PhoneNumber = null;
    private String DOJ = null;
    private String Salary = null;

   
    private String Employee_ID = null;
    public String getEmployee_ID() {
		return Employee_ID;
	}
	public void setEmployee_ID(String employee_ID) {
		Employee_ID = employee_ID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getDOJ() {
		return DOJ;
	}
	public void setDOJ(String dOJ) {
		DOJ = dOJ;
	}
	public String getSalary() {
		return Salary;
	}
	public void setSalary(String salary) {
		Salary = salary;
	}
	
    public EmployeeVO()
    {
 	super();
    }
    @Override
	public String toString() {
		return "EmployeeVO [FirstName=" + FirstName + ", LastName=" + LastName + ", Email=" + Email + ", PhoneNumber="
				+ PhoneNumber + ", DOJ=" + DOJ + ", Salary=" + Salary + ", Employee_ID=" + Employee_ID + "]";
	}
	public void init() {	 
           super.init();
    }





}
