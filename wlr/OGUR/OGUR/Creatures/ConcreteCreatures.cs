﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Skills;
using OGUR.Strategies;
using OGUR.Classes;
using SPX.Sprites;
using Microsoft.Xna.Framework;
using OGUR.Entities;
using OGUR.Gods;
using OGUR.Creatures;

namespace OGUR.Creatures
{
    public class Player : AbstractCreature
    {
        private static readonly List<Color> s_colors = new List<Color>() { Color.Red,Color.Green,Color.Blue,Color.White};
        public Player(int playerIndex): base(OgurActorType.PLAYER, SpriteType.PLAYER_STAND, new GluttonyAcolyte())
        {
            m_playerIndex = playerIndex;
            m_graphic.SetColor(s_colors[m_playerIndex]);
            m_strategy = new ControlledByPlayer(this);
            m_baseStats = new Stats(100f, 100f, 1f, 10f, 11f, 10f, 35f, 50f, 6.0f, 6, 1);
            m_maxStats = new Stats(m_baseStats);
            AssignGod(God.Get(GodId.GLUTTONY));
            Compose(Elements.PHYSICAL);
        }
    }
    class Peon : AbstractCreature
    {
        public Peon(): base(OgurActorType.PEON)
        {
            Weaknesses(StatType.STRENGTH, StatType.HEALTH,StatType.MOVE_COOL_DOWN);
            Compose(Elements.EARTH);
        }
    }
    class Zorb : AbstractCreature
    {
        public Zorb(): base(OgurActorType.ZORB)
        {
            Compose(Elements.PHYSICAL, Elements.FIRE);
            Strengths(StatType.MANA,StatType.HEALTH);
            Weaknesses(StatType.WISDOM,StatType.DEFENSE);
            Add(SkillId.FIREBALL);
        }
    }
}
