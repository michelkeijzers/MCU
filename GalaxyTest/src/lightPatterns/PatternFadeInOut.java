package lightPatterns;



public class PatternFadeInOut extends Pattern 
{	
	private boolean _fadeOut;

	public void init()
	{
		_fadeOut = false;
	}

	static final int Delay = 10;

	private int _delayCounter;


	public void update()
	{	
		_delayCounter = (_delayCounter + 1) % Delay;

		if (_delayCounter == 0)
		{
			processFadeInOut();
		}
	}


	private void processFadeInOut() 
	{
		for (int column = 0; column < Pattern.Columns; column++)
		{ 
			for (int row = 0; row < Pattern.Rows; row++)
			{
				if (_fadeOut)
				{
					fadeOut(column, row);
				}
				else // Fade in
				{
					fadeIn(column, row);
				}
			}
		}
	}


	private void fadeOut(int column, int row) {
		_intensities[column][row]--;
		if (_intensities[column][row] == 0)
		{
			_fadeOut = false;
		}
	}
	

	private void fadeIn(int column, int row) {
		_intensities[column][row]++;
		if (_intensities[column][row] == MaxIntensity)
		{
			_fadeOut = true;
		}
	}

}
