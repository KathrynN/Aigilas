package aigilas.entities;import spx.core.Point2;import spx.entities.Entity;import spx.entities.EntityType;import aigilas.management.SpriteType;public class Floor extends Entity {	public Floor(Point2 location) {		Initialize(location, SpriteType.FLOOR, EntityType.FLOOR,				aigilas.Depth.Floor);	}}