package lightPatterns;
public abstract class Pattern {
	public static final int Columns = 10;
	public static final int Rows = 16;
	public static final int MaxIntensity = 20;

	protected int _intensities[][];

	protected enum ChangeState { UP, DOWN, IDLE };
	protected ChangeState _changeStates[][];

	protected int _speeds[][];
	protected int _speedCounters[][];
	
	
	public Pattern()
	{
		_intensities = new int[Columns][Rows];
		_changeStates = new ChangeState[Columns][Rows];
		_speeds = new int[Columns][Rows];
		_speedCounters = new int[Columns][Rows];
	}


	public int getIntensity(int column, int row)
	{
		assertRowColumn(column, row);

		return _intensities[column][row];
	}

	
	protected void setIntensity(int column, int row, int intensity)
	{
		assertRowColumn(column, row);
		assert(intensity >= 0);
		assert(intensity <= MaxIntensity);
		
		_intensities[column][row] = intensity;
	}

	
	public ChangeState getChangeState(int column, int row)
	{
		assertRowColumn(column, row);

		return _changeStates[column][row];
	}
	
	
	public void setChangeState(int column, int row, ChangeState changeState)
	{
		assertRowColumn(column, row);

		_changeStates[column][row] = changeState;
	}
	
	
	protected int getSpeed(int column, int row)
	{
		assertRowColumn(column, row);

		return _speeds[column][row];
	}

	
	protected void setSpeed(int column, int row, int speed)
	{
		assertRowColumn(column, row);
		assert(speed >= 0);
		
		_speeds[column][row] = speed;
	}
	

	protected int getSpeedCounter(int column, int row)
	{
		assertRowColumn(column, row);
		
		return _speedCounters[column][row];
	}

	
	protected int increaseSpeedCounter(int column, int row)
	{
		assertRowColumn(column, row);
		
		return _speedCounters[column][row]++;
	}
	
	
	protected void resetSpeedCounter(int column, int row)
	{
		assertRowColumn(column, row);
		
		_speedCounters[column][row] = 0;
	}
	
	
	public void init()
	{
		for (int column = 0; column < Pattern.Columns; column++)
		{ 
			for (int row = 0; row < Pattern.Rows; row++)
			{
				_intensities[column][row] = 0;
				_changeStates[column][row] = ChangeState.IDLE;
				_speeds[column][row] = 0;
				_speedCounters[column][row] = 0;
			}
		}
	}


	public abstract void update(); 
	
	
	public static void assertRowColumn(int column, int row) {
		assert(column >= 0);
		assert(column < Columns);
		assert(row >= 0);
		assert(row < Columns);
	}
}
