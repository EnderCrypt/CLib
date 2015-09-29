package com.github.EnderCrypt.CLib;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class CLibTile
	{
	Color backgroundColor = Color.BLACK;
	Color frontColor = Color.WHITE;
	int intFrontColor = getIntFromColor(frontColor);
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
		intFrontColor = getIntFromColor(frontColor);
		}
	public void setBackground(Color color)
		{
		backgroundColor = color;
		}
	private int getIntFromColor(Color color)
		//(SOURCE) http://stackoverflow.com/questions/18022364/how-to-convert-rgb-color-to-int-in-java
		//(EXTRA) http://stackoverflow.com/questions/22719275/java-graphics2d-draw-image-with-transparent-color
		{
		int Red = (color.getRed() << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
		int Green = (color.getGreen() << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
		int Blue = color.getBlue() & 0x000000FF; //Mask out anything not blue.
		
		return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
		}
	public void draw(Graphics2D g2d, Point location)
		{
		g2d.setColor(backgroundColor);
		g2d.fillRect(location.x, location.y, tileset.tileSize.width, tileset.tileSize.height);
		//g2d.setColor(frontColor);
		int markerRGB = new Color(0,0,0).getRGB() | 0xFF000000;
		ImageFilter imageFilter = new RGBImageFilter()
			{
			@Override
			public int filterRGB(int x, int y, int rgb)
				{
				if ((rgb | 0xFF000000) == markerRGB)
					{
					return rgb;
					}
				//return 0x00FFFFFF & intFrontColor;
				//return 0xFF000000 & intFrontColor;
				return 0xFF000000 | intFrontColor;
				}
			};
		ImageProducer ip = new FilteredImageSource(tile.getSource(), imageFilter);
		Image image = Toolkit.getDefaultToolkit().createImage(ip);
		g2d.drawImage(image, location.x, location.y, null);
		}
	}
