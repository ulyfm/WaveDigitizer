package us.noop.wavedigitizer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import us.noop.wavedigitizer.Start;
import us.noop.wavedigitizer.windows.DigitizeWindow;

public class ExportImageAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Export Image");   
				 
				int userSelection = fileChooser.showSaveDialog(Start.mainw);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    Start.mainw.saveImage(fileToSave);
				}
			}
		});
	}

}
