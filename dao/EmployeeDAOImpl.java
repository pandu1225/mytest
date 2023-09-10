package com.phoenix.wss.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.phoenix.frmwrk.exception.PHXDBException;
import com.phoenix.frmwrk.util.SessionData;
import com.phoenix.wss.service.AuditLogService;
import com.phoenix.wss.service.UserMasterService;
import com.phoenix.wss.vo.AuditLogVO;
import com.phoenix.wss.vo.EmployeeVO;


/**
 * <p>Title: mPower - Framework</p>
 * <p>Description: Template for middle-tier code</p>
 * <p>Copyright: Copyright (c) 2009-2010/p>
 * <p>Company: Phoenix IT Solutions Ltd.</p>
 * @author Murali Krishna.B
 * @version 3.2
 */

@Repository("EmployeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext        
    private EntityManager entityManager;

	@Autowired
	SessionData sessionData;
	
	@Autowired
	AuditLogService auditLogService;
	
	@Autowired
	UserMasterService userMasterService;
	
	AuditLogVO auditLogVO = null;
	
    public void setEntityManager(EntityManager entityManager) throws PHXDBException {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public EmployeeVO findById(String id) {
        return (EmployeeVO) entityManager.find(EmployeeVO.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<EmployeeVO> findByWhere(String where) throws PHXDBException {
        Query query = null;

        if (where != null) {
            query = entityManager.createQuery(
                    "FROM EmployeeVO o WHERE " + where);
        }
        return query.getResultList();
    }
	
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<EmployeeVO> findAll() throws PHXDBException {
        Query query = this.entityManager.createQuery("FROM EmployeeVO o");

        return query.getResultList();
    }
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void save(EmployeeVO EmployeeVO) throws PHXDBException {
         try{
			 sessionData.onSave(EmployeeVO);
			 
			auditLogVO = new AuditLogVO();
    		auditLogVO.setAction("I");
    		auditLogVO.setUserid(EmployeeVO.getCreatedBy());
    		auditLogVO.setUsername(userMasterService.findById(EmployeeVO.getCreatedBy()).getName());
    		auditLogVO.setTable("DOWNLOAD_FORMS");
    		auditLogVO.setScreenName("Download Form");
    		auditLogVO.setIpAddress(EmployeeVO.getIpAddress());
	    		
        EmployeeVO.setCreateDate(new java.util.Date());
        System.out.println(EmployeeVO.toString());
        entityManager.persist(EmployeeVO);
        auditLogVO.setRowId(EmployeeVO.getId());
        auditLogService.saveAudit(auditLogVO);
        entityManager.flush();
         }catch(Exception e){e.printStackTrace();}
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void save(EmployeeVO[] EmployeeVO) throws PHXDBException {
        try {
            for (int i = 0; i < EmployeeVO.length; i++) {
				sessionData.onSave(EmployeeVO[i]);
                EmployeeVO[i].setCreateDate(new java.util.Date());
                entityManager.persist(EmployeeVO[i]);
                entityManager.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void update(EmployeeVO EmployeeVO)  throws PHXDBException {
		sessionData.onUpdate(EmployeeVO);
		
		auditLogVO = new AuditLogVO();
		auditLogVO.setAction("U");
		auditLogVO.setUserid(EmployeeVO.getUpdatedBy());
		auditLogVO.setUpdatedBy(EmployeeVO.getUpdatedBy());
		auditLogVO.setUsername(userMasterService.findById(EmployeeVO.getUpdatedBy()).getName());
		auditLogVO.setUpdateDate(new java.util.Date());
		auditLogVO.setTable("DOWNLOAD_FORMS");
		auditLogVO.setScreenName("Download Form");
		auditLogVO.setIpAddress(EmployeeVO.getIpAddress());
		auditLogVO.setRowId(EmployeeVO.getId());
		
        EmployeeVO.setUpdateDate(new java.util.Date());
        entityManager.merge(EmployeeVO); 
        auditLogService.saveAudit(auditLogVO);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void inactive(String id) throws PHXDBException {
        EmployeeVO EmployeeVO = entityManager.find(
                EmployeeVO.class, id);   
		sessionData.onUpdate(EmployeeVO);
		
		auditLogVO = new AuditLogVO();
		auditLogVO.setAction("D");
		auditLogVO.setUserid(EmployeeVO.getUpdatedBy());
		auditLogVO.setUpdatedBy(EmployeeVO.getUpdatedBy());
		auditLogVO.setUsername(userMasterService.findById(EmployeeVO.getUpdatedBy()).getName());
		auditLogVO.setUpdateDate(new java.util.Date());
		auditLogVO.setTable("DOWNLOAD_FORMS");
		auditLogVO.setScreenName("Download Form");
		auditLogVO.setIpAddress(EmployeeVO.getIpAddress());
		auditLogVO.setRowId(EmployeeVO.getId());
		
        EmployeeVO.setRecordStatus(0);
        EmployeeVO.setUpdateDate(new java.util.Date());
        entityManager.merge(EmployeeVO); 
        auditLogService.saveAudit(auditLogVO);
    }
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void inactive(EmployeeVO EmployeeVO) throws PHXDBException {
		sessionData.onUpdate(EmployeeVO);
        EmployeeVO.setRecordStatus(0);
        EmployeeVO.setUpdateDate(new java.util.Date());
        entityManager.merge(EmployeeVO);  
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(String id) throws PHXDBException {
        EmployeeVO EmployeeVO = entityManager.find(
                EmployeeVO.class, id);

        entityManager.remove(EmployeeVO);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(EmployeeVO EmployeeVO) throws PHXDBException {
        entityManager.remove(EmployeeVO);
    }
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void refresh(EmployeeVO EmployeeVO) throws PHXDBException {
        entityManager.refresh(EmployeeVO);
    }

    public static boolean isValidNewData(EmployeeVO dataObject) {
        if (dataObject.getCreateDate() == null
                || dataObject.getCreatedBy() == null
                || dataObject.getRecordStatus() == 1) {
            return false;
        } else {
            return true;
        } 
    }
}
