package spx.text; import com.badlogic.gdx.graphics.Texture;  import com.badlogic.gdx.graphics.Color;import spx.core.Depth;import spx.core.Point2;import spx.core.XnaManager;class DefaultHudText extends Text {	private final Color _color;	private Point2 _origin;	public DefaultHudText(float alpha) {		_color = new Color(255f, 255f, 255f, alpha);	}	public DefaultHudText(String contents, int x, int y, Point2 origin,			float alpha) {		super(contents, x, y, TextType.Inventory);		_color = new Color(255f, 255f, 255f, alpha);		_origin = origin;	}	public void Reset(String contents, int x, int y, Point2 origin) {		_origin = origin;		Reset(contents, x, y);	}	@Override	public int Update() {		return 1;	}	@Override	public void Draw() {		XnaManager.Renderer.DrawString(_contents, _position.Add(_origin),				_color, .75f, Depth.DefaultHudText);	}}