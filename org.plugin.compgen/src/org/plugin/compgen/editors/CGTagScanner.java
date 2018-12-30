package org.plugin.compgen.editors;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;

public class CGTagScanner extends RuleBasedScanner {

	public CGTagScanner(ColorManager manager) {
		IToken string = new Token (new TextAttribute(manager.getColor(ICGColorConstants.STRING)));
		IToken comment = new Token (new TextAttribute(manager.getColor(ICGColorConstants.CG_COMMENT)));
		IToken tag = new Token (new TextAttribute(manager.getColor(ICGColorConstants.TAG)));
		IToken tokens = new Token (new TextAttribute(manager.getColor(ICGColorConstants.TOKEN)));
		IToken character = new Token (new TextAttribute(manager.getColor(ICGColorConstants.CHARACTER)));
		IToken number = new Token (new TextAttribute(manager.getColor(ICGColorConstants.NUMBER)));
		IToken proc_instr = new Token (new TextAttribute(manager.getColor(ICGColorConstants.PROC_INSTR)));
		IRule[] rules = new IRule[10];

		rules[0] = new SingleLineRule(":=", " ", tokens);
		rules[1] = new SingleLineRule("while", " ", tokens);
		rules[2] = new SingleLineRule("do", " ", tokens);
		rules[3] = new SingleLineRule(";", " ", tokens);
		rules[5] = new WordRule(new NumberDetector(), number);
		
		// Add rule for double quotes
		rules[6] = new MultiLineRule("\"", "\"", string);
		// Add a rule for single quotes
		rules[7] = new MultiLineRule("'", "'", character);
		// Add generic whitespace rule.
		rules[8] = new WhitespaceRule(new CGWhitespaceDetector());
		
		rules[9] = new MultiLineRule("<--", "-->", comment);
		
		setRules(rules);
	}
}
