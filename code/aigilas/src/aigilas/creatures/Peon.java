package aigilas.creatures; import aigilas.entities.Elements;public class Peon extends AbstractCreature {	public Peon() {		super(AigilasActorType.PEON);		Weaknesses(StatType.STRENGTH, StatType.HEALTH, StatType.MOVE_COOL_DOWN);		Compose(Elements.EARTH);	}}