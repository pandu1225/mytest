package com.phoenix.wss.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.EncDecFluentgrid;
import com.phoenix.common.service.SMSConfigService;
import com.phoenix.common.vo.ConsumerMasterVO;
import com.phoenix.frmwrk.exception.PHXException;
import com.phoenix.sendmail.SendEmail;
import com.phoenix.wss.service.CmtuserMService;
//import com.phoenix.wss.service.CustomerMobileAppService;
import com.phoenix.wss.service.JPAQueryService;
import com.phoenix.wss.service.MobileAppUserLoginService;
import com.phoenix.wss.service.MulAccountTService;
import com.phoenix.wss.service.OtpTService;
import com.phoenix.wss.service.UpdateProfileService;
import com.phoenix.wss.service.WssMobileAppUserLoginService;
//import com.phoenix.wss.util.EncryptionDecryptionAES;
//import com.phoenix.wss.util.EncryptionDecryptionAES;
import com.phoenix.wss.util.generateRefNo;
import com.phoenix.wss.vo.CmtuserMVO;
import com.phoenix.wss.vo.EmployeeVO;
import com.phoenix.wss.vo.MobileAppGraphCustVO;
import com.phoenix.wss.vo.MulAccountTVO;
import com.phoenix.wss.vo.OtpTVO;
import com.phoenix.wss.vo.UpdateProfileVO;
import com.phoenix.wss.service.EmployeeService;


@Controller 
public class EmployeeController {
		
	private static final Log log = LogFactory.getLog(EmployeeController.class);
	@Autowired
	private EmployeeService EmployeeService;

	
	
	    
	    
	    
	 //   employee code, first name, last name, yearly salary, tax amount, cess amount.
	    
	    @RequestMapping("StoreEmployee.htm")
		 public @ResponseBody String StoreEmployee(HttpServletResponse response,HttpServletRequest request,HttpSession session,@WebParam(name = "userid") String msg) throws JSONException {
			 response.setContentType("application/json");
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
				response.setHeader("Access-Control-Max-Age", "3600");
				response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	      
	      
	      JSONArray jsonarr = new JSONArray();
		  JSONObject objjson = new JSONObject();
		  JSONObject jsonObject=null;
		  String Employee_ID="",FirstName="",LastName="",Email="",PhoneNumber="",DOJ="",Salary="";
		  
		  String userid123=request.getParameter("msg").replaceAll(" ", "+");
			//String userid123="{\"SYNCOUT_DETAILS\":[{\"DIV_CODE\":\"BH\",\"GROUP\":\"4\",\"BOOK\":\"2510\",\"SCNO\":\"336167\",\"NAME\":\"SATENDRA SINGH\",\"FATHERNAME\":\"DARYAB SINGH\",\"ADDRESS1\":\"0VILL- BHAKAWA\",\"ADDRESS2\":\"BAHERIBAREILLY\",\"SUPPLY_TYPE\":\"11\",\"PHASE\":\"1\",\"CONTRACTEDLOAD\":\"1\",\"CONNECTEDLOAD\":\"1\",\"LOADUNIT\":\"KW\",\"SUPPLY_RELEASE_DATE\":\"07/23/2019\",\"SD_AMOUNT\":\"150\",\"NO_OF_LOOMS_LS60\":\"0\",\"NO_OF_LOOMS_GT60\":\"0\",\"GOVT_FLAG\":\"NG\",\"MOBILE_NUMBER\":\"9760003637\",\"VILLAGE_CODE\":\"129313\",\"HABITATION_CODE\":\"1120265\",\"METER_NO\":\"206855\",\"MF\":\"1\",\"OPENREADING\":\"0\",\"OPEN_RDG_DATE\":\"08/01/2019\",\"METER_SEALING_DOC_NUMBER\":\"000046239\",\"METER_MAKE\":\"GENUS\",\"METER_OWNED_BY\":\"Department\",\"IS_SAUBHAGYA_SCHEME\":\"N\",\"SCHEME_TYPE\":\"N\",\"METER_INSTALLATION_AGENCY\":\"OTR\",\"Division_Name\":\"EDSD-I BAHERI\"}]}";
			try{
				 jsonObject = new JSONObject(userid123);
				 JSONArray jsonArray = jsonObject.getJSONArray("SYNCOUT_DETAILS");

					JSONObject innerObj2 = null;  
					innerObj2=jsonArray.getJSONObject(0);
					Employee_ID=innerObj2.get("Employee_ID").toString();
					FirstName=innerObj2.get("FirstName").toString();
					LastName=innerObj2.get("LastName").toString();
					Email=innerObj2.get("Email").toString();
					PhoneNumber=innerObj2.get("PhoneNumber").toString();
					DOJ=innerObj2.get("DOJ").toString();
					Salary=innerObj2.get("Salary").toString();
					EmployeeVO employeeVO=new EmployeeVO();
					
					employeeVO.setEmployee_ID(Employee_ID);
					employeeVO.setFirstName(FirstName);
					employeeVO.setLastName(LastName);
					employeeVO.setEmail(Email);
					employeeVO.setPhoneNumber(PhoneNumber);
					employeeVO.setDOJ(DOJ);
					employeeVO.setPhoneNumber(PhoneNumber);
					employeeVO.setSalary(Salary);
					EmployeeService.saveEmployee(employeeVO);
					
						objjson.put("STATUS","Success");
						jsonarr.put(objjson);
						log.info("obj"+objjson.toString());
						return jsonarr.toString();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			objjson.put("STATUS","Exception occured please try again");
			log.info("obj"+objjson.toString());
			jsonarr.put(objjson);
			return jsonarr.toString();
		  
		  
		 }
	    
	    @RequestMapping("SendEmployee.htm")
		 public @ResponseBody String SendEmployee(HttpServletResponse response,HttpServletRequest request,HttpSession session,@WebParam(name = "userid") String msg) throws JSONException {
			 response.setContentType("application/json");
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
				response.setHeader("Access-Control-Max-Age", "3600");
				response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	      
	      
	      JSONArray jsonarr = new JSONArray();
		  JSONObject objjson = new JSONObject();
		  JSONObject jsonObject=null;
		  String Employee_ID="",FirstName="",LastName="",Email="",PhoneNumber="",DOJ="",Salary="";
		  
		  //String userid123=request.getParameter("msg").replaceAll(" ", "+");
			//String userid123="{\"SYNCOUT_DETAILS\":[{\"DIV_CODE\":\"BH\",\"GROUP\":\"4\",\"BOOK\":\"2510\",\"SCNO\":\"336167\",\"NAME\":\"SATENDRA SINGH\",\"FATHERNAME\":\"DARYAB SINGH\",\"ADDRESS1\":\"0VILL- BHAKAWA\",\"ADDRESS2\":\"BAHERIBAREILLY\",\"SUPPLY_TYPE\":\"11\",\"PHASE\":\"1\",\"CONTRACTEDLOAD\":\"1\",\"CONNECTEDLOAD\":\"1\",\"LOADUNIT\":\"KW\",\"SUPPLY_RELEASE_DATE\":\"07/23/2019\",\"SD_AMOUNT\":\"150\",\"NO_OF_LOOMS_LS60\":\"0\",\"NO_OF_LOOMS_GT60\":\"0\",\"GOVT_FLAG\":\"NG\",\"MOBILE_NUMBER\":\"9760003637\",\"VILLAGE_CODE\":\"129313\",\"HABITATION_CODE\":\"1120265\",\"METER_NO\":\"206855\",\"MF\":\"1\",\"OPENREADING\":\"0\",\"OPEN_RDG_DATE\":\"08/01/2019\",\"METER_SEALING_DOC_NUMBER\":\"000046239\",\"METER_MAKE\":\"GENUS\",\"METER_OWNED_BY\":\"Department\",\"IS_SAUBHAGYA_SCHEME\":\"N\",\"SCHEME_TYPE\":\"N\",\"METER_INSTALLATION_AGENCY\":\"OTR\",\"Division_Name\":\"EDSD-I BAHERI\"}]}";
			try{
				 
				List<EmployeeVO> lst=EmployeeService.findAll();
					
				for(int i=0;i<lst.size();i++)
				{
					objjson = new JSONObject();
					EmployeeVO employeeVO=lst.get(0);
					objjson.put("employee_code",employeeVO.getEmployee_ID());
					objjson.put("first_name",employeeVO.getFirstName());
					objjson.put("last_name",employeeVO.getLastName());
					objjson.put("yearly_salary",""+(Integer.parseInt(employeeVO.getSalary())*12));
					int sal=Integer.parseInt(employeeVO.getSalary());
					double tax=0d;
					SimpleDateFormat sdf=new SimpleDateFormat("DD-MM-YYYY");
					
					
					Calendar doj = new GregorianCalendar();
					doj.setTime(sdf.parse(employeeVO.getDOJ()));
			        Calendar today = new GregorianCalendar();
			        today.setTime(new Date());
			        int monthsDiff = today.get(Calendar.MONTH) 
                            - doj.get(Calendar.MONTH);
			        int totsal=sal*monthsDiff;
					if(totsal<250000)
					{
						tax=totsal*0.05;
					}
					else if(totsal<500000)
					{
						tax=totsal*0.1;
					}
					else if(totsal<1000000)
						tax=totsal*0.2;
					double cess=0;
					if(totsal>250000)
					{
						cess=(totsal-250000)*0.02;
					}
					objjson.put("tax_amount",tax);
					objjson.put("cess_amount",cess);
					jsonarr.put(objjson);
				}
				
						
						log.info("obj"+objjson.toString());
						return jsonarr.toString();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			objjson.put("STATUS","Exception occured please try again");
			log.info("obj"+objjson.toString());
			jsonarr.put(objjson);
			return jsonarr.toString();
		  
		  
		 }
	 
    }

