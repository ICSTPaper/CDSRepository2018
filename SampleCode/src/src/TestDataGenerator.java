package src;
import java.util.ArrayList;


public class TestDataGenerator  extends AbstractTestCase{
	String umlFile = "";
	String path ="";
	public ArrayList<ClassifierTuple> getData(String query)
	{
		
		ArrayList<ClassifierTuple> result = null;
		result = test(1,1000, query, SearchAlgorithmEnum.AVM, umlFile,path);
		return result;
	}
	

}
