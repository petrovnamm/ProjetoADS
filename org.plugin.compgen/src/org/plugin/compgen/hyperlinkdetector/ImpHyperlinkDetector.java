package org.plugin.compgen.hyperlinkdetector;

import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.IHyperlink;


public class ImpHyperlinkDetector extends AbstractHyperlinkDetector {
	private static final String[] TEXT = {"language", "class", "compile", "extends", "syntax", "this", 
	"val", "eval", "asm","print", "forEach", "nextLabel", "opCodeOf", "toString", "lexical",
		"whitespace", "start"};

	 @Override
	 public IHyperlink[] detectHyperlinks(ITextViewer textViewer, IRegion region, boolean canShowMultipleHyperlinks) {

	  IDocument document = textViewer.getDocument();
	  int offset = region.getOffset();
	  
	  //modificacao
	  canShowMultipleHyperlinks = true;
	  
	  // extract relevant characters
	  IRegion lineRegion;
	  String candidate;
	  try {
	   lineRegion = document.getLineInformationOfOffset(offset);
	   candidate = document.get(lineRegion.getOffset(), lineRegion.getLength());
	  } catch (BadLocationException ex) {
	   return null;
	  }
	  
	  // look for keyword
	  for(int i=0; i<=TEXT.length-1; i++) {
		//if(TEXT[i].equals(candidate)) {  
		  int index = candidate.indexOf(TEXT[i]);
		  if (index != -1) {
	
		   // detect region containing keyword
		   IRegion targetRegion = new Region(lineRegion.getOffset() + index, TEXT[i].length());
		   if ((targetRegion.getOffset() <= offset) && ((targetRegion.getOffset() + targetRegion.getLength()) > offset))
		    // create link
		    return new IHyperlink[] { new ImpHyperlink(targetRegion, textViewer) };
	  }}

	  return null;
	 }

}
