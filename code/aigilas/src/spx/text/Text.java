package spx.text; import spx.core.Point2;import xna.wrapper.SpriteBatch;public class Text {	protected String _contents;	protected Point2 _position = new Point2(0, 0);	protected int _textType = TextType.Inventory;	public Text() {	}	public void Reset(String contents, int x, int y) {		_contents = contents;		_position.Reset(x, y);	}	public Text(String contents, int x, int y, int type) {		Reset(contents, x, y);		_textType = type;	}	public int Update() {		return 0;	}	public int GetTextType() {		return _textType;	}	protected void DrawText(SpriteBatch target) {	}	public void Draw() {	}}