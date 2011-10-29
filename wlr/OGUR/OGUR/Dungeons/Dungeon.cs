﻿using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.Collision;
using OGUR.GameObjects;
using OGUR.Gods;
using OGUR.Items;
using OGUR.Sprites;
using OGUR.Creatures;

namespace OGUR.Dungeons
{
    public class Dungeon
    {
        private class PointPoint
        {
            public readonly int X;
            public readonly int Y;
            private readonly bool m_isHorizontal;

            public PointPoint(int x, int y, bool destroyHorizontal = false)
            {
                X = x;
                Y = y;
                m_isHorizontal = destroyHorizontal;
            }
            public bool isHorizontal()
            {
                return m_isHorizontal;
            }
        }

        private static readonly int m_blocksHigh = DungeonFactory.BlocksHigh;
        private static readonly int m_blocksWide = DungeonFactory.BlocksWide;

        private readonly List<Room> m_rooms = new List<Room>();
        private List<GameplayObject> m_contents = new List<GameplayObject>();
        private readonly GameplayObject[,] dungeon = new GameplayObject[m_blocksWide,m_blocksHigh];
        private Point2 downSpawnLocation = new Point2(0, 0);
        private Point2 upSpawnLocation = new Point2(0, 0);

        public Dungeon()
        {
            Generate();
        }

        public Dungeon(Location target)
        {
            Init();
            ConvertRoomsToWalls();
            PlaceAltars();
            PlaceStairs();
            PlaceFloor();
            TransferDungeonState();
        }

        private void Generate()
        { 
            GameplayObjectManager.Clear();
            Init();
            PlaceRooms();
            ConvertRoomsToWalls();
            PlaceStairs();
            PlaceCreatures(new Random().Next(3, 10));
            PlaceItems(new Random().Next(0, 5));
            PlaceFloor();
            TransferDungeonState();
        }

        private void PlaceAltars()
        {
            var startX = 8;
            const int startY = 10;
            foreach(God.Name god in OGUR.Util.EnumUtil.GetValues(typeof(God.Name)))
            {
                dungeon[startX,startY] = new Altar(new Point2(startX,startY),god);
                startX += 2;
            }
        }

        public void LoadTiles(bool goingUp)
        {
            GameplayObjectManager.Clear();
            PlaceFloor();
            m_contents.AddRange(DungeonFactory.FlushCache());
            var spawn = goingUp ? upSpawnLocation : downSpawnLocation;
            foreach (
                ICreature player in
                    m_contents.Where(tile => tile.GetObjectType() == GameObjectType.CREATURE).Where(
                        creature => ((ICreature) creature).GetCreatureType() == CreatureType.PLAYER))
            {
                player.SetLocation(spawn);
            }
            foreach (var item in m_contents)
            {
                GameplayObjectManager.AddObject(item);
            }
        }

        public void CacheContents()
        {
            foreach (var player in m_contents.Where(o => o.GetObjectType() == GameObjectType.CREATURE).Cast<ICreature>().Where(o => o.GetCreatureType() == CreatureType.PLAYER))
            {
                DungeonFactory.AddToCache(player);
                GameplayObjectManager.RemoveObject(player);
            }
            m_contents = new List<GameplayObject>(GameplayObjectManager.GetObjectsToCache());
        }

        private void Init()
        {
            m_rooms.Add(new Room(m_blocksHigh, m_blocksWide, 0, 0));
        }

        private void TransferDungeonState()
        {
            foreach (var tile in dungeon)
            {
                if (tile != null)
                {
                    if (tile.GetObjectType() != GameObjectType.FLOOR)
                    {
                        m_contents.Add(tile);
                    }
                    GameplayObjectManager.AddObject(tile);
                }
            }

            var cache = DungeonFactory.FlushCache();
            if (cache.Count() == 0)
            {
                m_contents.Add(CreatureFactory.Create(CreatureType.PLAYER, downSpawnLocation));
            }
            else
            {
                foreach (var player in cache.Cast<ICreature>())
                {
                    player.SetLocation(downSpawnLocation);
                }
                GameplayObjectManager.AddObjects(cache);
                m_contents.AddRange(cache);
            }
            
            //Give player random objects
            for (int ii = 101; ii < 100; ii++)
            {
                GameplayObjectManager.GetObjects(CreatureType.PLAYER).ElementAt(0).PickupItem(ItemFactory.CreateRandomPlain());
            }
           
        }

        private void PlaceItems(int amountToPlace)
        {
            while (amountToPlace > 0)
            {
                amountToPlace--;
                var randomPoint = FindRandomFreeTile();
                dungeon[randomPoint.GridX, randomPoint.GridY] = ItemFactory.CreateRandomPlain(randomPoint);
            }
        }

        private void PlaceFloor()
        {
            for (var ii = 1; ii < m_blocksWide-1; ii++)
            {
                for(var jj = 1;jj<m_blocksHigh-1;jj++)
                {
                    GameplayObjectManager.AddObject(new Floor(new Point2(ii,jj)));
                }
            }
        }

        private void PlaceCreatures(int amountOfCreatures)
        {
            while(amountOfCreatures>0)
            {
                amountOfCreatures--;
                var randomPoint = new Point2(FindRandomFreeTile());
                dungeon[randomPoint.GridX, randomPoint.GridY] = CreatureFactory.CreateRandom(randomPoint);
            }
        }

        private void PlaceRooms()
        {
            var newRooms = new List<Room>();
            var rand = new Random();
            const int maxRoomCount = 15;
            var roomsToPlace = 3 + rand.Next(0, maxRoomCount);
            var attemptCount = 0;
            while (attemptCount < 1000 && roomsToPlace > 0)
            {
                attemptCount++;
                var startX = rand.Next(0, m_blocksWide - 5);
                var startY = rand.Next(0, m_blocksHigh - 5);
                var startWidth = 5 + rand.Next(0, 2);
                var startHeight = 5 + rand.Next(0, 2);
                roomsToPlace--;
                var nextRoom = new Room(startHeight, startWidth, startX, startY);
                var collides = false;
                if(newRooms.Any(room => room.Collides(nextRoom)))
                {
                    collides = true;
                }
                if (!collides && !nextRoom.IsBad())
                {
                    newRooms.Add(nextRoom);
                }
            }
            foreach (var room in newRooms)
            {
                m_rooms.Add(room);
            }
        }

        private Point2 FindRandomFreeTile()
        {
            var rand = new Random();
            while (true)
            {
                var x = rand.Next(0, m_blocksWide);
                var y = rand.Next(0, m_blocksHigh);
                if (dungeon[x, y].GetObjectType() == GameObjectType.FLOOR)
                {
                    return new Point2(x, y);
                }
            }
        }

        private void PlaceStairs()
        {
            PlaceUpstairs();
            PlaceDownstairs();
        }

        private void PlaceDownstairs()
        {
            upSpawnLocation = FindRandomFreeTile();
            dungeon[upSpawnLocation.GridX, upSpawnLocation.GridY] = new Downstairs(upSpawnLocation);
        }

        private void PlaceUpstairs()
        {
            downSpawnLocation = FindRandomFreeTile();
            dungeon[downSpawnLocation.GridX, downSpawnLocation.GridY] = new Upstairs(downSpawnLocation);
        }

         private void ConvertRoomsToWalls()
        {
            var roomCount = 0;
            var dungeonEntrances = new List<PointPoint>();
            foreach (var room in m_rooms)
            {
                var entrances = new List<PointPoint>();
                for (var ii = room.X; ii < room.RightSide; ii++)
                {
                    for (var jj = room.Y; jj < room.BottomSide; jj++)
                    {
                        if (ii == room.X || jj == room.Y || ii == room.RightSide - 1 || jj == room.BottomSide - 1)
                        {
                            if (!room.Corners.Contains(new Point2(ii, jj)))
                            {
                                if ((ii == room.X && ii > 0) || (ii == room.RightSide && ii < m_blocksWide))
                                {
                                    if (IsFloor(ii - 1, jj) && IsFloor(ii + 1, jj))
                                    {
                                        entrances.Add(new PointPoint(ii, jj,true));
                                    }
                                }
                                if ((jj == room.Y && jj > 0) ||
                                    (jj == room.BottomSide && jj < m_blocksHigh))
                                {
                                    if (IsFloor(ii, jj - 1) && IsFloor(ii, jj + 1))
                                    {
                                        entrances.Add(new PointPoint(ii, jj));
                                    }
                                }
                            }
                            dungeon[ii, jj] = GameplayObjectFactory.Create(GameObjectType.WALL, new Point2(ii, jj));
                        }
                        else
                        {
                            dungeon[ii, jj] = GameplayObjectFactory.Create(GameObjectType.FLOOR, new Point2(ii, jj));
                        }
                    }
                }
                if (roomCount > 0 && entrances.Count() > 0)
                {
                    var index = new Random().Next(0, entrances.Count() - 1);
                    var entrance = entrances[index];
                    if (dungeon[entrance.X, entrance.Y].GetObjectType() != GameObjectType.FLOOR)
                    {
                        dungeonEntrances.Add(entrance);
                    }
                }
                roomCount++;
            }
            foreach (var entrance in dungeonEntrances)
            {
                if (entrance.isHorizontal())
                {
                    for(var ii = 1;ii<m_blocksWide-1;ii++)
                    {
                        var currentTarget = new Point2(ii, entrance.Y);
                        if(dungeon[currentTarget.GridX,currentTarget.GridY].GetObjectType()==GameObjectType.WALL)
                        {
                            dungeon[currentTarget.GridX, currentTarget.GridY] = GameplayObjectFactory.Create(GameObjectType.FLOOR, currentTarget);
                        }
                    }
                }
                else
                {
                    for (var ii = 1; ii < m_blocksHigh - 1; ii++)
                    {
                        var currentTarget = new Point2(entrance.X, ii);
                        if (dungeon[currentTarget.GridX, currentTarget.GridY].GetObjectType() == GameObjectType.WALL)
                        {
                            dungeon[currentTarget.GridX, currentTarget.GridY] = GameplayObjectFactory.Create(GameObjectType.FLOOR, currentTarget);
                        }
                    }
                }
            }
        }

        private bool IsFloor(int x, int y)
        {
            return dungeon[x, y].GetObjectType() == GameObjectType.FLOOR;
        }
    }
}