package us.noop.wavedigitizer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import us.noop.wavedigitizer.Start;
import us.noop.wavedigitizer.windows.DigitizeWindow;
import us.noop.wavedigitizer.windows.WaveOverlayWindow;

public class WaveOverlayAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new WaveOverlayWindow();
			}
		});
	}

}
