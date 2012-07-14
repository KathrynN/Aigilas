﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Aigilas.Entities;
using SPX.Entities;
using SPX.Particles;
using SPX.DevTools;
using Aigilas.Creatures;

namespace Aigilas.Dungeons
{
    public class Location
    {
        public const int Start = 0;
        public const int Depths = 1;
    }
    public static class DungeonFactory
    {
        public static int BlocksHigh = 20;
        public static int BlocksWide = 30;

        private static int __floorCount = 0;

        private static Dictionary<int, DungeonSet> _world = new Dictionary<int, DungeonSet>();
        private static List<Entity> _cache = new List<Entity>(); 

        public static void GetNextFloor()
        {
            _world[Location.Depths].GotoNext();
        }

        public static bool GetPreviousFloor()
        {
            return _world[Location.Depths].GotoPrevious();
        }

        public static void AddToCache(Entity content)
        {
            _cache.Add(content);
        }

        public static List<Entity> FlushCache()
        {
            var result = new List<Entity>(_cache);
            _cache.Clear();
            return result;
        }

        public static void Start()
        {           
            _world = new Dictionary<int, DungeonSet>();
            _cache = new List<Entity>();
            _world.Add(Location.Depths, new DungeonSet());            
            while (CreatureFactory.BossesRemaining() > 0)
            {
                GetNextFloor();
            }
            while (GetPreviousFloor()) { }            
        }

        public static int GetFloorCount()
        {
            return __floorCount;
        }

        public static void IncreaseFloorCount()
        {
            __floorCount++;
        }
    }

    public class DungeonSet
    {
        private int _currentFloor = 0;
        private readonly Dictionary<int,Dungeon> _floors = new Dictionary<int, Dungeon>();

        /*
         * This whole "area" thing is very messy and doesn't work in an intuitive way.
         * In order to pre-load, we need to make sure we can easily get back to start.
         * */
        public DungeonSet()
        {
            _floors.Add(_currentFloor, new Dungeon());
        }

        public void GotoNext()
        {
            _floors[_currentFloor].CacheContents();
            _currentFloor++;
            LoadOrCreateDungeon(false);
        }

        public bool GotoPrevious()
        {
            if (_currentFloor > 0)
            {
                _floors[_currentFloor].CacheContents();
                _currentFloor--;
                LoadOrCreateDungeon(true);
                return true;
            }
            return false;
        }

        private void LoadOrCreateDungeon(bool goingUp)
        {
            if (!_floors.ContainsKey(_currentFloor))
            {
                _floors.Add(_currentFloor, new Dungeon());
                DungeonFactory.IncreaseFloorCount();
            }
            ParticleEngine.Reset();
            _floors[_currentFloor].LoadTiles(goingUp);
        }
    }
}