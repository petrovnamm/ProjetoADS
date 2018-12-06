package org.plugin.compgen.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class CGScanner extends RuleBasedScanner {

	public CGScanner(ColorManager manager) {
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(ICGColorConstants.PROC_INSTR)));

		IRule[] rules = new IRule[2];
		//Add rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// Add generic whitespace rule.
		rules[1] = new WhitespaceRule(new CGWhitespaceDetector());

		setRules(rules);
	}
}
