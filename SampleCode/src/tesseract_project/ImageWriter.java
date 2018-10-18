package tesseract_project;


/**
 * Example of template javacv (opencv) template matching using the last java build 
 * 
 * We need 4 default parameters like this 
 * "C:\Users\Waldema\Desktop\bg.jpg" "C:\Users\Waldema\Desktop\logosiemens.jpg" "C:\Users\Waldema\Desktop\imageToFind.jpg" 100 200 
 * 
 * @author Waldemar Neto 
 */ 
public class ImageWriter { 
 
//    public static void main(String[] args) throws Exception { 
//        int width = Integer.parseInt(100+""); 
//        int height = Integer.parseInt(200+""); 
// 
//        IplImage src = cvLoadImage( 
//"/Users/Fitash/Desktop/tTest/S1.png", 0); 
//        IplImage tmp = cvLoadImage( 
//                "/Users/Fitash/Desktop/tTest/test3.png", 0); 
// 
//        IplImage result = cvCreateImage( 
//                cvSize(src.width() - tmp.width() + 1, 
//                        src.height() - tmp.height() + 1), IPL_DEPTH_32F, src.nChannels()); 
// 
//        cvZero(result); 
// 
//        // Match Template Function from OpenCV 
//        cvMatchTemplate(src, tmp, result, CV_TM_CCORR_NORMED); 
// 
//        // double[] min_val = new double[2]; 
//        // double[] max_val = new double[2]; 
//        DoublePointer min_val = new DoublePointer(); 
//        DoublePointer max_val = new DoublePointer(); 
// 
//        CvPoint minLoc = new CvPoint(); 
//        CvPoint maxLoc = new CvPoint(); 
// 
//        cvMinMaxLoc(result, min_val, max_val, minLoc, maxLoc, null); 
// 
//        // Get the Max or Min Correlation Value 
//        // System.out.println(Arrays.toString(min_val)); 
//        // System.out.println(Arrays.toString(max_val)); 
// 
//        CvPoint point = new CvPoint(); 
//        point.x(maxLoc.x() + tmp.width()); 
//        point.y(maxLoc.y() + tmp.height()); 
//        // cvMinMaxLoc(src, min_val, max_val,0,0,result); 
// 
//        cvRectangle(src, maxLoc, point, CvScalar.RED, 2, 8, 0); // Draw a 
//                                                                // Rectangle for 
//                                                                // Matched 
//                                                                // Region 
//        CvRect rect = new CvRect(); 
//        rect.x(maxLoc.x()); 
//        rect.y(maxLoc.y()); 
//        rect.width(tmp.width() + width); 
//        rect.height(tmp.width() + height); 
//        cvSetImageROI(src, rect); 
//        IplImage imageNew = cvCreateImage(cvGetSize(src), src.depth(), 
//                src.nChannels()); 
//        cvCopy(src, imageNew); 
//        cvSaveImage("/Users/Fitash/Desktop/tTest/test2.png", imageNew); 
// 
//        cvShowImage("Lena Image", src); 
//        cvWaitKey(0); 
//        cvReleaseImage(src); 
//        cvReleaseImage(tmp); 
//        cvReleaseImage(result); 
//    } 
}
/*
import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
* This program demonstrates how to capture a screenshot (full screen) as an
* image which will be saved into a file.
* 

public class ImageWriter extends JFrame {

    static String fileName = "/Users/Fitash/Desktop/tTest/FullScreenshot.jpg";

   /**
    * 
    
   private static final long serialVersionUID = 1L;

   public static void imageWriter(BufferedImage screenFullImage) {
      ImageWriter f = new ImageWriter();
      try {
        /*
         * Let the program wait for 5 seconds to allow you to open the
         * window whose screenshot has to be captured
         
        // Thread.sleep(5000);
                 ImageIO.write(screenFullImage, "jpg", new File(fileName));


      } catch (Exception ex) {
               System.err.println(ex);
      }
   }
}*/