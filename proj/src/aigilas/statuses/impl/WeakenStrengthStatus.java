package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.IStatus;

public class WeakenStrengthStatus extends IStatus {
	public WeakenStrengthStatus(ICreature target)

	{
		super(target);

		_buff = new StatBuff(StatType.STRENGTH, -10);
		Setup();
	}
}