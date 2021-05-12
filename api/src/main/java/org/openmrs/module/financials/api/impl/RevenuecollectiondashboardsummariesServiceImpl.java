/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.financials.api.impl;

import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.financials.api.RevenuecollectiondashboardsummariesService;
import org.openmrs.module.financials.api.dao.RevenuecollectiondashboardsummariesDao;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;

import java.util.List;

public class RevenuecollectiondashboardsummariesServiceImpl extends BaseOpenmrsService implements RevenuecollectiondashboardsummariesService {
	
	RevenuecollectiondashboardsummariesDao dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(RevenuecollectiondashboardsummariesDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<OpdTestOrder> getRevenuesPerDepartmentByDate() {
		
		return dao.getRevenuesPerDepartmentByDate();
	}
	
	@Override
	public List<PatientServiceBillItem> getPatientBilledItems() {
		return null;
	}
}
