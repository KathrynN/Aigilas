﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;
using SPX.Entities;
using Microsoft.Xna.Framework;

namespace SPX.Particles
{
    public static class ParticleEngine
    {
        private static Particle2[] __particles = new Particle2[100000];
        private static Emitter[] __emitters = new Emitter[1000];

        public static void Reset()
        {
            for (int ii = 0; ii < __particles.Length; ii++)
            {
                __particles[ii] = new Particle2(null);
                __particles[ii].IsActive = false;
                
            }
            for (int ii = 0; ii < __emitters.Length; ii++)
            {
                __emitters[ii] = new Emitter();
                __emitters[ii].IsActive = false;
            }
        }

        private static int __emitterIndex;
        public static void Emit(ParticleBehavior behavior, Point2 position, Color baseColor)
        {
            while (__emitters[__emitterIndex].IsActive)
            {
                __emitterIndex = (__emitterIndex + 1) % __emitters.Length;
            }

            __emitters[__emitterIndex].Reset(behavior, position, baseColor);
        }

        public static void Emit(ParticleBehavior behavior, IEntity entity,Color baseColor)
        {
            while (__emitters[__emitterIndex].IsActive)
            {
                __emitterIndex = (__emitterIndex + 1) % __emitters.Length;
            }

            __emitters[__emitterIndex].Reset(behavior, entity, baseColor);
        }
        
        public static void Update()
        {
            for (int ii = 0; ii < __emitters.Length; ii++)
            {
                __emitters[ii].Update();
            }
        }

        public static void Draw()
        {
            for (int ii = 0; ii < __emitters.Length; ii++)
            {
                __emitters[ii].Draw();
            }
        }

        private static int __particleIndex;
        public static Particle2 CreateParticle(ParticleBehavior behavior, Point2 position,Color baseColor)
        {
            SetIndexToInactiveParticle();
            __particles[__particleIndex].Reset(behavior, position,baseColor);
            return __particles[__particleIndex];
        }

        public static Particle2 CreateParticle(ParticleBehavior behavior, IEntity entity, Color baseColor)      
        {
            SetIndexToInactiveParticle();
            __particles[__particleIndex].Reset(behavior, entity,baseColor);
            return __particles[__particleIndex];
        }

        private static void SetIndexToInactiveParticle()
        {
            while (__particles[__particleIndex].IsActive)
            {
                __particleIndex = (__particleIndex + 1) % __particles.Length;
            }
        }
    }
}