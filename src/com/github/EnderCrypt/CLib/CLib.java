package com.github.EnderCrypt.CLib;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

public class CLib
	{
	JFrame jframe;
	Dimension tileNumber;
	CLibPanel clibPanel;
	CLibTileset tileset;
	CLibTile[][] screen;
	public CLib(String title, Dimension tileNumber)
		{
		this.tileNumber = tileNumber;
		// JFrame
		jframe = new JFrame(title);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		// JPanel
		clibPanel = new CLibPanel(this);
		jframe.add(clibPanel);
		}
	public void loadGraphics(File file, Dimension tileNumber, Dimension tileSize) throws IOException
		{
		tileset = new CLibTileset(file, tileNumber, tileSize);
		}
	public void start()
		{
		createScreen();
		jframe.setSize((tileset.tileSize.width*tileNumber.width),(tileset.tileSize.height*tileNumber.height));
		jframe.setVisible(true);
		redraw();
		}
	private void createScreen()
		{
		screen = new CLibTile[tileNumber.width][tileNumber.height];
		for (int y=0;y<tileNumber.height;y++)
			{
			for (int x=0;x<tileNumber.width;x++)
				{
				screen[x][y] = new CLibTile(tileset);
				}
			}
		}
	public void clearScreen()
		{
		for (int y=0;y<tileNumber.height;y++)
			{
			for (int x=0;x<tileNumber.width;x++)
				{
				put(x, y, 0);
				}
			}
		}
	public void put(int x, int y, int tile)
		{
		screen[x][y].setTile(tile);
		}
	public void redraw()
		{
		jframe.repaint();
		}
	}
