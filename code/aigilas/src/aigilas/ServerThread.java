package aigilas; import com.badlogic.gdx.graphics.Texture;  import com.badlogic.gdx.graphics.Color;import spx.io.Server;import xna.wrapper.Console;public class ServerThread implements Runnable {	@Override	public void run() {		try {			Console.WriteLine("Server loop");			Server.Get();			Thread.sleep(50);			while (Server.Get().IsOnlyInstance()) {				Server.Get().Update();				Thread.sleep(50);			}		} catch (Exception e) {			Console.WriteLine(e.getMessage());		}		Console.WriteLine("Finished being a server");	}}