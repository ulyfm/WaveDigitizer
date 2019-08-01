package us.noop.wavedigitizer.windows;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import javaspring.SpringUtilities;
import us.noop.wavedigitizer.Start;

public class WaveOverlayWindow extends JFrame {

	public WaveOverlayWindow() {
		super("Wave Overlay");
		JPanel container = new JPanel();
		container.setLayout(new SpringLayout());
		this.add(container);

		// Display?
		JLabel visL = new JLabel("Display?", JLabel.TRAILING);
		JCheckBox visF = new JCheckBox();
		container.add(visL);
		visL.setLabelFor(visF);
		container.add(visF);

		// Frequency (Hz)
		JLabel freqL = new JLabel("Freq (Hz)", JLabel.TRAILING);
		JTextField freqF = new JTextField(10);
		container.add(freqL);
		freqL.setLabelFor(freqF);
		container.add(freqF);

		// Y Scale
		JLabel yscaleL = new JLabel("Y Scale (x)", JLabel.TRAILING);
		JTextField yscaleF = new JTextField(10);
		container.add(yscaleL);
		yscaleL.setLabelFor(yscaleF);
		container.add(yscaleF);

		// X Shift (ms)
		JLabel xshiftL = new JLabel("X Shift (ms)", JLabel.TRAILING);
		JTextField xshiftF = new JTextField(10);
		container.add(xshiftL);
		xshiftL.setLabelFor(xshiftF);
		container.add(xshiftF);

		// Update
		JLabel updateL = new JLabel("--", JLabel.TRAILING);
		JButton updateF = new JButton("Update");
		container.add(updateL);
		updateL.setLabelFor(updateF);
		container.add(updateF);

		updateF.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Start.mainw.dp.waveDisplay(visF.isEnabled(),
						Double.parseDouble(freqF.getText()),
						Double.parseDouble(xshiftF.getText()),
						Double.parseDouble(yscaleF.getText()));
			}
			
		});
		
		this.setSize(new Dimension(200, 200));
		SpringUtilities.makeCompactGrid(container, 5, 2, 2, 2, 2, 2);
		this.setResizable(false);
		this.setVisible(true);
	}
}
