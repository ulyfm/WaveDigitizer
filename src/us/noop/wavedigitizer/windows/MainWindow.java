package us.noop.wavedigitizer.windows;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import us.noop.wavedigitizer.actions.*;

public class MainWindow extends JFrame {
	
	public DrawPanel dp;
	public MainWindow(){
		super();
		this.setTitle("Summary View");
		this.setSize(500, 400);
		this.setResizable(false);
		
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		bar.add(fileMenu);
		JMenuItem saveB = new JMenuItem("Save");
		saveB.addActionListener(new SaveAction());
		fileMenu.add(saveB);
		JMenuItem saveAsB = new JMenuItem("Save As...");
		saveAsB.addActionListener(new SaveAsAction());
		fileMenu.add(saveAsB);
		JMenuItem openB = new JMenuItem("Open...");
		openB.addActionListener(new OpenAction());
		fileMenu.add(openB);
		JMenuItem imageB = new JMenuItem("Export As Image...");
		imageB.addActionListener(new ExportImageAction());
		fileMenu.add(imageB);
		
		JMenu analysisMenu = new JMenu("Analysis");
		bar.add(analysisMenu);
		JMenuItem digitizeB = new JMenuItem("Digitize...");
		digitizeB.addActionListener(new DigitizeAction());
		analysisMenu.add(digitizeB);
		JMenuItem overlayB = new JMenuItem("Overlay Wave...");
		overlayB.addActionListener(new WaveOverlayAction());
		analysisMenu.add(overlayB);
		JMenuItem fourierB = new JMenuItem("Fourier Transform...");
		fourierB.addActionListener(new FourierTransformAction());
		analysisMenu.add(fourierB);
		
		JMenuItem overlayErrorB = new JMenuItem("Find Overlay Error...");
		overlayErrorB.addActionListener(new FourierTransformAction());
		analysisMenu.add(overlayErrorB);
		
		dp = new DrawPanel();
		
		JScrollPane sp = new JScrollPane(dp);
		this.getContentPane().add(sp);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		sp.setBounds(0, 0, 500, 360);
		dp.repaint();
		
		this.setJMenuBar(bar);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void saveImage(File f)
	{
	    BufferedImage bImg = new BufferedImage(dp.getWidth(), dp.getHeight(), BufferedImage.TYPE_INT_RGB);
	    Graphics2D cg = bImg.createGraphics();
	    dp.paintAll(cg);
	    try {
	            if (ImageIO.write(bImg, "png", f))
	            {
	                System.out.println("-- saved");
	            }
	    } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }
	}
}
