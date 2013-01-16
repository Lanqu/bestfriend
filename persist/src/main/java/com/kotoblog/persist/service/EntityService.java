package com.kotoblog.persist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kotoblog.beans.Site;
import com.kotoblog.persist.dao.IEntityDao;

@Service
public class EntityService implements IEntityService {

	@Autowired
	private IEntityDao dao;

	@Override
	public void saveEntity(Site site) {
		this.dao.saveEntity(site);
	}

	public void setDao(IEntityDao dao) {
		this.dao = dao;
	}

}
