package com.github.EnderCrypt.CLib.event;

import java.awt.event.KeyEvent;

public interface CLibKeylistener
	{
	public void keyTyped(CLibKeyData e);
	
	public void keyPressed(CLibKeyData e);
	
	public void keyReleased(CLibKeyData e);
	}
