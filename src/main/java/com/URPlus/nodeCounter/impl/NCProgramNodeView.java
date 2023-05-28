package com.URPlus.nodeCounter.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.Style;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class NCProgramNodeView implements SwingProgramNodeView<NCProgramNodeContribution>{
	
	private final ViewAPIProvider apiProvider;
	private JLabel labelMessage = new JLabel("");

	public NCProgramNodeView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated constructor stub
		this.apiProvider = apiProvider;
				
	}
	public void setLableText(String str) {
		labelMessage.setText(str);
	}
	@Override
	public void buildUI(JPanel panel, ContributionProvider<NCProgramNodeContribution> provider) {
		// TODO Auto-generated method stub
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(createSpaceing(0, 200));
		panel.add(createDescription());
	}
	
	private Box createDescription() {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(labelMessage);
		return box;
	}
	

	private Component createSpaceing(int horizontal, int vertical) {
		return Box.createRigidArea(new Dimension(horizontal, vertical));
	}


}
