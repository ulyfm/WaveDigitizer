package us.noop.wavedigitizer.maths;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class WaveSet {
	
	private Color color;
	private double scale;//real dist per pixel
	private ArrayList<Point2D.Double> proData;
	private double startPoint;
	
	public WaveSet(Color color, double startPoint, double scale, ArrayList<Point2D.Double> data1){
		ArrayList<Point2D.Double> data = deepClone(data1);
		this.color = color;
		this.scale = scale;
		this.startPoint = startPoint;
		proData = new ArrayList<Point2D.Double>();
		
		while(data.size() > 0){
			Point2D.Double min = new Point2D.Double(Double.MAX_VALUE, 0);
			for(Point2D.Double pt : data){
				if(pt.getX() < min.getX()){
					min = pt;
				}
			}
			min.x += startPoint / scale;
			proData.add(min);
			data.remove(min);
		}
	}
	
	private ArrayList<java.awt.geom.Point2D.Double> deepClone(ArrayList<java.awt.geom.Point2D.Double> data1) {
		ArrayList <java.awt.geom.Point2D.Double> n = new ArrayList<java.awt.geom.Point2D.Double>();
		for(Point2D.Double d : data1){
			n.add(new Point2D.Double(d.getX(), d.getY()));
		}
		return n;
	}

	public ArrayList<Point2D.Double> getData(){
		return deepClone(proData);
	}
	public Color getColor(){
		return color;
	}
	
	public double getScale(){
		return scale;
	}
	
}
