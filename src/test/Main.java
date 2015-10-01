package test;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.Instrument;

import com.github.EnderCrypt.CLib.CLib;

public class Main
	{
	static int counter = 0;
	public static void main(String[] args)
		{
		CLib clib = new CLib("Test", new Dimension(50, 25));
		File file = new File("Tilesets/curses_800x600.png");
		//File file = new File("src/com/github/EnderCrypt/CLib/curses_800x600.png");
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
		clib.put(5, 3, "Welcome to the CLib demo "+(char)3);
		
		clib.setFrontBrush(new Color(255,0,255));
		clib.put(2, 10, "% & / []");
		
		clib.setFrontBrush(Color.WHITE);
		clib.put(2, 20, "ABCDE abcde 12345");
		
		clib.redraw();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
			{
			@Override
			public void run()
				{
				//clib.println(counter+": "+(char)counter);
				//clib.println(new int[]{counter,counter+1,counter+2,counter+3,counter+4,counter+5});
				//counter++;
				clib.clearScreen();
				clib.setFrontBrush(null);
				clib.put(0, 0, "Keys down: "+clib.getKeysDebug().toString());
				clib.put(0, 1, "Paint delta: "+clib.getPaintDelta()+" Milli");
				clib.put(0, 2, "Cached images: "+clib.getCacheCount());
				
				clib.setFrontBrush(new Color((int)(Math.random()*(255*255*255))));
				int[] data = new int[50];
				for (int i=0;i<data.length-1;i++)
					{
					data[i] = (int)(Math.random()*255);
					}
				clib.put(0, 5, data);
				clib.redraw();
				}
			}, 2000, 50);
		}
	}
