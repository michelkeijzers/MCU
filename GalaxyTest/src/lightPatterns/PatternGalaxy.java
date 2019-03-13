package lightPatterns;

import java.util.Random;

public class PatternGalaxy extends Pattern 
{
	Random _random;

	static final int MinSpeed = 5;
	static final int MaxSpeed = 25;
	
	static final int MaxNewStars = 1;
	static final double Chance = 2.5; // %
	
	
	public void init()
	{
		super.init();
		_random = new Random();
	}


	public void update()
	{
		startNewStars(MaxNewStars, Chance);
		updateStars();
	}


	private void updateStars() {
		// Update all stars.
		for (int column = 0; column < Pattern.Columns; column++)
		{ 
			for (int row = 0; row < Pattern.Rows; row++)
			{
				switch(getChangeState(column, row))
				{
				case UP:
					processUpState(column, row);
					break;

				case DOWN:
					processDownState(column, row);
					break;

				case IDLE:
					break;

				default:
					assert(false);
					break;
				}
			}
		}
	}


	private void processDownState(int column, int row) 
	{
		Pattern.assertRowColumn(column, row);
		
		if (increaseSpeedCounter(column, row) == getSpeed(column, row))
		{
			resetSpeedCounter(column, row);
			_intensities[column][row]--;
			if (_intensities[column][row] == 0)
			{
				setChangeState(column, row, ChangeState.IDLE);
			}
		}
	}


	private void processUpState(int column, int row) 
	{
		Pattern.assertRowColumn(column, row);
		
		if (increaseSpeedCounter(column, row) == getSpeed(column, row))
		{
			resetSpeedCounter(column, row);
			_intensities[column][row]++;
			if (_intensities[column][row] == Pattern.MaxIntensity)
			{
				setChangeState(column, row, ChangeState.DOWN);
			}
		}
	}


	private void startNewStars(final int MaxNewStars, final double Chance) 
	{
		// Start new stars.
		for (int newStar = 0; newStar < MaxNewStars; newStar++)
		{
			if (Math.abs(_random.nextInt()) % 100 <= Chance)
			{
				final int column = Math.abs(_random.nextInt()) % Pattern.Columns;
				final int row =  Math.abs(_random.nextInt()) % Pattern.Rows;

				if ((getIntensity(column, row) == 0) && (getChangeState(column, row) == 
						ChangeState.IDLE))
				{
					setChangeState(column, row, ChangeState.UP);
					setSpeed(column, row, 
							Math.abs(_random.nextInt() % (MaxSpeed - MinSpeed)) + MinSpeed);
					resetSpeedCounter(column, row);
				}
			}
		}
	}
}