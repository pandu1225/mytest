package com.phoenix.wss.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.phoenix.frmwrk.exception.PHXException;
import com.phoenix.wss.vo.EmployeeVO;


/**
 * <p>Title: mPower - Framework</p>
 * <p>Description: Template for middle-tier code</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Phoenix IT Solutions Ltd.</p>
 * @author Murali Krishna.B
 * @version 3.2
 */
public interface EmployeeService {
    public void saveEmployee(EmployeeVO EmployeeVO) throws PHXException;
    public void saveEmployee(EmployeeVO[] EmployeeVO) throws PHXException;
    public void updateEmployee(EmployeeVO EmployeeVO) throws PHXException;
    public void deleteEmployee(String id) throws PHXException;
    public void inactiveEmployee(String id) throws PHXException;
    public List<EmployeeVO> findAll() throws PHXException;
    public EmployeeVO findById(String id) throws PHXException;
    public List<EmployeeVO> findByWhere(String where) throws PHXException; 
}
