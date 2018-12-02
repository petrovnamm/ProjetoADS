package org.plugin.compgen.editors;

import org.eclipse.jface.text.rules.*;

public class CompGenPartitionScanner extends RuleBasedPartitionScanner {
	public final static String CON_GEN_COMMENT = "__con_gen_comment";
	public final static String CON_GEN_CLASS = "__con_gen_class";

	public CompGenPartitionScanner() {

		IToken conGenComment = new Token(CON_GEN_COMMENT);
		IToken conGenClass = new Token(CON_GEN_CLASS);

		IPredicateRule[] rules = new IPredicateRule[2];

		rules[0] = new MultiLineRule("<!--", "-->", conGenComment);
		rules[1] = new ClassRule(conGenClass);
		//rules[1] = new TagRule(conGenContent);

		setPredicateRules(rules);
	}
}
