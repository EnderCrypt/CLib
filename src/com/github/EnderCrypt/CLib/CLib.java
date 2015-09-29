package com.github.EnderCrypt.CLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
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
	final Color defaultFrontColorBrush = Color.WHITE;
	final Color defaultBackColorBrush = Color.BLACK;
	Color frontColorBrush;
	Color backColorBrush;
	public CLib(String title, Dimension tileNumber)
		{
		this.tileNumber = tileNumber;
		// JFrame
		jframe = new JFrame(title);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.setBackground(defaultBackColorBrush);
		// JPanel
		clibPanel = new CLibPanel(this);
		jframe.getContentPane().add(clibPanel);
		}
	public void loadGraphics(File file, Dimension tileSize) throws IOException
		{
		tileset = new CLibTileset(file, tileSize);
		prepare();
		}
	private void prepare()
		{
		createScreen();
		resize();
		redraw();
		jframe.setVisible(true);
		}
	private void resize()
		{
		int width = (tileset.tileSize.width*tileNumber.width);
		int height = (tileset.tileSize.height*tileNumber.height);
		clibPanel.setPreferredSize(new Dimension(width,height));
		jframe.pack();
		}
	private void createScreen()
		{
		screen = new CLibTile[tileNumber.height][tileNumber.width];
		for (int y=0;y<tileNumber.height;y++)
			{
			for (int x=0;x<tileNumber.width;x++)
				{
				screen[y][x] = new CLibTile(tileset);
				}
			}
		}
	public void clearScreen(Color backgroundColor)
		{
		Color origColor = backColorBrush;
		setBackBrush(backgroundColor);
		clearScreen();
		setBackBrush(origColor);
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
	public void setFrontBrush(Color color)
		{
		if (color == null)
			{
			color = defaultFrontColorBrush;
			}
		frontColorBrush = color;
		}
	public void setBackBrush(Color color)
		{
		if (color == null)
			{
			color = defaultBackColorBrush;
			}
		backColorBrush = color;
		}
	public void put(int x, int y, int tileID)
		{
		CLibTile tile = screen[y][x];
		tile.setTile(tileID);
		if (frontColorBrush != null)
			{
			tile.setColor(frontColorBrush);
			}
		if (backColorBrush != null)
			{
			tile.setBackground(backColorBrush);
			}
		}
	public void put(int x, int y, String text)
		{
		for (int i=0;i<text.length();i++)
			{
			if (((x+i) >= tileNumber.width))
				{
				return;
				}
			put(x+i, y, text.charAt(i));
			}
		}
	public void println(String text)
		{
		//shift
		for (int y=0;y<tileNumber.height-1;y++)
			{
			screen[y] = screen[y+1];
			}
		screen[tileNumber.height-1] = new CLibTile[tileNumber.width];
		for (int x=0;x<tileNumber.width;x++)
			{
			screen[tileNumber.height-1][x] = new CLibTile(tileset);
			}
		//println
		put(0, tileNumber.height-1, text);
		}
	public void redraw()
		{
		jframe.repaint();
		}
	// listeners
	public void addKeyListener(KeyListener keyListener)
		{
		jframe.addKeyListener(keyListener);
		}
	public void addMouseListener(MouseListener mouseListener)
		{
		jframe.addMouseListener(mouseListener);
		}
	}
