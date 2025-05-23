package shapes;

import java.awt.Font;
import java.awt.Graphics2D;

public class Text extends Shape {
	private String text;
	private int posX;
	private int posY;
	private String fontName = "Ariel";
	private int fontSize = 16;
	
	public Text(String id, String text, int posX, int posY) {
		super(id);
		this.posX = posX;
		this.posY = posY;
		this.text = text;
	}
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}	
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int size) {
		this.fontSize = size;
	}
	public String getFontName() {
		return fontName;
	}
	
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		 g.setFont(new Font(fontName, Font.BOLD, fontSize));;
		 g.drawString(text, posX, posY);
	}
	
	@Override
	public boolean isInArea(int x, int y) {
		return false;
	}
	
	@Override
	public void move(int dx, int dy) {
		this.posX += dx;
		this.posY += dy;
	}
	
	@Override
	public void moveToLocation(int x, int y) {
		this.posX = x;
		this.posY = y;
	}	
	

}
