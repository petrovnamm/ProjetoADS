package org.plugin.compgen.hyperlinkdetector;

import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.osgi.internal.loader.classpath.ClasspathEntry;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.lang.model.element.Element;
import javax.swing.JOptionPane;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.OpenInNewWindowAction;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.EditorInputTransfer.EditorInputData;
import org.osgi.framework.Bundle;

public class ImpHyperlink implements IHyperlink {
	private final IRegion fUrlRegion;
	private ITextViewer textView;
	
	 public ImpHyperlink(IRegion urlRegion, ITextViewer view) {
	  fUrlRegion = urlRegion;
	  textView = view;
	 }

	 @Override
	 public IRegion getHyperlinkRegion() {
	  return fUrlRegion;
	 }

	 @Override
	 public String getTypeLabel() {
	  return null;
	 }

	 public ITextViewer getTextView() {
		return textView;
	}

	@Override
	 public String getHyperlinkText() {
		 IDocument document = getTextView().getDocument();
	  try {
		return document.get(fUrlRegion.getOffset(), fUrlRegion.getLength());
	} catch (BadLocationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	 }

	 @Override
	 public void open() {
		 Bundle bundle = Platform.getBundle("org.plugin.compgen");
		 URL fileURL = bundle.getEntry("bin/Linguagem IMP.txt");
		try {
			File file = new File(FileLocator.resolve(fileURL).getPath());
			IFileStore fileStore = EFS.getLocalFileSystem().getStore(file.toURI());
		    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		    System.out.println(file.getPath());
		    IDE.openEditorOnFileStore(page, fileStore);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  catch (PartInitException e) {
			e.printStackTrace();
				// TODO Auto-generated catch block
			} finally {
				System.out.println(getHyperlinkText());
			}
		 //if (file.exists() && file.isFile()) {//}
		 //PreferencesUtil.createPreferenceDialogOn(Display.getDefault().getActiveShell(), null, null, null).open();
	 }
}
