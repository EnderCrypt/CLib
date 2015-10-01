package com.github.EnderCrypt.CLib.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class CLibListener implements KeyListener, MouseListener
	{
	public List<CLibKeylistener> keyListeners = new ArrayList<>();
	public List<Integer> keysPressed = new ArrayList<>();
	public void add(MouseListener e)
		{
		
		}
	public void add(CLibKeylistener e)
		{
		keyListeners.add(e);
		}
	// MOUSE
	@Override
	public void mouseClicked(MouseEvent e)
		{
		System.out.println("mouseClicked");
		}
	@Override
	public void mousePressed(MouseEvent e)
		{
		System.out.println("mousePressed");
		}
	@Override
	public void mouseReleased(MouseEvent e)
		{
		System.out.println("mouseReleased");
		}
	@Override
	public void mouseEntered(MouseEvent e)
		{
		
		}
	@Override
	public void mouseExited(MouseEvent e)
		{
		
		}
	//KEYS
	@Override
	public void keyTyped(KeyEvent e)
		{
		
		}
	@Override
	public void keyPressed(KeyEvent e)
		{
		// mark key as down
		int keyCode = e.getKeyCode();
		if (keyCode > 0)
			{
			if (!keysPressed.contains(keyCode))
				{
				keysPressed.add(keyCode);
				}
			}
		//activate keyListeners
		CLibKeyData keyData = new CLibKeyData(e, keysPressed);
		for (CLibKeylistener keyListener:keyListeners)
			{
			keyListener.keyPressed(keyData);
			}
		}
	@Override
	public void keyReleased(KeyEvent e)
		{
		// mark key as up
		int keyCode = e.getKeyCode();
		int location = keysPressed.indexOf(keyCode);
		if (location >= 0)
			{
			keysPressed.remove(location);
			}
		//activate keyListeners
		CLibKeyData keyData = new CLibKeyData(e, keysPressed);
		for (CLibKeylistener keyListener:keyListeners)
			{
			keyListener.keyPressed(keyData);
			}
		}
	}