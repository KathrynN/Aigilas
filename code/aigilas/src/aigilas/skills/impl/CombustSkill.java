package aigilas.skills.impl; import com.badlogic.gdx.graphics.Texture;  import com.badlogic.gdx.graphics.Color;import java.util.List;import spx.entities.EntityManager;import spx.entities.IActor;import aigilas.creatures.ICreature;import aigilas.creatures.StatType;import aigilas.entities.Elements;import aigilas.skills.AnimationType;import aigilas.skills.ISkill;import aigilas.skills.SkillId;import aigilas.statuses.Status;import aigilas.statuses.StatusFactory;public class CombustSkill extends ISkill {	private static final int CombustDistance = 1;	public CombustSkill()	{		super(SkillId.COMBUST, AnimationType.RANGED);		Add(Elements.AIR, Elements.PHYSICAL);		AddCost(StatType.MANA, 10);	}	@Override	public void Affect(ICreature target)	{		target.ApplyDamage(10, _source);		if (!target.IsActive()) {			List<IActor> targets = EntityManager.GetActorsSurrounding(					target.GetLocation(), CombustDistance);			for (int ii = 0; ii < targets.size(); ii++) {				StatusFactory.Apply((ICreature) targets.get(ii), Status.Burn);			}		}	}}