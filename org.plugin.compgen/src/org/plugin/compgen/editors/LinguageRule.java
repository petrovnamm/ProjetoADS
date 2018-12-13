package org.plugin.compgen.editors;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;

public class LinguageRule extends MultiLineRule {
	
	public LinguageRule(String startSequence, String endSequence, IToken token) {
		super("linguage", "}", token);
	}

	protected boolean sequenceDetected(
			ICharacterScanner scanner,
			char[] sequence,
			boolean eofAllowed) {
			
			if (sequence[0] == '}') {
				scanner.unread();
			}
			return super.sequenceDetected(scanner, sequence, eofAllowed);
		}

}
