package com.phoenix.wss.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;
import javax.annotation.Resource;
import com.phoenix.frmwrk.exception.PHXException;

import com.phoenix.wss.vo.EmployeeVO;
import com.phoenix.wss.dao.EmployeeDAO;


/**
 * <p>Title: mPower - Framework</p>
 * <p>Description: Template for middle-tier code</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Phoenix IT Solutions Ltd.</p>
 * @author Murali Krishna.B
 * @version 3.2
 */


@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO EmployeeDAO;

    /**
     * This method is used to insert the new data into Corrosponding Table.
     * @param EmployeeVO A ObjectVO containing the Information about the Corrosponding VO Object.
     * @exception Exception on error (if any).
     */

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveEmployee(EmployeeVO EmployeeVO)  throws PHXException {
        EmployeeDAO.save(EmployeeVO); 
    }

    /**
     * This method is used to insert the bulk data into Corrosponding Table.
     * @param EmployeeVOArr  containing the Information about the Corrosponding VO array Object.
     * @exception Exception on error (if any).
     */

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveEmployee(EmployeeVO[] EmployeeVOArr) throws PHXException {
        EmployeeDAO.save(EmployeeVOArr);
    }

    /**
     * This method is used to Update the new data into Corrosponding Table.
     * @param EmployeeVO A ObjectVO containing the Information about the Corrosponding new VO Object.
     * @exception Exception on error (if any).
     */
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void updateEmployee(EmployeeVO EmployeeVO) throws PHXException {
        EmployeeDAO.update(EmployeeVO);
    }

    /**
     * This method is used to remove the data from Corrosponding Table.
     * @param id a String Containing the value of id.
     * @exception Exception on error (if any).
     */		
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteEmployee(String id)  throws PHXException {
        EmployeeDAO.delete(id);
    }
	 
    /**
     * This method is used to change the Record Status of particlur record in the Corrosponding Table.
     * @param id a String Containing the value of id.
     * @exception Exception on error (if any).
     */

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void inactiveEmployee(String id)  throws PHXException {
        EmployeeDAO.inactive(id);
    }

    /**
     * This method is used to get the All data from Corrosponding Table.
     * @return EmployeeVO A Array of ObjectVO Containing the Information about the Corrosponding Object VO Array.
     * @exception Exception on error (if any).
     */

    @Transactional(readOnly = true)
    public List<EmployeeVO> findAll() throws PHXException {
        return EmployeeDAO.findAll();
    }

    /**
     * This method is used to get the All data based on the where clause from Corrosponding Table .
     * @param strings A String containting where clause .
     * @return EmployeeVO A Array of ObjectVO Containing the Information about the Corrosponding Object VO Array.
     * @exception Exception on error (if any).
     */
    @Transactional(readOnly = true)
    public List<EmployeeVO> findByWhere(String where) throws PHXException {
        return EmployeeDAO.findByWhere(where);
    } 

    /**
     * This method is used to get the All data from Corrosponding Table.
     * @param id a String Containing the primary key.
     * @return EmployeeVO A ObjectVO Containing the Information about the Corrosponding Object VO.
     * @exception Exception on error (if any).
     */
    @Transactional(readOnly = true)
    public EmployeeVO findById(String id) throws PHXException {
        return EmployeeDAO.findById(id);
    } 

    public void setEmployeeDAO(EmployeeDAO EmployeeDAO) {
        this.EmployeeDAO = EmployeeDAO;
    }
}
