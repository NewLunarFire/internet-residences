/**
 * 
 */
package org.tsavaria;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 * @author tommy
 *
 */
public class ResultDialog extends JFrame {
	private static final long serialVersionUID = 1L;

	public ResultDialog(float consomme, float total) {
		double pc = (consomme * 100.0) / total;
		
		JProgressBar pbConsommation = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
		pbConsommation.setValue((int) pc);
		
		setLayout(new FlowLayout(FlowLayout.LEADING));
		
		getContentPane().add(new JLabel("Vous avez consommé " + consomme + " / " + total + " Go (" + ((int)(pc*10)) / 10.0 + "%)"));
		getContentPane().add(pbConsommation);
		
		pack();
	}
}
