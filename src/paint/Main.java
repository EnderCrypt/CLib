package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.github.EnderCrypt.CLib.CLib;
import com.github.EnderCrypt.CLib.event.CLibMouseData;
import com.github.EnderCrypt.CLib.event.CLibMouselistener;

public class Main
	{
	public static char icon = (char) 219;
	public static char halfIcon = (char) 219-(16*2)-10;
	public static boolean blink = true;
	public static Point selected = new Point(0,0);
	public static char lineIcon = (char) 196;
	public static int colorHeight = 2;
	public static Dimension screenSize = new Dimension(50, 25);
	public static Color[][] colorBar = new Color[colorHeight][screenSize.width];
	public static void main(String[] args)
		{
		CLib clib = new CLib("Test", screenSize);
		File file = new File("Tilesets/curses_800x600.png");
		try
			{
			clib.loadGraphics(file, new Dimension(10, 12));
			}
		catch (IOException e)
			{
			System.err.println("Failed to load tileset!");
			e.printStackTrace();
			System.exit(1);
			}
		// draw colors
		for (int y=0;y<colorBar.length;y++)
			{
			Color[] colorRow = colorBar[y];
			for (int x=0;x<colorRow.length;x++)
				{
				if ((x == 0) && (y == 0))
					{
					colorRow[x] = Color.BLACK;
					clib.setFrontBrush(colorRow[x]);
					clib.put(x, screenSize.height-colorHeight+y, icon);
					}
				else
					{
					colorRow[x] = new Color((int)(Math.random()*(255*255*255)));
					clib.setFrontBrush(colorRow[x]);
					clib.put(x, screenSize.height-colorHeight+y, icon);
					}
				}
			}
		// draw line
		clib.setFrontBrush(Color.WHITE);
		for (int x=0;x<screenSize.width;x++)
			{
			clib.put(x, screenSize.height-colorHeight-1, lineIcon);
			}
		clib.setFrontBrush(Color.BLACK);
		// add listeners
		clib.addMouseListener(new CLibMouselistener()
			{
			@Override
			public void mouseReleased(CLibMouseData e)
				{
				
				}
			@Override
			public void mousePressed(CLibMouseData e)
				{
				if (e.tile.y >= (screenSize.height-colorHeight))
					{
					selected.x = e.tile.x;
					selected.y = e.tile.y-screenSize.height+colorHeight;
					Color color = colorBar[selected.y][selected.x];
					clib.setFrontBrush(color);
					}
				}
			@Override
			public void mouseMoved(CLibMouseData mouseData)
				{
				
				}
			@Override
			public void mouseDragged(CLibMouseData e)
				{
				if (e.tile.y < (screenSize.height-colorHeight-1))
					{
					clib.put(e.tile.x, e.tile.y, icon);
					clib.redraw();
					}
				}
			});
		// blinking
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
			{
			@Override
			public void run()
				{
				blink = !blink;
				int y = screenSize.height-colorHeight+selected.y;
				if (blink)
					{
					clib.put(selected.x, y, icon);
					}
				else
					{
					clib.put(selected.x, y, halfIcon);
					}
				clib.redraw();
				}
			}, 500,500);
		}
	}
