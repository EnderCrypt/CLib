package com.github.EnderCrypt.CLib.event;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class CLibMouseData
	{
	private final MouseEvent mouseEvent;
	public final Point tile;
	CLibMouseData(MouseEvent mouseEvent, Point tileMousePosition)
		{
		this.mouseEvent = mouseEvent;
		tile = tileMousePosition;
		}
	public MouseEvent getMouseEvent()
		{
		return mouseEvent;
		}
	}
