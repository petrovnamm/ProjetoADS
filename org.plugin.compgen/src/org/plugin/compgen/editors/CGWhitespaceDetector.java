package org.plugin.compgen.editors;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class CGWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
