package aigilas.skills.impl;

import spx.bridge.ActorType;
import spx.core.Point2;
import spx.core.RNG;
import spx.core.Settings;
import aigilas.creatures.CreatureFactory;
import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class DismembermentSkill extends ISkill {
	public DismembermentSkill()

	{
		super(SkillId.DISMEMBERMENT, AnimationType.SELF);

		Add(Elements.PHYSICAL);
		AddCost(StatType.MANA, 3);

	}

	@Override
	public void Activate(ICreature target)

	{
		super.Activate(target);
		int openCell = RNG.Next(1, Settings.Get().tileMapWidth - 1);
		for (int ii = 1; ii < Settings.Get().tileMapWidth - 1; ii++) {
			if (ii != openCell) {
				CreatureFactory.Create(ActorType.HAND, new Point2(ii, 1));

			}

		}

	}

}