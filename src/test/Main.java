package test;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.github.EnderCrypt.CLib.CLib;

public class Main
	{
	static int counter = 0;
	public static void main(String[] args)
		{
		CLib clib = new CLib("Test", new Dimension(50, 25));
		File file = new File("src/com/github/EnderCrypt/CLib/curses_800x600.png");
		//File file = new File("src/com/github/EnderCrypt/CLib/cleanfont.png");
		try
			{
			clib.loadGraphics(file, new Dimension(10, 12));
			//clib.loadGraphics(file, new Dimension(32, 32));
			}
		catch (IOException e)
			{
			System.err.println("Failed to load tileset!");
			e.printStackTrace();
			System.exit(1);
			}
		
		clib.setFrontBrush(Color.RED);
		clib.put(2, 15, "We love Clib\nLine 2\nLine 3");
		clib.put(15, 15, 3);
		
		clib.setFrontBrush(new Color(255,0,255));
		clib.put(2, 21, "% & / []");
		
		clib.setFrontBrush(Color.WHITE);
		clib.put(2, 22, "ABCDE abcde 12345");
		
		clib.println("Testing! 123% -0-");
		clib.redraw();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
			{
			@Override
			public void run()
				{
				clib.println(counter+": "+(char)counter);
				counter++;
				clib.redraw();
				}
			}, 1000, 500);
		}
	}
