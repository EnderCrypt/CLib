package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.github.EnderCrypt.CLib.CLib;
import com.github.EnderCrypt.CLib.event.CLibKeyData;
import com.github.EnderCrypt.CLib.event.CLibKeylistener;
import com.github.EnderCrypt.CLib.event.CLibMouseData;
import com.github.EnderCrypt.CLib.event.CLibMouselistener;

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
		clib.setMousePointer(true);
		clib.setFrontBrush(Color.RED);
		clib.put(5, 3, "Welcome to the CLib demo "+(char)3);
		
		clib.setFrontBrush(new Color(255,0,255));
		clib.put(2, 10, "% & / []");
		
		clib.setFrontBrush(Color.WHITE);
		clib.put(2, 20, "ABCDE abcde 12345");
		
		clib.redraw();
		
		clib.addMouseListener(new CLibMouselistener()
			{
			@Override
			public void mouseReleased(CLibMouseData e)
				{
				
				}
			@Override
			public void mousePressed(CLibMouseData e)
				{
				
				}
			@Override
			public void mouseMoved(CLibMouseData mouseData)
				{
				
				}
			@Override
			public void mouseDragged(CLibMouseData e)
				{
				clib.put(e.tile.x, e.tile.y, 'X');
				}
			});
		clib.addKeyListener(new CLibKeylistener()
			{
			@Override
			public void keyReleased(KeyEvent e)
				{
				
				}
			@Override
			public void keyPressed(KeyEvent e)
				{
				
				}
			@Override
			public void keyDown(CLibKeyData e)
				{
				List<Integer> keysDown = e.getKeysDown();
				int pos = clib.put(0, 0, "Keys down: ");
				for (int keyDown:keysDown)
					{
					pos = clib.put(pos+1, 0, String.valueOf(keyDown));
					}
				}
			});
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
			{
			@Override
			public void run()
				{
				clib.clearScreen(Color.BLACK);
				}
			}, 2000);
		timer.schedule(new TimerTask()
			{
			@Override
			public void run()
				{
				//clib.clearScreen(Color.BLACK);
				clib.setFrontBrush(null);
				clib.updateListeners();
				clib.put(0, 1, "Paint delta: "+clib.getPaintDelta()+" Milli");
				clib.put(0, 2, "Cached images: "+clib.getCacheCount());
				
				clib.setFrontBrush(new Color((int)(Math.random()*(255*255*255))));
				clib.put(0, 5, (int)(Math.random()*255));
				clib.redraw();
				}
			}, 2000, 50);
		}
	}
