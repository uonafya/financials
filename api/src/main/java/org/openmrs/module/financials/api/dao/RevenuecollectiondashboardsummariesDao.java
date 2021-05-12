/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.financials.api.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.financials.GeneralRevenuePerUnit;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("financials.RevenuecollectiondashboardsummariesDao")
public class RevenuecollectiondashboardsummariesDao {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<OpdTestOrder> getRevenuesPerDepartmentByDate() {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OpdTestOrder.class, "test_order");
		criteria.add(Restrictions.eq("billingStatus", 1));
		
		return criteria.list();
	}
	
	public List<PatientServiceBillItem> getPatientBilledItems() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PatientServiceBillItem.class, "billed_items");
		
		return criteria.list();
	}
}
