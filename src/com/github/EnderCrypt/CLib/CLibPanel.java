package com.github.EnderCrypt.CLib;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import com.github.EnderCrypt.CLib.event.CLibListener;

@SuppressWarnings("serial")
public class CLibPanel extends JPanel
	{
	CLibListener cLibListener;
	CLib clib;
	int paintMilli = 0;
	boolean drawTileMousePointer;
	public CLibPanel(CLib clib)
		{
		this.clib = clib;
		cLibListener = new CLibListener(this, clib.tileset.tileSize);
		addKeyListener(cLibListener);
		addMouseListener(cLibListener);
		addMouseMotionListener(cLibListener);
		}
	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		long milli = System.currentTimeMillis();
		Graphics2D g2d = (Graphics2D) g;
		// draw tiles
		for (int y=0;y<clib.tileNumber.height;y++)
			{
			for (int x=0;x<clib.tileNumber.width;x++)
				{
				CLibTile tile = clib.screen[y][x];
				Point location = new Point((x*clib.tileset.tileSize.width), (y*clib.tileset.tileSize.height));
				tile.draw(g2d, location);
				}
			}
		paintMilli = (int) (System.currentTimeMillis()-milli);
		//System.out.println("Paint took: "+paintMilli+" Milli");
		//draw tile mouse pointer
		if (drawTileMousePointer)
			{
			Point tileMouse = clib.getTileMousePosition();
			if ((tileMouse.x >= 0) && (tileMouse.y >= 0))
				{
				Point mouse = new Point((tileMouse.x*clib.tileset.tileSize.width), (tileMouse.y*clib.tileset.tileSize.height));
				clib.screen[tileMouse.y][tileMouse.x].drawInvertedColor(g2d, mouse);
				}
			}
		}
	}