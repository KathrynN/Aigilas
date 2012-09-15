package aigilas.creatures;

import spx.bridge.ActorType;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class AcidNozzle extends Minion {
	public AcidNozzle() {
		super(ActorType.MINION, 50f);
		Add(SkillId.ACID_DRIP);
		_composition.add(Elements.EARTH);
		_strategy = StrategyFactory.Create(Strategy.MinionRotate, this);
	}
}