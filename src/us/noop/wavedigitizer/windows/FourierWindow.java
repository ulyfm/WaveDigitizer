package us.noop.wavedigitizer.windows;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FourierWindow extends JFrame {
	public FourierWindow(){
		super("Fourier Transform");
		
	}
	
	class FourierDraw extends JPanel {
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
		}
	}
}