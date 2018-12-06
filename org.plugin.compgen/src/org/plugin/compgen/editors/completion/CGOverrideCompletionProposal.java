package org.plugin.compgen.editors.completion;

import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class CGOverrideCompletionProposal extends ScriptCompletionProposal {

	public CGOverrideCompletionProposal(String replacementString, int replacementOffset, int replacementLength,
			Image image, StyledString displayString, int relevance, boolean indoc) {
		super(replacementString, replacementOffset, replacementLength, image, displayString, relevance, indoc);
		// TODO Auto-generated constructor stub
	}



}
