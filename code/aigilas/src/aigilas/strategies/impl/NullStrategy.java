package aigilas.strategies.impl; import aigilas.creatures.ICreature;import aigilas.strategies.IStrategy;import aigilas.strategies.Strategy;public class NullStrategy extends IStrategy {	public NullStrategy(ICreature parent, int... targetTypes)	{		super(parent, Strategy.Null);		for (int targetType : targetTypes) {			_targets.AddTargetTypes(targetType);		}	}	@Override	public void Act() {	}}