package org.plugin.compgen.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class CGEditor extends TextEditor {

	private ColorManager colorManager;

	public CGEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new CGConfiguration(colorManager));
		setDocumentProvider(new CGDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
