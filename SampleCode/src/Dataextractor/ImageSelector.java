package Dataextractor;



import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;


import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class ImageSelector {


	public int run(String inFile, String templateFile, String outFile, int match_method) throws IOException, InterruptedException, TesseractException {
		Mat img = Imgcodecs.imread(inFile);
		Mat templ = Imgcodecs.imread(templateFile);
		int result_cols = img.cols() - templ.cols() + 1;
		int result_rows = img.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
		Imgproc.matchTemplate(img, templ, result, match_method);
		Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
		MinMaxLocResult mmr = Core.minMaxLoc(result);
		Point matchLoc;
		if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
			matchLoc = mmr.minLoc;
		} else {
			matchLoc = mmr.maxLoc;
		}
		Rect rect_Crop=null;
		System.out.println("x:"+((int)matchLoc.x+2)+"\n");
		System.out.println("y:"+((int)matchLoc.y)+"\n");
		
		
		System.out.println("width:"+(templ.cols()+20));
		
		System.out.println("height:"+(templ.rows()+30));
		
		rect_Crop = new Rect(((int)matchLoc.x)+2, (int)matchLoc.y, templ.cols()+20, templ.rows()+30);
		Mat image_roi = new Mat(img,rect_Crop);
		Imgcodecs.imwrite("test2.jpeg",image_roi);
		Imgcodecs.imwrite(outFile, img);
		Tesseract tessInst = new Tesseract();
		tessInst.setDatapath("Tess4J");
		String word= tessInst.doOCR(new File("test2.jpeg"));
		int x=2,y=5;
		word = word.replaceAll("[^\\d.]", "");

		int possiblevalue = 130;
		if(word.trim().isEmpty())
		{
			while(word.trim().isEmpty())
			{
				y-=20;
				rect_Crop = new Rect(((int)matchLoc.x)+x, (int)matchLoc.y-y, templ.cols()+20, templ.rows()+30);
				image_roi = new Mat(img,rect_Crop);
				Imgcodecs.imwrite("test2.jpeg",image_roi);

				word= tessInst.doOCR(new File("test2.jpeg"));
				word = word.replaceAll("[^\\d]", "");

				if(!word.equals(possiblevalue+""))
				{
					word="";

				}
			}
		}
		word = word.replaceAll("[^\\d.]", "");
		if(word.isEmpty())
		{
			return -1;
		}
		return  Integer.parseInt(word);
	}

}