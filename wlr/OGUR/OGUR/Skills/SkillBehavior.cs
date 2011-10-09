﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Sprites;
using OGUR.Creatures;
using OGUR.Collision;
using OGUR.GameObjects;

namespace OGUR.Skills
{
    public class SkillBehavior
    {
        protected SideEffects m_sideEffects;
        protected ISkill m_parent;
        protected bool m_used = false;
        protected Stats m_cost;

        public SkillBehavior(SpriteType effectGraphic, Skill.Animation animation,ISkill parentSkill)
        {
            m_parent = parentSkill;
            m_sideEffects = new SideEffects(effectGraphic, animation,m_parent);
            m_cost = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
        public SpriteType GetSpriteType() { return m_sideEffects.GetSpriteType(); }
        public virtual void Activate(ICreature target) { }
        public virtual void Cleanup(ICreature target) { }
        public void AddCost(StatType stat, float cost)
        {
            m_cost.AddBuff(new StatBuff(stat, cost));
        }
        protected bool SubtractCost(ICreature owner)
        {
            bool costPaid = false;
            foreach (StatType stat in Enum.GetValues(typeof(StatType)))
            {
                if (owner.LowerStat(stat, m_cost.Get(stat)))
                {
                    costPaid = true;
                }
            }
            return costPaid;
        }
        public virtual bool AffectTarget(ICreature source,SkillEffect graphic)
        {
            var collidedTarget = source.GetTargets().GetCollidedTarget(graphic);
            if (null != collidedTarget)
            {
                m_parent.Affect(collidedTarget);
                if (!m_parent.IsPersistent())
                {
                    return false;
                }
            }
            return true;
        }
        internal Skill.Animation GetAnimationType()
        {
            return m_sideEffects.GetAnimationType();
        }
    }
    public class RangedBehavior: SkillBehavior
    {
        public RangedBehavior(SpriteType effectGraphic, ISkill parentSkill) : base(effectGraphic, Skill.Animation.RANGED, parentSkill) { }
        public override void Activate(ICreature target) { m_sideEffects.Generate(target.GetLocation(), target.GetSkillVector(), target);}
    }
    public class SelfBehavior:SkillBehavior
    {
        public SelfBehavior(SpriteType effectGraphic, ISkill parentSkill) : base(effectGraphic, Skill.Animation.SELF, parentSkill) { }
        public override void Activate(ICreature target) { if (SubtractCost(target)) { m_sideEffects.Generate(target.GetLocation(), new Point2(0, 0), target); } }
        public override void Cleanup(ICreature target) {if(m_used)m_parent.Affect(target);}
        public override bool AffectTarget(ICreature source, SkillEffect graphic)
        {
            if (!m_used)
            {
                m_parent.Affect(source);
                m_used = true;
            }
            return true;
        }
    }
    public class StationaryBehavior : SkillBehavior
    {
        public StationaryBehavior(SpriteType effectGraphic, ISkill parentSkill) : base(effectGraphic, Skill.Animation.STATIONARY, parentSkill) { }
        public override void Activate(ICreature target) 
        {
            if(SubtractCost(target))
            {
                if (m_parent.StartOffCenter)
                {
                    var location = new Point2(target.GetLocation().GridX + target.GetSkillVector().GridX, target.GetLocation().GridY + target.GetSkillVector().GridY);
                    m_sideEffects.Generate(location, new Point2(0,0), target);
                }
                else
                {
                    m_sideEffects.Generate(target.GetLocation(), new Point2(0,0), target);
                }
            }
        }
    }
    public class CloudBehavior:SkillBehavior
    {
        public CloudBehavior(SpriteType effectGraphic, ISkill parentSkill) : base(effectGraphic, Skill.Animation.CLOUD, parentSkill) { }
        public override void Activate(ICreature target)
        {
            if (SubtractCost(target))
            {
                var referencePoint = target.GetLocation();
                for (var ii = -1; ii < 2; ii++)
                {
                    for (var jj = -1; jj < 2; jj++)
                    {
                        if (ii != 0 || jj != 0)
                        {
                            var cloudPosition = new Point2(referencePoint.GridX + ii, referencePoint.GridY + jj);
                            m_sideEffects.Generate(cloudPosition, new Point2(0, 0), target);
                        }
                    }
                }
            }
        }
    }
}