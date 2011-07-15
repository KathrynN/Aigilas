﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace OGUR.Sprites
{
    public class SpriteSheetManager
    {
        private static Dictionary<SpriteType, SpriteInfo> m_manager = new Dictionary<SpriteType, SpriteInfo>()
        {
            {SpriteType.EMPTY, new SpriteInfo(0, 1)},
            {SpriteType.PLAYER_STAND,new SpriteInfo(1, 2)},
            {SpriteType.FLOOR, new SpriteInfo(2, 1)},
            {SpriteType.WALL, new SpriteInfo(3, 1)},
            {SpriteType.UPSTAIRS,new SpriteInfo(4, 1)},
            {SpriteType.DOWNSTAIRS,new SpriteInfo(5, 1)},            
            {SpriteType.CREATURE,new SpriteInfo(6, 1)},
            {SpriteType.ITEM,new SpriteInfo(7, 1)},
            {SpriteType.SKILL_EFFECT,new SpriteInfo(8, 1)},
            {SpriteType.ALTAR,new SpriteInfo(9, 1)}
        };

        public static SpriteInfo GetSpriteInfo(SpriteType spriteName)
        {
            return m_manager[spriteName];
        }
    }
}