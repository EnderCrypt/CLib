package com.github.EnderCrypt.CLib.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class CLibListener implements KeyListener
	{
	public List<Integer> keysPressed = new ArrayList<>();
	public void add(KeyListener e)
		{
		
		}
	public void add(MouseListener e)
		{
		
		}
	@Override
	public void keyTyped(KeyEvent e)
		{
		
		}
	@Override
	public void keyPressed(KeyEvent e)
		{
		int keyCode = e.getKeyCode();
		if (keyCode > 0)
			{
			if (!keysPressed.contains(keyCode))
				{
				keysPressed.add(keyCode);
				}
			}
		}
	@Override
	public void keyReleased(KeyEvent e)
		{
		int keyCode = e.getKeyCode();
		int location = keysPressed.indexOf(keyCode);
		if (location >= 0)
			{
			keysPressed.remove(location);
			}
		}
	}