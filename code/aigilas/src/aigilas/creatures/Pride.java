package aigilas.creatures; import aigilas.entities.Elements;import aigilas.management.SpriteType;import aigilas.skills.SkillId;public class Pride extends AbstractCreature {	public Pride() {		super(AigilasActorType.PRIDE, SpriteType.PRIDE);		Compose(Elements.DARK);		Strengths(StatType.STRENGTH, StatType.STRENGTH);		Add(SkillId.BREAKING_WHEEL);	}}