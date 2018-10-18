package TestPathGenerator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.eclipse.uml2.uml.internal.impl.PseudostateImpl;
import org.eclipse.uml2.uml.internal.impl.StateImpl;
/**
 * A class that reads UML class diagram in XMI format and prints its elements.
 * 
 * @author 
 * @version 1.0
 */
public class StateMachineReader {

	public static void main(String[] args) {
		LoadUMLModel umlModel = new LoadUMLModel();
		String umlFilePath = "examples/StateAMachine.uml";

		String uri = null;
		try {
			uri = umlModel.getFileURI(umlFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object objModel = umlModel.loadModel(uri);
		Model sourceModel;
		EList<PackageableElement> sourcePackagedElements = null;
		if (objModel instanceof Model) {
			sourceModel = (Model) objModel;
			sourcePackagedElements = sourceModel.getPackagedElements();
		} else if (objModel instanceof Package) {
			Package sourcePackage = (Package) objModel;
			sourcePackagedElements = sourcePackage.getPackagedElements();
		}

		for (PackageableElement element : sourcePackagedElements){
			//for nested package
			printModelDetails(element);
		}
	}

	private static void printModelDetails(PackageableElement element){
		if (element.eClass() == UMLPackage.Literals.STATE_MACHINE)
		{
			StateMachine sm = (StateMachine)element;

			getFromRegions( sm.getRegions());

		}

	}

	private static void getFromRegions(EList<Region> regions) {
		// TODO Auto-generated method stub
		
		for(Region r:regions)
		{
			EList<Vertex> subvertices = r.getSubvertices();
			for(Vertex v:subvertices)
			{
				if(v instanceof PseudostateImpl)
				{
					PseudostateImpl s=(PseudostateImpl)v;
					printTree(s.getOutgoings());

				}
			}
		}
	}

	private static ArrayList<Transition>tt=new ArrayList<Transition>();
	private static ArrayList<String>visited=new ArrayList<String>();
	private static void printTree(EList<Transition> outgoings) {

		for(Transition t:outgoings)
		{
			if(tt.contains(t))
			{
				continue;
			}
			System.out.print(t.getSource().getName()+"--");
			System.out.print(t.getName()+"->");
			System.out.print(t.getTarget().getName()+" ==> ");
			if(visited.contains(t.getTarget().getName()))
			{
				System.out.println();
				
			}
			StateImpl tts = (StateImpl) t.getTarget();
			
			if(tts.getRegions().size()>0)
			{
				System.out.println();
				getFromRegions(tts.getRegions());
			}
			
			
			visited.add(t.getSource().getName());
			tt.add(t);
			printTree(t.getTarget().getOutgoings());
		}

	}

	private static void PrintStates(Vertex v) {
		if(v instanceof StateImpl)
		{
			StateImpl s= (StateImpl) v;

			Region r = s.getRegions().get(0);

			System.out.println(r);

			Vertex startState = getPseduoState(r.getSubvertices());

		}

	}

	private static Vertex getPseduoState(EList<Vertex> subvertices) {
		for(Vertex v:subvertices)
		{
			if(v instanceof Pseudostate)
				return v;
		}
		return null;

	}
}
