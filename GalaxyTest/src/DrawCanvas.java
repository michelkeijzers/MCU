import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import lightPatterns.Pattern;

class DrawCanvas extends JPanel 
{
	private static final long serialVersionUID = 1L;
	public static final int Ratio = 40;
	public static final int LedSize = 8;

	private Color LedColors[];

	private Pattern _pattern;

	DrawCanvas(Pattern pattern)
	{
		_pattern = pattern;
		setLedColors();
	}


	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		setBackground(Color.BLACK);

		for (int column = 0; column < Pattern.Columns; column++)
		{ 
			for (int row = 0; row < Pattern.Rows; row++)
			{
				drawLed(g, column, row); 
			}
		}
	}


	private void drawLed(Graphics g, int column, int row) 
	{
		final int intensity = _pattern.getIntensity(column, row);
		final int colorIndex = (column + row) % Pattern.Columns;
		final int red   = LedColors[colorIndex].getRed()   * intensity / Pattern.MaxIntensity;
		final int green = LedColors[colorIndex].getGreen() * intensity / Pattern.MaxIntensity;
		final int blue  = LedColors[colorIndex].getBlue()  * intensity / Pattern.MaxIntensity;
		g.setColor(new Color(red, green, blue));
		g.fillOval((1 + column) * Ratio, (1 + row) * Ratio, LedSize, LedSize);
	}


	private void setLedColors() {
		LedColors = new Color[Pattern.Rows];
		int row = 0;
		LedColors[row++] = Color.YELLOW;
		LedColors[row++] = Color.YELLOW;
		LedColors[row++] = Color.ORANGE;
		LedColors[row++] = Color.ORANGE;
		LedColors[row++] = Color.BLUE;
		LedColors[row++] = Color.BLUE;
		LedColors[row++] = Color.GREEN;
		LedColors[row++] = Color.GREEN;

		while (row < Pattern.Rows)
		{
			LedColors[row] = Color.WHITE;
			row++;
		}
	}
}

