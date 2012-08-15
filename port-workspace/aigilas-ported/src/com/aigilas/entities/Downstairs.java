package com.aigilas.entities;
    public class Downstairs  extends  Entity
    {
        public Downstairs(Point2 location)
        {
            Initialize(location, SpriteType.DOWNSTAIRS, com.aigilas.EntityType.DOWNSTAIRS,com.aigilas.Depth.Stairs);
        }

        private ICreature player;
@Override
        {
            player = (ICreature)EntityManager.GetTouchingCreature(this);
            if (player != null)
            {
                if (player.IsInteracting())
                {
                    player.PerformInteraction();
                    try {
                }
            }
        }
    }