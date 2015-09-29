package test;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import com.github.EnderCrypt.CLib.CLib;

public class Main
	{
	public static void main(String[] args)
		{
		CLib clib = new CLib("Test", new Dimension(100, 50));
		File file = new File("src/com/github/EnderCrypt/CLib/curses_800x600.png");
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
		
		clib.setFrontBrush(Color.RED);
		clib.put(2, 20, "12345");
		
		clib.setFrontBrush(new Color(255,0,255));
		clib.put(2, 21, "abcde");
		
		clib.setFrontBrush(null);
		clib.put(2, 22, "ABCDE");
		
		clib.println("Testing! 123 lol");
		clib.redraw();
		
		for(int i=0;i<10;i++)
			{
			try
				{
				Thread.sleep(1000);
				}
			catch (Exception e)
				{
				e.printStackTrace();
				}
			clib.println("Number: "+i);
			clib.redraw();
			}
		}
	}
