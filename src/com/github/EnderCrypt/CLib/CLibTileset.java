package com.github.EnderCrypt.CLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class CLibTileset
	{
	BufferedImage[] tiles;
	Dimension tileNumber;
	Dimension tileSize;
	HashMap<Color, Image[]> cache = new HashMap<>();
	public CLibTileset(File file, Dimension tileSize) throws IOException
		{
		this.tileSize = tileSize;
		BufferedImage tileset = ImageIO.read(file);
		tileNumber = new Dimension((int)(tileset.getWidth()/tileSize.width), (int)(tileset.getHeight()/tileSize.height));
		tiles = new BufferedImage[tileNumber.width*tileNumber.height];
		int index = 0;
		for (int y=0;y<tileNumber.height;y++)
			{
			for (int x=0;x<tileNumber.width;x++)
				{
				BufferedImage tile = tileset.getSubimage(x*tileSize.width, y*tileSize.height, tileSize.width, tileSize.height);
				tiles[index] = tile;
				index++;
				}
			}
		System.out.println("Loaded "+(tileNumber.width*tileNumber.height)+" ("+tileNumber.width+"x"+tileNumber.height+") tiles");
		}
	public Dimension getTileDimension()
		{
		return tileNumber;
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
	private Image createTile(int index, Color color)
		{
		int intColor = getIntFromColor(color);
		int markerRGB = new Color(0,0,0).getRGB() | 0xFF000000;
		ImageFilter imageFilter = new RGBImageFilter()
			{
			@Override
			public final int filterRGB(int x, int y, int rgb)
				{
				if ((rgb | 0xFF000000) == markerRGB)
					{
					return rgb;
					}
				return 0xFF000000 | intColor;
				}
			};
		ImageProducer ip = new FilteredImageSource(tiles[index].getSource(), imageFilter);
		return Toolkit.getDefaultToolkit().createImage(ip);
		}
	public Image getTile(int index, Color color)
		{
		Image[] tileset_ref = null;
		Image tile_ref = null;
		if (cache.containsKey(color))
			{
			tileset_ref = cache.get(color);
			Image tile = tileset_ref[index];
			if (tile == null)
				{
				tile_ref = createTile(index, color);
				}
			else
				{
				return tile;
				}
			}
		else
			{
			tileset_ref = new Image[tiles.length];
			cache.put(color, tileset_ref);
			tile_ref = createTile(index, color);
			}
		tileset_ref[index] = tile_ref;
		return tile_ref;
		}
	}
