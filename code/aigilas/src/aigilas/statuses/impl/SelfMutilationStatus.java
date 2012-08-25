package aigilas.statuses.impl; import aigilas.creatures.ICreature;import aigilas.statuses.IStatus;import aigilas.strategies.Strategy;import aigilas.strategies.StrategyFactory;public class SelfMutilationStatus extends IStatus {	private int previousStrategy;	public SelfMutilationStatus(ICreature target)	{		super(target);	}	@Override	public void Setup() {		super.Setup();		previousStrategy = _target.GetStrategyId();		_target.SetStrategy(StrategyFactory				.Create(Strategy.AttackSelf, _target));	}	@Override	public void Cleanup() {		super.Cleanup();		_target.SetStrategy(StrategyFactory.Create(previousStrategy, _target));	}}