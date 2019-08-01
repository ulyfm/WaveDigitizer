package us.noop.wavedigitizer.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import us.noop.wavedigitizer.Start;
import us.noop.wavedigitizer.maths.WaveSet;

public class DigitizeWindow extends JFrame{
	public double scale = 1;
	public DigitizeWindow(File target){
		super();
		this.setTitle("Digitize");
		JMenuBar bar = new JMenuBar();
		JMenu men = new JMenu("Actions");
		bar.add(men);
		JMenuItem undoB = new JMenuItem("Undo");
		men.add(undoB);
		JMenuItem undoAllB = new JMenuItem("Undo All");
		men.add(undoAllB);
		JMenuItem exportB = new JMenuItem("Export...");
		men.add(exportB);
		JMenuItem scaleB = new JMenuItem("Set Scale");
		men.add(scaleB);
		
		//ImageIcon 
		
		this.setJMenuBar(bar);
		TestPane tp = new TestPane(target);
		this.add(new JScrollPane(tp));
		
		undoB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tp.points.size() >= 1){
					tp.points.remove(tp.points.size() - 1);
					tp.repaint();
				}
			}
			
		});
		undoAllB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				tp.points = new ArrayList<Point2D.Double>();
			}
			
		});
		scaleB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tp.points.size() >= 2){
					Point2D.Double a = tp.points.get(tp.points.size() - 1);
					tp.points.remove(tp.points.size() - 1);
					Point2D.Double b = tp.points.get(tp.points.size() - 1);
					tp.points.remove(tp.points.size() - 1);
					double pixdist = a.distance(b);
					double ms = Double.parseDouble(JOptionPane.showInputDialog("Scale length (ms)"));
					scale = ms / pixdist * 0.1d;//convenience magic number
					JOptionPane.showMessageDialog(DigitizeWindow.this, "Set scale: " + scale);
					tp.repaint();
				}
			}
			
		});
		exportB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Color col = JColorChooser.showDialog(DigitizeWindow.this, "Wave Display Color", Color.BLUE);
				double startTime = Double.parseDouble(JOptionPane.showInputDialog("Start Time? (ms"));
				WaveSet ws = new WaveSet(col, startTime * 0.1d, scale, prepPoints(tp.points));
				Start.mainw.dp.addData(ws);
				Start.mainw.dp.repaint();
			}
			
		});
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	protected ArrayList<java.awt.geom.Point2D.Double> prepPoints(ArrayList<java.awt.geom.Point2D.Double> points) {
		double minY = Double.MAX_VALUE;
		double minX = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;
		for(Point2D.Double p : points){
			if(p.getX() < minX){
				minX = p.getX();
			}
			if(p.getY() < minY){
				minY = p.getY();
			}
			if(p.getY() > maxY){
				maxY = p.getY();
			}
		}
		for(Point2D.Double p : points){
			p.x -= minX;
			p.y -= minY;
			p.y -= (maxY - minY) / 2;
		}
		return points;
	}
	public class TestPane extends JPanel {

        private ArrayList<Point2D.Double> points;
        private BufferedImage image;

        public TestPane(File f) {
            points = new ArrayList<>(25);
            try {
                image = ImageIO.read(f);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	int x = e.getPoint().x - 1;
                	int y = e.getPoint().y - 3;
                    points.add(new Point2D.Double(x, y));
                    repaint();
                }                
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return image == null ? new Dimension(200, 200) : new Dimension(image.getWidth(), image.getHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            if (image != null) {
                g2d.drawImage(image, 0, 0, this);
            }
            g2d.setColor(Color.RED);
            for (Point2D.Double p : points) {
                g2d.fillOval((int) p.x - 2, (int) p.y - 2, 4, 4);
            }
            g2d.dispose();
        }

    }
}
