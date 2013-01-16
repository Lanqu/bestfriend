package com.kotoblog.persist.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.kotoblog.beans.Site;

@Repository
public class EntityDao implements IEntityDao {

	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveEntity(Site site) {
		this.sessionFactory.getCurrentSession().save(site);
	}

}
