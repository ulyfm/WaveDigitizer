package us.noop.wavedigitizer;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import us.noop.wavedigitizer.maths.WaveSet;
import us.noop.wavedigitizer.windows.MainWindow;


/**
 * 
 * @author ulysses
 *
 * TODO LIST:
 * • Add Fourier Transform
 * • Vertical scale (?)
 * • Documentation
 * • Refactor
 * • Upload to GitHub
 * • Display % error for range of real vs theoretical values
 * • Y shift of wave overlay
 */
public class Start {
	
	public static MainWindow mainw;
	
	public static void main(String... args){
		mainw = new MainWindow();
	}
}
