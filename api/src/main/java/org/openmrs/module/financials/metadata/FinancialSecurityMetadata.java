package org.openmrs.module.financials.metadata;

import org.springframework.stereotype.Component;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.metadatadeploy.bundle.Requires;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.idSet;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.privilege;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.role;

/**
 * Implementation of access control to the app.
 */
@Component
@Requires(org.openmrs.module.kenyaemr.metadata.SecurityMetadata.class)
public class FinancialSecurityMetadata extends AbstractMetadataBundle {
	
	public static class _Privilege {
		
		public static final String APP_FINANCE_APP = "App: financials.home";
	}
	
	public static final class _Role {
		
		public static final String APPLICATION_FINANCE_MODULE = "EHR Finance";
	}
	
	/**
	 * @see AbstractMetadataBundle#install()
	 */
	@Override
	public void install() {
		install(privilege(_Privilege.APP_FINANCE_APP, "Able to access Key EHR finance  module features"));
		install(role(_Role.APPLICATION_FINANCE_MODULE, "Can access EHR finance module Application",
		    idSet(org.openmrs.module.kenyaemr.metadata.SecurityMetadata._Role.API_PRIVILEGES_VIEW_AND_EDIT),
		    idSet(_Privilege.APP_FINANCE_APP)));
	}
}
