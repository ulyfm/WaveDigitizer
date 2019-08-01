package us.noop.wavedigitizer.file;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;

import us.noop.wavedigitizer.Start;
import us.noop.wavedigitizer.maths.WaveSet;
import us.noop.wavedigitizer.windows.DigitizeWindow;
import us.noop.wavedigitizer.windows.MainWindow;

public class SaveUtilities {

	public static File mostRecent = null;

	public static void save(MainWindow m) {
		if (mostRecent == null) {
			saveAs(m);
		} else {
			writeFile(m, mostRecent);
		}
	}

	public static void saveAs(MainWindow m) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save As");   
		 
		int userSelection = fileChooser.showSaveDialog(Start.mainw);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    writeFile(m, fileToSave);
		}
	}

	public static void open(MainWindow m) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(Start.mainw);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File fl = fc.getSelectedFile();
			ArrayList<WaveSet> read = readFile(fl);
			m.dp.getData().addAll(read);
			m.dp.repaint();
		}
	}
	
	private static void writeFile(MainWindow m, File f){
		try {
			PrintStream ps = new PrintStream(f);
			for(WaveSet ws : m.dp.getData()){
				ps.println("--ws_start--");
				ps.println(ws.getColor().getRed() + "," + 
						ws.getColor().getGreen() + "," +
						ws.getColor().getBlue());
				ps.println(ws.getScale());
				for(Point2D.Double p2d : ws.getData()){
					ps.println(p2d.getX() + "," + p2d.getY());
				}
				ps.println("-------------");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static ArrayList<WaveSet> readFile(File f){
		ArrayList<WaveSet> dats = new ArrayList<WaveSet>();
		try{
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine() && sc.nextLine().equals("--ws_start--")){
				String[] colStr = sc.nextLine().trim().split(",");
				Color col = new Color(Integer.parseInt(colStr[0]),
						Integer.parseInt(colStr[1]),
						Integer.parseInt(colStr[2]));
				double scale = Double.parseDouble(sc.nextLine().trim());
				ArrayList<Point2D.Double> dat = new ArrayList<Point2D.Double>();
				String str = "";
				while(!(str = sc.nextLine().trim()).equals("-------------")){
					dat.add(new Point2D.Double(Double.parseDouble(str.split(",")[0]),
							Double.parseDouble(str.split(",")[1])));
				}
				WaveSet w = new WaveSet(col, 0d, scale, dat);
				dats.add(w);
			}
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return dats;
	}
}
