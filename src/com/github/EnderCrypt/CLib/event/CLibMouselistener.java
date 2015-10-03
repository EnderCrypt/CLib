package com.github.EnderCrypt.CLib.event;

public interface CLibMouselistener
	{
	public void mousePressed(CLibMouseData e);
	
	public void mouseDragged(CLibMouseData e);
	
	public void mouseMoved(CLibMouseData mouseData);
	
	public void mouseReleased(CLibMouseData e);
	}
