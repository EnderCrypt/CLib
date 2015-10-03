package com.github.EnderCrypt.CLib;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class CLibTile
	{
	public Color backgroundColor = Color.BLACK;
	public Color frontColor = Color.WHITE;
	private CLibTileset tileset;
	public int tileID;
	public CLibTile(CLibTileset tileset)
		{
		this.tileset = tileset;
		setTile(0);
		}
	public CLibTile(CLibTileset tileset, Color backgroundColor)
		{
		this.tileset = tileset;
		this.backgroundColor = backgroundColor;
		setTile(0);
		}
	public void setTile(int tile)
		{
		tileID = tile;
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
		if (tileID > 0)
			{
			Image tile = tileset.getTile(tileID, frontColor);
			g2d.drawImage(tile, location.x, location.y, null);
			}
		}
	public void drawInvertedColor(Graphics2D g2d, Point location)
		{
		g2d.setColor(invertColor(backgroundColor));
		g2d.fillRect(location.x, location.y, tileset.tileSize.width, tileset.tileSize.height);
		if (tileID > 0)
			{
			Image tile = tileset.getTile(tileID, invertColor(frontColor));
			g2d.drawImage(tile, location.x, location.y, null);
			}
		}
	Color invertColor(Color color) //http://stackoverflow.com/questions/4672271/reverse-opposing-colors
		{
		return new Color(0xFFFFFF - color.getRGB());
		}
	}
