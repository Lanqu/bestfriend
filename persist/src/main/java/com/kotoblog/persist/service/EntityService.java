package com.kotoblog.persist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kotoblog.beans.Site;
import com.kotoblog.persist.dao.IEntityDao;

@Service
@Transactional(readOnly = true)
public class EntityService implements IEntityService {

	@Autowired
	private IEntityDao dao;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void saveEntity(Site site) {
		this.dao.saveEntity(site);
	}

	public void setDao(IEntityDao dao) {
		this.dao = dao;
	}

}
