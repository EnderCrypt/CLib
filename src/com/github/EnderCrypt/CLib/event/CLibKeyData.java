package com.github.EnderCrypt.CLib.event;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class CLibKeyData
	{
	private final KeyEvent keyEvent;
	private final List<Integer> keysDown;
	CLibKeyData(List<Integer> keysDown)
		{
		this.keyEvent = null;
		this.keysDown = keysDown;
		}
	CLibKeyData(KeyEvent keyEvent, List<Integer> keysDown)
		{
		this.keyEvent = keyEvent;
		this.keysDown = keysDown;
		}
	public KeyEvent getKeyEvent()
		{
		return keyEvent;
		}
	public List<Integer> getKeysDown()
		{
		return new ArrayList<Integer>(keysDown);//keysDown.toArray(new Integer[keysDown.size()]);
		}
	public boolean isKeyDown(int key)
		{
		for (int keyDown:keysDown)
			{
			if (keyDown == key)
				{
				return true;
				}
			}
		return false;
		}
	}
