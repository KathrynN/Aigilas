package aigilas.entities; import com.badlogic.gdx.graphics.Texture;  import com.badlogic.gdx.graphics.Color;import spx.core.Point2;import spx.entities.Entity;import spx.entities.EntityType;public class EntityFactory {	public static Entity Create(int type, Point2 location) {		switch (type) {		case EntityType.FLOOR:			return new Floor(location);		case aigilas.EntityType.WALL:			return new Wall(location);		case aigilas.EntityType.DOWNSTAIRS:			return new Downstairs(location);		case aigilas.EntityType.UPSTAIRS:			return new Upstairs(location);		default:			try {				throw new Exception(						"An undefined int case was passed into the EntityFactory.");			} catch (Exception e) {				e.printStackTrace();			}			return null;		}	}}