package aigilas.statuses.impl; import com.badlogic.gdx.graphics.Texture;  import com.badlogic.gdx.graphics.Color;import aigilas.creatures.ICreature;import aigilas.entities.Elements;import aigilas.statuses.IStatus;public class PreventMentalUsageStatus extends IStatus {	public PreventMentalUsageStatus(ICreature target)	{		super(target);		_blockedElements.add(Elements.MENTAL);	}}