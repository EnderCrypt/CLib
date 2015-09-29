package com.github.EnderCrypt.CLib;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class CLibTile
	{
	Color backgroundColor = Color.BLACK;
	Color frontColor = Color.WHITE;
	BufferedImage tile;
	CLibTileset tileset;
	public CLibTile(CLibTileset tileset)
		{
		this.tileset = tileset;
		setTile(0);
		}
	public void setTile(int tile)
		{
		setTile(tileset.getTile(tile));
		}
	public void setTile(BufferedImage tile)
		{
		this.tile = tile;
		}
	public void setColor(Color color)
		{
		frontColor = color;
		}
	public void setBackground(Color color)
		{
		backgroundColor = color;
		}
	public void draw(Graphics2D g2d, Point location)
		{
		g2d.setColor(backgroundColor);
		g2d.fillRect(location.x, location.y, tileset.tileSize.width, tileset.tileSize.height);
		g2d.setColor(frontColor);
		g2d.drawImage(tile, location.x, location.y, null);
		}
	}
