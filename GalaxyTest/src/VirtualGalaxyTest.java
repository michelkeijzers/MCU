import java.awt.*;       // Using AWT's Graphics and Color

import javax.swing.*;    // Using Swing's components and containers

import java.util.Timer;
import java.util.TimerTask;

import lightPatterns.PatternBlink;
import lightPatterns.PatternFadeInOut;
import lightPatterns.PatternGalaxy;
import lightPatterns.Pattern;
import lightPatterns.PatternScrollDown;

/**
 * Custom Graphics Example: Using key/button to move a line left or right.
 */
@SuppressWarnings("serial")
public class VirtualGalaxyTest extends JFrame 
{
	Timer _timer;
	Pattern _pattern;

	private DrawCanvas canvas; 
	
	
	public Pattern getPattern()
	{
		return _pattern;
	}
	
	// Constructor to set up the GUI components and event handlers
	public VirtualGalaxyTest() 
	{
		//_pattern = new FadeInPattern();
		_pattern = new PatternGalaxy();
		_pattern.init();
		_timer = new Timer();

		// Set up a custom drawing JPanel
		canvas = new DrawCanvas(_pattern);
		canvas.setPreferredSize(new Dimension(
				(int) ((2 + Pattern.Columns) * DrawCanvas.Ratio), 
				(int) ((2 + Pattern.Rows) * DrawCanvas.Ratio)));

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the CLOSE button
		setTitle("Galaxy Light");
		pack();           // pack all the components in the JFrame
		setVisible(true); // show it
		requestFocus();   // set the focus to JFrame to receive KeyEvent

		Timer timer = new Timer();
		timer.schedule(new UpdateScreen(), 0, 10);
	}

	
	class UpdateScreen extends TimerTask 
	{
		public void run() 
		{
			_pattern.update();
			repaint();
		}
	}


	// The entry main() method
	public static void main(String[] args) 
	{
		// Run GUI codes on the Event-Dispatcher Thread for thread safety
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				new VirtualGalaxyTest(); // Let the constructor do the job
			}
		});
	}
}