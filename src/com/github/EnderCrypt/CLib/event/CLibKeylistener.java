package com.github.EnderCrypt.CLib.event;

import java.awt.event.KeyEvent;

public interface CLibKeylistener
	{
	public void keyDown(CLibKeyData e);
	
	public void keyPressed(KeyEvent e);
	
	public void keyReleased(KeyEvent e);
	}
