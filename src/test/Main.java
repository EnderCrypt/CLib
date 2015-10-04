package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import com.github.EnderCrypt.CLib.CLib;
import com.github.EnderCrypt.CLib.event.CLibKeyData;
import com.github.EnderCrypt.CLib.event.CLibKeylistener;

public class Main
	{
	static int[] text = new int[49];
	static int cursorPosition = 0;
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
		
		clib.addKeyListener(new CLibKeylistener()
			{
			@Override
			public void keyReleased(KeyEvent e)
				{
				
				}
			@Override
			public void keyPressed(KeyEvent e)
				{
				int keyChar = e.getKeyCode();
				if (keyChar == KeyEvent.VK_BACK_SPACE)
					{
					if (cursorPosition > 0)
						{
						cursorPosition--;
						text[cursorPosition] = 0;
						}
					}
				else if (keyChar == KeyEvent.VK_ENTER)
					{
					clib.shiftUp();
					clib.put(0, 23, text);
					text = new int[49];
					cursorPosition = 0;
					}
				else
					{
					if ((cursorPosition < text.length) && (keyChar < clib.getTileCount()))
						{
						text[cursorPosition] = keyChar;
						cursorPosition++;
						}
					}
				clib.put(0, 24, text);
				clib.redraw();
				}
			@Override
			public void keyDown(CLibKeyData e)
				{
				
				}
			});
		}
	}
