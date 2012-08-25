package aigilas.skills.impl;import spx.entities.EntityManager;import aigilas.creatures.AigilasActorType;import aigilas.creatures.CreatureFactory;import aigilas.creatures.ICreature;import aigilas.creatures.StatType;import aigilas.entities.Elements;import aigilas.skills.AnimationType;import aigilas.skills.ISkill;import aigilas.skills.SkillId;public class BreakingWheelSkill extends ISkill {	public BreakingWheelSkill()	{		super(SkillId.BREAKING_WHEEL, AnimationType.SELF);		Add(Elements.DARK);		AddCost(StatType.MANA, 10);	}	@Override	public void Activate(ICreature source) {		CreatureFactory.Create(AigilasActorType.BREAKING_WHEEL,				EntityManager.GetEmptyLocation());	}}