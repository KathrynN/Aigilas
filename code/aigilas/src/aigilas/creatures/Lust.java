package aigilas.creatures; import com.badlogic.gdx.graphics.Texture;  import com.badlogic.gdx.graphics.Color;import aigilas.entities.Elements;import aigilas.management.SpriteType;import aigilas.skills.SkillId;public class Lust extends AbstractCreature {	public Lust() {		super(AigilasActorType.LUST, SpriteType.LUST);		Compose(Elements.FIRE);		Strengths(StatType.STRENGTH, StatType.STRENGTH);		Add(SkillId.BRIMSTONE);	}}