package org.plugin.compgen.editors.completion;

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;
import org.plugin.compgen.Activator;

public class CGCompletionProposal extends CompletionProposal {
	
	public static final int KEYWORD = 0;
	public static final int METHOD_DECLARATION = 0;
	private char[] completion;
	private char[] name;
	
	public CGCompletionProposal(String replacementString,
			int replacementOffset, int replacementLength, Image image,
			String displayString, int relevance) {
		super(replacementOffset, replacementLength);
	}
	public CGCompletionProposal(String replacementString,
			int replacementOffset, int replacementLength, Image image,
			String displayString, int relevance, boolean isInDoc) {
		super(replacementOffset, replacementLength);
	}
	protected boolean isSmartTrigger(char trigger) {
		if (trigger == '.') {
			return true;
		}
		return false;
	}
	protected boolean insertCompletion() {
		IPreferenceStore preference = Activator.getDefault()
				.getPreferenceStore();
		return preference
				.getBoolean(PreferenceConstants.CODEASSIST_INSERT_COMPLETION);
	}
	public void setCompletion(char[] charArray) {
		this.completion = charArray;
	}
	public void setName(char[] charArray) {
		this.name = charArray;
	}
}
