package com.URPlus.nodeCounter.impl;

import java.util.Timer;
import java.util.TimerTask;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.CreationContext.NodeCreationType;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoRedoManager;
import com.ur.urcap.api.domain.variable.GlobalVariable;
import com.ur.urcap.api.domain.variable.Variable;
import com.ur.urcap.api.domain.variable.VariableException;
import com.ur.urcap.api.domain.variable.VariableFactory;

public class NCProgramNodeContribution implements ProgramNodeContribution{

	private final ProgramAPIProvider apiProvider;
	private final NCProgramNodeView view;
	private final DataModel model;
	private final UndoRedoManager undoRedoManager;
	
	private Timer updateTimer;
	
	private static final String COUNTER_KEY = "counter";

	public NCProgramNodeContribution(ProgramAPIProvider apiProvider, NCProgramNodeView view, DataModel model, CreationContext context) {
		// TODO Auto-generated constructor stub
		this.apiProvider = apiProvider;
		this.view = view;
		this.model = model;
		this.undoRedoManager = apiProvider.getProgramAPI().getUndoRedoManager();
		if(context.getNodeCreationType().equals(NodeCreationType.NEW)) {
			createCounterVariable(COUNTER_KEY);
		}
	}
	
	private GlobalVariable createVariable(String suggestedName) {
		VariableFactory vf = apiProvider.getProgramAPI().getVariableModel().getVariableFactory();
		
		GlobalVariable var = null;
		try {
			var = vf.createGlobalVariable(suggestedName);
		} catch (VariableException e) {
			System.out.println("Exception creating global variable...");
		} 
		return var;
	}
	private void createCounterVariable(String suggestedName) {
		model.set(COUNTER_KEY, createVariable(suggestedName));
	}
	public Variable getCounterVariable() {
		return model.get(COUNTER_KEY, (GlobalVariable)null);
	}
	
	@Override
	public void openView() {
		// TODO Auto-generated method stub
		updateTimer = new Timer(true);
		updateTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(COUNTER_KEY.equals(getCounterVariable().getDisplayName())) {
					view.setLableText("");
				}else {
					view.setLableText("Warning: there is more than one NodeCounter node.");
				}
			}
		}, 0, 500);
	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub
		if(updateTimer != null) {
			updateTimer.cancel();
		}
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Node-" + getCounterVariable().getDisplayName();
	}

	@Override
	public boolean isDefined() {
		// TODO Auto-generated method stub
		if (COUNTER_KEY.equals(getCounterVariable().getDisplayName())) {
			return true;
		} else
			return false;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub
		
	}

}
