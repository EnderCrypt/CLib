package com.github.EnderCrypt.CLib;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CLibTileset
	{
	BufferedImage[] tiles;
	Dimension tileNumber;
	Dimension tileSize;
	public CLibTileset(File file, Dimension tileNumber, Dimension tileSize) throws IOException
		{
		this.tileNumber = tileNumber;
		this.tileSize = tileSize;
		BufferedImage tileset = ImageIO.read(file);
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
		}
	public BufferedImage getTile(int index)
		{
		return tiles[index];
		}
	}
