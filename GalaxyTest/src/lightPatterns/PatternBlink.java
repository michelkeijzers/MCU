package lightPatterns;

public class PatternBlink extends Pattern 
{
	static final int Delay = 10;

	private int _delayCounter;

	public void init()
	{
		super.init();
		_delayCounter = 0;
	}


	public void update()
	{
		_delayCounter = (_delayCounter + 1) % Delay;

		if (_delayCounter == 0)
		{
			for (int column = 0; column < Pattern.Columns; column++)
			{ 
				for (int row = 0; row < Pattern.Rows; row++)
				{
					_intensities[column][row] = _intensities[column][row] == 0 ? MaxIntensity : 0;
				}
			}
		}
	}
}
