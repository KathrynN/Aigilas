package spx.io;import com.badlogic.gdx.Input;public enum Keys {	Up(Input.Keys.UP),	Down(Input.Keys.DOWN),	Left(Input.Keys.LEFT),	Right(Input.Keys.RIGHT),	Enter(Input.Keys.ENTER),	Back(Input.Keys.BACK),	R(Input.Keys.R),	D(Input.Keys.D),	A(Input.Keys.A),	S(Input.Keys.S),	RightControl(Input.Keys.CONTROL_RIGHT),	D1(Input.Keys.NUM_1),	D2(Input.Keys.NUM_2),	D3(Input.Keys.NUM_3),	OemTilde(Input.Keys.TAB),	E(Input.Keys.E),	Space(Input.Keys.SPACE);	private int _keyCode;	private Keys(int keyCode) {		_keyCode = keyCode;	}	public int getKeyCode() {		return _keyCode;	}}