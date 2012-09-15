package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.items.ItemFactory;
import aigilas.reactions.IReaction;

public class CraftsmanReaction implements IReaction {
	@Override
	public void Affect(ICreature target)

	{
		try {
			ItemFactory.CreateRandomPlain(target.GetLocation());
		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}
}