package SimulatorExecutor;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import ImageCapturer.IMC;

public class SE
{
    
    public static void main(String[] args) throws Exception
    {
    	new IMC().start();  
		Process p = Runtime.getRuntime().exec("ExecuteScripts.sh");
		
    }
}