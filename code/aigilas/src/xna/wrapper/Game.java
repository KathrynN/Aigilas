package xna.wrapper; import com.badlogic.gdx.graphics.Texture;  import com.badlogic.gdx.graphics.Color;public class Game {	public TimeSpan TargetElapsedTime;	public ContentManager Content;	public GraphicsDevice GraphicsDevice;	protected boolean _isRunning = true;	public void Initialize() {		// TODO Auto-generated method stub	}	protected void LoadContent() {		// TODO Auto-generated method stub	}	protected void UnloadContent() {		// TODO Auto-generated method stub	}	protected void Update(GameTime gameTime) {		// TODO Auto-generated method stub	}	public void Update() {		Update(new GameTime());	}	protected void Draw(GameTime gameTime) {		// TODO Auto-generated method stub	}	public boolean IsRunning() {		return _isRunning;	}	public void SetIsRunning(boolean isRunning) {		_isRunning = isRunning;	}}