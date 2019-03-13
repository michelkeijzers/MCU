package lightPatterns;

public class PatternScrollDown extends Pattern 
{
	enum EDirection 
	{
		Left,
		Right,
		Up,
		Down
	};

	static final EDirection _direction = EDirection.Left;
	static final int Delay = 10;

	private int _currentRow = 0; // Only one is used
	private int _currentColumn = 0; // Only one is used

	private int _delayCounter;

	public void init()
	{
		super.init();
		_delayCounter = 0;

		switch (_direction)
		{
		case Left:
			_currentColumn = Columns - 1;
			break;

		case Right:
			_currentColumn = 0;
			break;

		case Up:
			_currentRow = Rows - 1;
			break;

		case Down:
			_currentRow = 0;
			break;

		default:
			assert(false);
			break;
		}
	}


	public void update()
	{
		_delayCounter = (_delayCounter + 1) % Delay;

		if (_delayCounter == 0)
		{
			switch (_direction)
			{
			case Left:
				for (int row = 0; row < Pattern.Rows; row++)
				{ 
					_intensities[(_currentColumn + 1) % Columns][row] = 0;
					_intensities[_currentColumn][row] = MaxIntensity;
				}
				_currentColumn = (_currentColumn - 1 + Columns) % Columns;
				break;

			case Right:
				for (int row = 0; row < Pattern.Rows; row++)
				{ 
					_intensities[(_currentColumn - 1 + Columns) % Columns][row] = 0;
					_intensities[_currentColumn][row] = MaxIntensity;
				}
				_currentColumn = (_currentColumn + 1) % Columns;
				break;

			case Up:
				for (int column = 0; column < Pattern.Columns; column++)
				{ 
					_intensities[column][(_currentRow + 1) % Rows] = 0;
					_intensities[column][_currentRow] = MaxIntensity;
				}
				_currentRow = (_currentRow - 1 + Rows) % Rows;
				break;

			case Down:
				for (int column = 0; column < Pattern.Columns; column++)
				{ 
					_intensities[column][(_currentRow - 1 + Rows) % Rows] = 0;
					_intensities[column][_currentRow] = MaxIntensity;
				}
				_currentRow = (_currentRow + 1) % Rows;
				break;

			default:
				assert(false);
				break;
			}
		}
	}
}
