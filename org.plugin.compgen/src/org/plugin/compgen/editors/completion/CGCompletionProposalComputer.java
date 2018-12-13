package org.plugin.compgen.editors.completion;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;


public class CGCompletionProposalComputer extends
ScriptCompletionProposalComputer {

	public CGCompletionProposalComputer() {
	}
	
	protected ScriptCompletionProposalCollector createCollector(
		ScriptContentAssistInvocationContext context) {
	return new CGCompletionProposalCollector(context.getSourceModule());
	}
	
	protected TemplateCompletionProcessor createTemplateProposalComputer(
		ScriptContentAssistInvocationContext context) {
	return null;
	}
}
