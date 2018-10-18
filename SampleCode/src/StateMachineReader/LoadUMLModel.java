package StateMachineReader;

import java.io.File;
import java.util.Map;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.emf.mapping.ecore2xml.util.Ecore2XMLResource;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;
import org.eclipse.uml2.uml.resource.UML212UMLResource;
import org.eclipse.uml2.uml.resource.UML22UMLResource;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;


/**
 * A class that loads UML model form specified path.
 * 
 * @author 
 * @version 1.0
 */
public class LoadUMLModel {
	private ResourceSet RESOURCE_SET;
	public LoadUMLModel() {
		RESOURCE_SET = new ResourceSetImpl();
	}
	/*
	 * This function converts input path to uri and return uri.
	 * */
	public String getFileURI(String path) throws Exception {
		File f = new File(path);
		String uri = f.toURI().toString();
		return uri;	
	}
	public Object loadModel(String uris){
	Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("uml", new UMLResourceFactoryImpl());
	RESOURCE_SET = new ResourceSetImpl(); 
	final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(RESOURCE_SET.getPackageRegistry());
	RESOURCE_SET.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA,extendedMetaData);


	URI model = URI.createURI(uris);
	Map uriMap = RESOURCE_SET.getURIConverter().getURIMap();
	File currentDirFile = new File("");
	String here = currentDirFile.getAbsolutePath();
	String UML3 = "jar:file:"+here+"/jar/org.eclipse.uml2.uml.source_3.0.0.v200905151700.jar!/";
	URI uri = URI.createURI(UML3);
//	logger.info(UML3);
	uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
	uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
	uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));


	UMLResourcesUtil.init(RESOURCE_SET);
	RESOURCE_SET.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
	RESOURCE_SET.getPackageRegistry().put("http://www.eclipse.org/uml2/5.0.0/UML", UMLPackage.eINSTANCE);
	RESOURCE_SET.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	UMLPackage.eINSTANCE.eClass();

	UMLResource resource = (UMLResource)RESOURCE_SET.getResource(model, true);
	EcoreUtil.resolveAll(resource);	
	UMLResourcesUtil.init(RESOURCE_SET);
	Model _model = null;
	Object result= null;
	try
	{
		_model = (Model) resource.getContents().get(0);
		result = _model;
	}catch(Exception w){}

	if (_model == null) {
		Object _obj = resource.getContents().get(0);
		Package _Package = (Package) _obj;
		result = _Package;
	}


	return result;
}	
	private void registerResourceFactories() 
	{
		Map extensionFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();		 
		extensionFactoryMap.put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);		 
		extensionFactoryMap.put(Ecore2XMLResource.FILE_EXTENSION, Ecore2XMLResource.Factory.INSTANCE);		 
		extensionFactoryMap.put(UML22UMLResource.FILE_EXTENSION, UML22UMLResource.Factory.INSTANCE);		 		 
		extensionFactoryMap.put(UMLResource.FILE_EXTENSION, UML22UMLResource.Factory.INSTANCE);
		extensionFactoryMap.put(UMLResource.FILE_EXTENSION, UML22UMLResource.Factory.INSTANCE);
	}

	private void registerPackages(ResourceSet resourceSet) 
	{
		Map packageRegistry = resourceSet.getPackageRegistry();
		packageRegistry.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		packageRegistry.put(Ecore2XMLPackage.eNS_URI, Ecore2XMLPackage.eINSTANCE);
		packageRegistry.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		packageRegistry .put(UML212UMLResource.UML_METAMODEL_NS_URI, UMLPackage.eINSTANCE);
		packageRegistry .put("http://www.eclipse.org/uml2/2.0.0/UML",UMLPackage.eINSTANCE);

	}
	private void registerPathmaps(URI uri) 
	{
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP),
				uri.appendSegment("libraries").appendSegment(""));
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.METAMODELS_PATHMAP),
				uri.appendSegment("metamodels").appendSegment(""));
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.PROFILES_PATHMAP),
				uri.appendSegment("profiles").appendSegment(""));
	}
}
