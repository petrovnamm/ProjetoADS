package org.plugin.compgen.editors;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;


public class CompGenCompletionProposalComputer extends
ScriptCompletionProposalComputer {

public CompGenCompletionProposalComputer() {
}

protected CompGenCompletionProposalCollector createCollector(
	ScriptContentAssistInvocationContext context) {
return new CompGenCompletionProposalCollector(context.getSourceModule());
}

protected TemplateCompletionProcessor createTemplateProposalComputer(
	ScriptContentAssistInvocationContext context) {
return null;
}
}
