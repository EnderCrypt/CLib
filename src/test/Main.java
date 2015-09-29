package test;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import com.github.EnderCrypt.CLib.CLib;

public class Main
	{
	public static void main(String[] args)
		{
		CLib clib = new CLib("Test", new Dimension(25, 25));
		File file = new File("src/com/github/EnderCrypt/CLib/curses_800x600.png");
		try
			{
			clib.loadGraphics(file, new Dimension(16, 16), new Dimension(10, 12));
			}
		catch (IOException e)
			{
			System.err.println("Failed to load tileset!");
			e.printStackTrace();
			System.exit(1);
			}
		clib.start();
		clib.put(0, 0, '1');
		clib.put(1, 0, '2');
		clib.put(2, 0, '3');
		
		clib.put(0, 1, 'a');
		clib.put(1, 1, 'b');
		clib.put(2, 1, 'c');
		
		clib.put(0, 2, 'A');
		clib.put(1, 2, 'B');
		clib.put(2, 2, 'C');
		clib.redraw();
		}
	}
