package xna.wrapper;import spx.core.Point2;public class SpriteBatch {	private static final Color __defaultFilter = Color.white;	private static final float __defaultDepth = 1f;	private static final float __defaultAlpha = 1f;	private Graphics _graphics;	public void Draw(Image image, Point2 location, float depth, Color filter) {		Render(image, location, filter, depth, __defaultAlpha);	}	public void Draw(Image image, Point2 location, float depth, float alpha) {		Render(image, location, __defaultFilter, depth, alpha);	}	public void Draw(Image image, Point2 location, float depth) {		Render(image, location, __defaultFilter, depth, __defaultAlpha);	}	public void Draw(Image image, Point2 location) {		Render(image, location, __defaultFilter, __defaultDepth, __defaultAlpha);	}	private void Render(Image image, Point2 location, Color filter,			float depth, float alpha) {		image.draw(location.PosX, location.PosY, filter);	}	public void DrawString(String content, Point2 location, Color filter,			float scale, float depth) {		RenderString(content, location, filter, scale, depth);	}	private void RenderString(String content, Point2 location, Color filter,			float scale, float depth) {		Color temp = _graphics.getColor();		_graphics.scale(scale, scale);		_graphics.setColor(filter);		_graphics.drawString(content, location.PosX, location.PosY);		_graphics.setColor(temp);		_graphics.resetTransform();	}	public void RegisterGraphicsDevice(Graphics g) {		_graphics = g;	}}