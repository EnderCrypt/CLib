package com.github.EnderCrypt.CLib.event;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import com.github.EnderCrypt.CLib.CLibPanel;

public class CLibListener implements KeyListener, MouseListener, MouseMotionListener
	{
	public List<CLibMouselistener> mouseListeners = new ArrayList<>();
	public List<CLibKeylistener> keyListeners = new ArrayList<>();
	public List<Integer> keysPressed = new ArrayList<>();
	private CLibPanel clibPanel;
	private Dimension tileSize;
	public Point mousePosition = new Point(0,0);
	public Point mouseOffset = new Point(2, 3);
	public Point tileMousePosition = new Point(-1,-1);
	public CLibListener(CLibPanel clibPanel, Dimension tileSize)
		{
		this.clibPanel = clibPanel;
		this.tileSize = tileSize;
		}
	public boolean setMousePosition(Point point)
		{
		mousePosition = point.getLocation();
		if (mousePosition == null)
			{
			return false;
			}
		mousePosition.x -= mouseOffset.x;
		mousePosition.y -= mouseOffset.y;
		tileMousePosition.x = (int) Math.round(mousePosition.x/tileSize.width);
		tileMousePosition.y = (int) Math.round(mousePosition.y/tileSize.height);
		System.out.println(mousePosition);
		return true;
		}
	public void add(CLibMouselistener e)
		{
		mouseListeners.add(e);
		}
	public void add(CLibKeylistener e)
		{
		keyListeners.add(e);
		}
	// update
	public void update()
		{
		final CLibKeyData keyData = new CLibKeyData(keysPressed);
		for (CLibKeylistener keyListener:keyListeners)
			{
			keyListener.keyDown(keyData);
			}
		}
	// MOUSE
	@Override
	public void mouseClicked(MouseEvent e)
		{
		
		}
	@Override
	public void mousePressed(MouseEvent e)
		{
		//activate mouseListeners
		final CLibMouseData mouseData = new CLibMouseData(e, tileMousePosition);
		for (CLibMouselistener mouseListener:mouseListeners)
			{
			mouseListener.mousePressed(mouseData);
			}
		}
	@Override
	public void mouseReleased(MouseEvent e)
		{
		//activate mouseListeners
		final CLibMouseData mouseData = new CLibMouseData(e, tileMousePosition);
		for (CLibMouselistener mouseListener:mouseListeners)
			{
			mouseListener.mouseReleased(mouseData);
			}
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
		//CLibKeyData keyData = new CLibKeyData(e, keysPressed);
		for (CLibKeylistener keyListener:keyListeners)
			{
			keyListener.keyPressed(e);
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
		//CLibKeyData keyData = new CLibKeyData(e, keysPressed);
		for (CLibKeylistener keyListener:keyListeners)
			{
			keyListener.keyReleased(e);
			}
		}
	@Override
	public void mouseDragged(MouseEvent e)
		{
		setMousePosition(e.getPoint());
		//activate mouseListeners
		final CLibMouseData mouseData = new CLibMouseData(e, tileMousePosition);
		for (CLibMouselistener mouseListener:mouseListeners)
			{
			mouseListener.mouseDragged(mouseData);
			}
		}
	@Override
	public void mouseMoved(MouseEvent e)
		{
		setMousePosition(e.getPoint());
		//activate mouseListeners
		final CLibMouseData mouseData = new CLibMouseData(e, tileMousePosition);
		for (CLibMouselistener mouseListener:mouseListeners)
			{
			mouseListener.mouseMoved(mouseData);
			}
		}
	}