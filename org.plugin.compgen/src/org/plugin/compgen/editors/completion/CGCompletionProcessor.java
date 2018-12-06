package org.plugin.compgen.editors.completion;

import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.ContentAssistProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.plugin.compgen.Activator;
import org.plugin.compgen.editors.CGNature;

public class CGCompletionProcessor extends ContentAssistProcessor {
	
	public CGCompletionProcessor(
			ContentAssistant assistant, String partition) {
		super(assistant, partition);
	}
 
	protected String getNatureId() {
		return CGNature.COMP_GEN_NATURE;
	}
 
	protected CompletionProposalLabelProvider getProposalLabelProvider() {
		return new CompletionProposalLabelProvider();
	}
	protected IPreferenceStore getPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}
}
