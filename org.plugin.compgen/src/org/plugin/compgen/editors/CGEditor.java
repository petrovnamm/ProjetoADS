package org.plugin.compgen.editors;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;

public class CGEditor extends TextEditor {

	private ColorManager colorManager;
	private Annotation[] oldAnnotations;
	private ProjectionAnnotationModel annotationModel;

	public CGEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new CGConfiguration(colorManager, this));
		setDocumentProvider(new CGDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}
	
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
	    ProjectionViewer viewer =(ProjectionViewer)getSourceViewer();

	    ProjectionSupport projectionSupport = new ProjectionSupport(viewer,getAnnotationAccess(),getSharedColors());
	    projectionSupport.install();

	    viewer.doOperation(ProjectionViewer.TOGGLE);

	     annotationModel = viewer.getProjectionAnnotationModel();
	}
	
	public void updateFoldingStructure(ArrayList positions)
	{
		Annotation[] annotations = new Annotation[positions.size()];
		
		
		HashMap newAnnotations = new HashMap();
		
		for(int i =0;i<positions.size();i++)
		{
			ProjectionAnnotation annotation = new ProjectionAnnotation();
			
			newAnnotations.put(annotation,positions.get(i));
			
			annotations[i]=annotation;
		}
		
		annotationModel.modifyAnnotations(oldAnnotations,newAnnotations,null);
		
		oldAnnotations=annotations;
	}
	
	protected ISourceViewer createSourceViewer(Composite parent,
            IVerticalRuler ruler, int styles)
    {
        ISourceViewer viewer = new ProjectionViewer(parent, ruler, getOverviewRuler(), isOverviewRulerVisible(), styles);

    
    	getSourceViewerDecorationSupport(viewer);
    	
    	return viewer;
    }
	

}
