package com.phoenix.wss.dao;


import java.util.List;
import com.phoenix.frmwrk.exception.PHXDBException;
import com.phoenix.wss.vo.EmployeeVO;


/**
 * <p>Title: mPower - Framework</p>
 * <p>Description: Template for middle-tier code</p>
 * <p>Copyright: Copyright (c) 2009-2010/p>
 * <p>Company: Phoenix IT Solutions Ltd.</p>
 * @author Murali Krishna.B
 * @version 3.2
 */

public interface EmployeeDAO {

    public List<EmployeeVO> findAll() throws PHXDBException;
    public EmployeeVO findById(String id) throws PHXDBException;
    public List<EmployeeVO> findByWhere(String where) throws PHXDBException;
    public void save(EmployeeVO EmployeeVO) throws PHXDBException;
    public void save(EmployeeVO[] EmployeeVO) throws PHXDBException;
    public void update(EmployeeVO EmployeeVO) throws PHXDBException;
    public void inactive(String id) throws PHXDBException;
    public void inactive(EmployeeVO EmployeeVO) throws PHXDBException;
    public void delete(String id) throws PHXDBException;
    public void delete(EmployeeVO EmployeeVO) throws PHXDBException;
    public void refresh(EmployeeVO EmployeeVO) throws PHXDBException;

}
