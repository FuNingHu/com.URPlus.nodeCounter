package com.URPlus.nodeCounter.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.SystemAPI;
import com.ur.urcap.api.domain.data.DataModel;

public class NCProgramNodeService implements SwingProgramNodeService<NCProgramNodeContribution, NCProgramNodeView>{

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "nodecounter";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub
		configuration.setChildrenAllowed(false);
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "Node Counter";
	}

	@Override
	public NCProgramNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		SystemAPI systemAPI = apiProvider.getSystemAPI();
		return new NCProgramNodeView(apiProvider);
	}

	@Override
	public NCProgramNodeContribution createNode(ProgramAPIProvider apiProvider, NCProgramNodeView view, DataModel model,
			CreationContext context) {
		// TODO Auto-generated method stub
		
		return new NCProgramNodeContribution(apiProvider, view, model, context);
	}

}
