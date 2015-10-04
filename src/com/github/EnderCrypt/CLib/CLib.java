package com.github.EnderCrypt.CLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.github.EnderCrypt.CLib.event.CLibKeylistener;
import com.github.EnderCrypt.CLib.event.CLibMouselistener;

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
		}
	public void loadInternalGraphics(File file, Dimension tileSize) throws IOException
		{
		//TODO: needs testing (loads from a tileset inside library jar)
		Image tilesetImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/BLOCK.png"));
		tileset = new CLibTileset((BufferedImage)tilesetImage, tileSize);
		prepare();
		}
	public void loadGraphics(File file, Dimension tileSize) throws IOException
		{
		BufferedImage tilesetImage = ImageIO.read(file);
		tileset = new CLibTileset(tilesetImage, tileSize);
		prepare();
		}
	public Dimension getScreenSize()
		{
		return tileNumber.getSize();
		}
	public Point getMousePosition()
		{
		return clibPanel.cLibListener.mousePosition.getLocation();
		}
	public Point getTileMousePosition()
		{
		return clibPanel.cLibListener.tileMousePosition.getLocation();
		}
	private void prepare()
		{
		// JPanel
		clibPanel = new CLibPanel(this);
		jframe.getContentPane().add(clibPanel);
		//screen
		createScreen();
		resize();
		redraw();
		jframe.setVisible(true);
		clibPanel.requestFocus();
		}
	public void setMousePointer(boolean usePointer)
		{
		clibPanel.drawTileMousePointer = usePointer;
		}
	public void updateListeners()
		{
		clibPanel.cLibListener.update();
		}
	public void preloadTileset(Color color)
		{
		for (int i=0;i<tileset.tiles.length;i++)
			{
			preloadTile(i, color);
			}
		}
	public void preloadTile(int index, Color color)
		{
		//TODO: improve efficiency
		tileset.getTile(index, color);
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
	public int getCacheCount()
		{
		return tileset.cacheCount;
		}
	public int getPaintDelta()
		{
		return clibPanel.paintMilli;
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
	public Color getFrontColor(int x, int y)
		{
		return screen[y][x].frontColor;
		}
	public Color getBackColor(int x, int y)
		{
		return screen[y][x].backgroundColor;
		}
	public char getTileChar(int x, int y)
		{
		return (char) screen[y][x].tileID;
		}
	public CLibTile getTile(int x, int y)
		{
		return screen[y][x];
		}
	public int put(int x, int y, int tileID)
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
		return x+1;
		}
	public int put(int x, int y, int[] data)
		{
		return put(x, y, data, false);
		}
	private int put(int x, int y, int[] data, boolean pushUpAllowed)
		{
		int draw_x = x;
		int draw_y = y;
		for (int i=0;i<data.length;i++)
			{
			char letter = (char) data[i];
			if (draw_x >= tileNumber.width)
				{
				return draw_x;
				}
			put(draw_x, draw_y, letter);
			draw_x++;
			}
		return draw_x;
		}
	public int put(int x, int y, String text)
		{
		return put(x, y, text, false);
		}
	private int put(int x, int y, String text, boolean pushUpAllowed)
		{
		int draw_x = x;
		int draw_y = y;
		for (int i=0;i<text.length();i++)
			{
			char letter = text.charAt(i);
			if (draw_x >= tileNumber.width)
				{
				return draw_x;
				}
			put(draw_x, draw_y, letter);
			draw_x++;
			}
		return draw_x;
		}
	public int println(int[] data)
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
		return put(0, tileNumber.height-1, data, true);
		}
	public int println(String text)
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
		return put(0, tileNumber.height-1, text, true);
		}
	public void redraw()
		{
		jframe.repaint();
		}
	// listeners	
	public List<Integer> getKeysDebug()
		{
		return clibPanel.cLibListener.keysPressed;
		}
	public void addKeyListener(CLibKeylistener keyListener)
		{
		clibPanel.cLibListener.add(keyListener); 
		}
	public void addMouseListener(CLibMouselistener mouseListener)
		{
		clibPanel.cLibListener.add(mouseListener);
		}
	public void addNativeKeyListener(KeyListener keyListener)
		{
		clibPanel.addKeyListener(keyListener);
		}
	public void addNativeMouseListener(MouseListener mouseListener)
		{
		clibPanel.addMouseListener(mouseListener);
		}
	}
