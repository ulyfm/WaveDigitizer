package us.noop.wavedigitizer.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Scrollable;

import us.noop.wavedigitizer.maths.WaveSet;

public class DrawPanel extends JPanel{

	private static final int MAX_HEIGHT = 340;
	private int dataWidth = 800;
	//private double displayScale = 0.1;
	private ArrayList<WaveSet> data = new ArrayList<WaveSet>();
	
	//Wave Overlay Data
	private boolean display = false;
	private double frequency = 0d;
	private double xshift = 0d;
	private double yscale = 0d;
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
		
		//Wave overlay
	    if(display){
	    	g.setColor(new Color(153, 153, 153));
	    	double h1 = yscale * Math.sin(frequency * ((((double) 0) / 100d) * Math.PI * 2d));
	    	for(int i = 1; i < dataWidth; ++i){
	    		double h2 = yscale * Math.sin(frequency * ((((double) i) / 100d) * Math.PI * 2d));
	    		g.drawLine((int) (0.1d * xshift) + i - 1, (int) h1 + MAX_HEIGHT / 2, (int) (0.1d * xshift) + i, (int) h2 + MAX_HEIGHT / 2);
	    		h1 = h2;
	    	}
	    }
		
	    for(WaveSet ws : data){
	    	g.setColor(ws.getColor());
	    	for(int i = 1; i < ws.getData().size(); ++i){
	    		int sX1 = (int) (ws.getData().get(i - 1).getX() * ws.getScale());
	    		int sY1 = (int) (ws.getData().get(i - 1).getY() * ws.getScale() * 3d) + MAX_HEIGHT / 2;
	    		int sX2 = (int) (ws.getData().get(i).getX() * ws.getScale());
	    		int sY2 = (int) (ws.getData().get(i).getY() * ws.getScale() * 3d) + MAX_HEIGHT / 2;
	    		if(sX2 > dataWidth){									/** TODO scale factor **/
	    			dataWidth += (sX2 - dataWidth);
	    		}
	    		g.drawLine(sX1, sY1, sX2, sY2);
	    	}
	    }
	    
	  
	    
	    g.setColor(Color.BLACK);
		//g.drawLine(0, 0, getWidth(), getHeight());
	    g.drawLine(0, MAX_HEIGHT / 2, getWidth(), MAX_HEIGHT / 2);
	    
	    for(int i = 0; i < dataWidth; i += 100){
	    	g.drawLine(i, MAX_HEIGHT / 2, i, MAX_HEIGHT / 2 + 10);
	    }
	   
	}

	public void addData(WaveSet ws){
		data.add(ws);
	}
	
	public ArrayList<WaveSet> getData(){
		return data;
	}
	
	public void waveDisplay(boolean visible, double freq, double xshift, double yshift){
		this.display = visible;
		this.frequency = freq;
		this.xshift = xshift;
		this.yscale = yshift;
		this.repaint();
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(dataWidth, MAX_HEIGHT);
	}
}
