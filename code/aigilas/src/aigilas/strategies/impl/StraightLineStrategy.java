package aigilas.strategies.impl; import com.badlogic.gdx.graphics.Texture;  import com.badlogic.gdx.graphics.Color;import spx.core.GameManager;import aigilas.creatures.ICreature;import aigilas.strategies.IStrategy;import aigilas.strategies.Strategy;public class StraightLineStrategy extends IStrategy {	public StraightLineStrategy(ICreature parent, int... targetTypes)	{		super(parent, Strategy.StraightLine);		for (int targetType : targetTypes) {			_targets.AddTargetTypes(targetType);		}	}	@Override	public void Act() {		_parent.MoveIfPossible(0, 1);		if (_parent.GetLocation().GridY >= GameManager.TileMapHeight - 1) {			_parent.SetInactive();		}	}}