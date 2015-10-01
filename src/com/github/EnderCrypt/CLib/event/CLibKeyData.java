package com.github.EnderCrypt.CLib.event;

import java.awt.event.KeyEvent;
import java.util.List;

public class CLibKeyData
	{
	private final KeyEvent keyEvent;
	public final int[] keysPressed;
	CLibKeyData(KeyEvent keyEvent, List<Integer> keysDown)
		{
		this.keyEvent = keyEvent;
		
		int[] temp = new int[keysDown.size()];
		for (int i=0;i<keysDown.size();i++)
			{
			temp[i] = keysDown.get(i);
			}
		keysPressed = temp;
		}
	public KeyEvent getKeyEvent()
		{
		return keyEvent;
		}
	}
