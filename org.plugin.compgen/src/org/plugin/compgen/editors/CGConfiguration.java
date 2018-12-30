package org.plugin.compgen.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.DefaultAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.plugin.compgen.editors.completion.CGCompletionProcessor;

public class CGConfiguration extends SourceViewerConfiguration {
	
	private CGDoubleClickStrategy doubleClickStrategy;
	private CGTagScanner tagScanner;
	private CGScanner scanner;
	private ColorManager colorManager;
	private CGEditor editor;

	public CGConfiguration(ColorManager colorManager, CGEditor editor) {
		this.colorManager = colorManager;
		this.editor = editor;
	}
	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
		// TODO Auto-generated method stub
		return new DefaultAnnotationHover();
	}
	
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			CGPartitionScanner.CON_GEN_COMMENT,
			CGPartitionScanner.CON_GEN_CLASS };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new CGDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected CGScanner getCGScanner() {
		if (scanner == null) {
			scanner = new CGScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(ICGColorConstants.DEFAULT))));
		}
		return scanner;
	}
	protected CGTagScanner getCGTagScanner() {
		if (tagScanner == null) {
			tagScanner = new CGTagScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(ICGColorConstants.TAG))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr;
		
		dr = new DefaultDamagerRepairer(getCGTagScanner());
		reconciler.setDamager(dr, CGPartitionScanner.CON_GEN_CLASS);
		reconciler.setRepairer(dr, CGPartitionScanner.CON_GEN_CLASS);

		dr = new DefaultDamagerRepairer(getCGScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(ICGColorConstants.CG_COMMENT)));
		reconciler.setDamager(ndr, CGPartitionScanner.CON_GEN_COMMENT);
		reconciler.setRepairer(ndr, CGPartitionScanner.CON_GEN_COMMENT);

		return reconciler;
	}
	
	protected void alterContentAssistant(ContentAssistant assistant) {
		// IDocument.DEFAULT_CONTENT_TYPE
		IContentAssistProcessor scriptProcessor = (IContentAssistProcessor) new CGCompletionProcessor(
				assistant, IDocument.DEFAULT_CONTENT_TYPE);
		assistant.setContentAssistProcessor(scriptProcessor,
				IDocument.DEFAULT_CONTENT_TYPE);
	}

	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer)
    {
        ContentAssistant assistant = new ContentAssistant();
        assistant.setContentAssistProcessor(new CGContentAssistantProcessor(),IDocument.DEFAULT_CONTENT_TYPE);
        assistant.enableAutoActivation(true);
        
        return assistant;
    }
    /* (non-Javadoc)
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getReconciler(org.eclipse.jface.text.source.ISourceViewer)
     */
    public IReconciler getReconciler(ISourceViewer sourceViewer)
    {
    	CGReconcilingStrategy strategy = new CGReconcilingStrategy();
        strategy.setEditor(editor);
        
        MonoReconciler reconciler = new MonoReconciler(strategy,false);
        
        return reconciler;
    }

}