package org.plugin.compgen.editors.completion;

import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.plugin.compgen.editors.CGNature;

public class CGCompletionProposalCollector extends
ScriptCompletionProposalCollector {

	protected final static char[] VAR_TRIGGER = { '\t', ' ', '=', ';', '.' };
	
	protected char[] getVarTrigger() {
	return VAR_TRIGGER;
	}
	
	public CGCompletionProposalCollector(ISourceModule module) {
	super(module);
	}
	
	// Specific proposals creation. May be use factory?
	protected CGCompletionProposal createCompletionProposal(
		String completion, int replaceStart, int length, Image image,
		String displayString, int i) {
	return new CGCompletionProposal(completion, replaceStart, length,
			image, displayString, i);
	}
	
	protected CGCompletionProposal createCompletionProposal(
		String completion, int replaceStart, int length, Image image,
		String displayString, int i, boolean isInDoc) {
	return new CGCompletionProposal(completion, replaceStart, length,
			image, displayString, i, isInDoc);
	}
	
	protected CGOverrideCompletionProposal createOverrideCompletionProposal(
			String replacementString, int replacementOffset, int replacementLength,
			Image image, StyledString displayString, int relevance, boolean indoc) {
		
		return new CGOverrideCompletionProposal(replacementString, replacementOffset,
				replacementLength, image, displayString, relevance, indoc);
	}
	
	@Override
	protected String getNatureId() {
		return CGNature.COMP_GEN_NATURE;
	}
}