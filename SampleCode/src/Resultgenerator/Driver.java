package Resultgenerator;

import static org.junit.Assert.assertEquals;

import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.opencv.imgproc.Imgproc;

import Dataextractor.ImageSelector;
import Plane.Plane;
import src.CreateUMLModel;



public class Driver {

	private static void generateScript(String umlFile){
		PreprocessDataModel process = new PreprocessDataModel(umlFile);
		String newFile = process.processUMLModel();
		try {
			ObjScriptGenerator driver = new ObjScriptGenerator(newFile);
			driver.generateScript();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String []a) throws Exception
	{
		Model m= ModelLoader.loadModel();

				generateScript("");
		//		




		System.load( "opencv-3.4.1/build/lib/libopencv_java341.dylib" );
		ImageSelector m = new ImageSelector();
		int val1= m.run("S4.png", "test1.png", "S4.png", Imgproc.TM_CCOEFF);

		int val2= m.run("S4.png", "test4.png", "S4.png", Imgproc.TM_CCOEFF);

		System.out.println(val1+"\n"+val2);
		Plane.air_speed=val1;
		String query="context Plane inv inv1:self.air_speed=0";
		boolean result=sr.getResult(query,1,true);

		System.out.println(result);
	}

}
