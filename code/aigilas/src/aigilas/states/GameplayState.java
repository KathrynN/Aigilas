package aigilas.states;import spx.entities.EntityManager;import spx.io.Client;import spx.states.State;import xna.wrapper.Console;import aigilas.dungeons.DungeonFactory;public class GameplayState implements State {	public GameplayState() {		Console.WriteLine("Generating the dungeon...");		EntityManager.Reset();		DungeonFactory.Start();		Client.Get().DungeonHasLoaded();	}	@Override	public void Update() {		EntityManager.Update();	}	@Override	public void LoadContent() {		EntityManager.LoadContent();	}	@Override	public void Draw() {		EntityManager.Draw();	}}