package us.noop.wavedigitizer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import us.noop.wavedigitizer.Start;
import us.noop.wavedigitizer.file.SaveUtilities;

public class OpenAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		SaveUtilities.open(Start.mainw);
	}

}
