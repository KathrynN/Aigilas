package com.aigilas.states;
    public class GameplayState  implements  State
    {
        public GameplayState()
        {
            Console.WriteLine("Generating the dungeon...");
            EntityManager.Reset();
            DungeonFactory.Start();
            Client.Get().DungeonHasLoaded();
        }
        public void Update()
        {
            EntityManager.Update();
        }
        public void LoadContent()
        {
            EntityManager.LoadContent();
        }
        public void Draw()
        {
            EntityManager.Draw();
        }
    }