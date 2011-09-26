﻿using System;
using OGUR.Collision;
using OGUR.GameObjects;
using OGUR.Sprites;

namespace OGUR.Creatures
{
    public class CreatureFactory
    {
        private static int s_playerCount = 0;
        private static Random s_rand;
        public static ICreature Create(CreatureType type, Point2 position)
        {
            if (s_rand == null)
                s_rand = new Random();
            AbstractCreature result;
            switch (type)
            {
                case CreatureType.PLAYER:
                    result = new Player(s_playerCount++);
                    break;
                default:
                    result = GenerateCreature(type);
                    break;
            }
            result.Setup(position);
            GameplayObjectManager.AddObject(result);
            return result;
        }

        private static AbstractCreature GenerateCreature(CreatureType type)
        {
            switch(type)
            {
                case CreatureType.GOBLIN:
                    return new Goblin();
                case CreatureType.ZORB:
                    return new Zorb();
                default:
                    throw new Exception("You forgot to define Factory generation logic for: "+type);
            }
        }

        public static ICreature CreateRandom(Point2 randomPoint)
        {
            //TODO: This doesn't produce a very random distribution for some reason
            var val = s_rand.Next(1, Enum.GetValues(typeof(CreatureType)).Length - 1);
            return Create((CreatureType)val, randomPoint);
        }

        public static void ResetPlayerCount()
        {
            s_playerCount = 0;
        }

        public static void IncreasePlayerCount()
        {
            s_playerCount ++;
        }

        public static int GetPlayerCount()
        {
            return s_playerCount;
        }
    }
}