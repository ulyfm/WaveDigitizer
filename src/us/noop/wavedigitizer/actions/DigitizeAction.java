package us.noop.wavedigitizer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import us.noop.wavedigitizer.Start;
import us.noop.wavedigitizer.windows.DigitizeWindow;

/**
 * 
 * @author ulysses
 * Opens the digitization window, after asking the user to select a file.
 */
public class DigitizeAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(Start.mainw);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File fl = fc.getSelectedFile();
					new DigitizeWindow(fl);
				}
			}
		});
	}

}
