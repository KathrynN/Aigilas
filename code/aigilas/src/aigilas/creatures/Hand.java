package aigilas.creatures; import com.badlogic.gdx.graphics.Texture;  import com.badlogic.gdx.graphics.Color;import aigilas.entities.Elements;import aigilas.management.SpriteType;import aigilas.strategies.Strategy;import aigilas.strategies.StrategyFactory;public class Hand extends AbstractCreature {	public Hand() {		super(AigilasActorType.HAND, SpriteType.HAND);		Compose(Elements.PHYSICAL);		Strengths(StatType.STRENGTH, StatType.STRENGTH);		_isBlocking = false;		_strategy = StrategyFactory.Create(Strategy.StraightLine, this);	}}