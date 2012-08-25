package aigilas.states;import spx.core.Point2;import spx.core.XnaManager;import spx.io.Input;import spx.states.State;import spx.states.StateManager;import aigilas.management.Commands;public class GameOverState implements State {	private final Image _menuBase;	public GameOverState() {		_menuBase = XnaManager.GetGameOverAsset();	}	@Override	public void Draw() {		float x = (XnaManager.WindowWidth - _menuBase.getWidth()) / 2;		float y = (XnaManager.WindowHeight - _menuBase.getHeight()) / 2;		XnaManager.Renderer.Draw(_menuBase, new Point2(x, y));	}	@Override	public void Update() {		if (Input.IsActive(Commands.Confirm, 0, true)) {			StateManager.LoadState(new GameplayState());		}	}	@Override	public void LoadContent() {	}}