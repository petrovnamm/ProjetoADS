package org.plugin.compgen.editors.completion;

import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.swt.graphics.Image;

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
protected ScriptCompletionProposal createScriptCompletionProposal(
	String completion, int replaceStart, int length, Image image,
	String displayString, int i) {
return new CGCompletionProposal(completion, replaceStart, length,
		image, displayString, i);
}

protected ScriptCompletionProposal createScriptCompletionProposal(
	String completion, int replaceStart, int length, Image image,
	String displayString, int i, boolean isInDoc) {
return new CGCompletionProposal(completion, replaceStart, length,
		image, displayString, i, isInDoc);
}

protected CompletionProposal createOverrideCompletionProposal(
	IScriptProject scriptProject, ISourceModule compilationUnit,
	String name, String[] paramTypes, int start, int length,
	String displayName, String completionProposal) {
	
	return new CGOverrideCompletionProposal(scriptProject, compilationUnit,
			name, paramTypes, start, length, displayName,
			completionProposal);
}

@Override
protected String getNatureId() {
	// TODO Auto-generated method stub
	return null;
}
}