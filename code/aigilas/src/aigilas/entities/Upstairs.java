package aigilas.entities;import spx.core.Point2;import spx.entities.Entity;import spx.entities.EntityManager;import aigilas.creatures.ICreature;import aigilas.creatures.Player;import aigilas.dungeons.DungeonFactory;import aigilas.management.SpriteType;public class Upstairs extends Entity {	public Upstairs(Point2 location) {		Initialize(location, SpriteType.UPSTAIRS,				aigilas.EntityType.UPSTAIRS, aigilas.Depth.Stairs);	}	ICreature player;	@Override	public void Update() {		player = (Player) EntityManager.GetTouchingCreature(this);		if (player != null) {			if (player.IsInteracting()) {				player.PerformInteraction();				try {					DungeonFactory.GetPreviousFloor();				} catch (Exception e) {					e.printStackTrace();				}			}		}	}}