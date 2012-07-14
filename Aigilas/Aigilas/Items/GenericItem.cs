﻿using System;
using System.Collections.Generic;
using Aigilas.Creatures;
using SPX.Core;
using SPX.Entities;
using Aigilas.Management;

namespace Aigilas.Items
{
    public class GenericItem : Entity
    {
        public Stats Modifers;
        public String Name;
        private string _suffix;
        private string _prefix;
        private int _type;
        private Slots _targetSlots;

        private const string __spacingCharacter = " ";

        public int GetItemClass()
        {
            switch (_type)
            {
                case ItemName.Sword:
                    return ItemClass.Melee_Weapon;
                case ItemName.Arrow:
                    return ItemClass.Ranged_Ammo;
                case ItemName.Bow:
                    return ItemClass.Ranged_Weapon;
                case ItemName.Shield:
                    return ItemClass.Shield;
                case ItemName.Pants:
                    return ItemClass.Leggings;
                case ItemName.Dagger:
                    return ItemClass.Melee_Weapon;
                case ItemName.Staff:
                    return ItemClass.Melee_Weapon;
                case ItemName.Hood:
                    return ItemClass.Head_Gear;
                case ItemName.Shirt:
                    return ItemClass.Torso_Garb;
                default:
                    return ItemClass.NULL;
            }
        }

        private void Initialize(string suffix, string prefix,int type, Slots targetSlots,Stats modifiers,Point2 location)
        {
            Setup(location, type);
            _suffix = suffix;
            _prefix = prefix;
            _type = type;
            _targetSlots = GetSlotFromType(type);
            Name = (_prefix == ItemPrefix.NULL ? String.Empty :  _prefix + __spacingCharacter) +
                    ItemName.Names[_type] +
                   (_suffix == ItemSuffix.NULL ? String.Empty : __spacingCharacter + _suffix);
            Modifers = new Stats(modifiers);
        }

        public GenericItem(GenericItem item,Point2 location)
        {
            Initialize(item._suffix, item._prefix, item._type,item._targetSlots, item.Modifers,location);
        }

        public GenericItem(Stats modifiers, string suffix, string prefix, int type, Point2 location,bool onGround = true)
        {
            if (type == ItemName.NULL)
            {
                throw new Exception("Invalid type NULL passed into the GenericItem factory!");
            }
            Initialize(_suffix,_prefix,type,GetSlotFromType(type),modifiers,location);
        }

        protected void Setup(Point2 location, int type)
        {
            Initialize(location, SpriteFromItem(type), Aigilas.EntityType.ITEM,ZDepth.Item);
        }

        private Player _currentTarget;
        public override void Update()
        {
            base.Update();
            if (_isOnBoard)
            {
                _currentTarget = EntityManager.GetTouchingCreature(this) as Player;
                if (_currentTarget != null)
                {
                    if (_currentTarget.IsInteracting())
                    {
                        _currentTarget.PickupItem(this);
                    }
                }
            }
        }

        private int SpriteFromItem(int item)
        {
            return SpriteType.ITEM;
        }

        private Slots GetSlotFromType(int type)
        {
            switch (type)
            {
                case ItemName.Sword:
                    return new Slots(new List<int>() {ItemSlot.RIGHT_HAND, ItemSlot.LEFT_HAND});
                case ItemName.Pants:
                    return new Slots(new List<int>() {ItemSlot.LEGS});
                default:
                    return new Slots(new List<int>());
            }
        }

        public override bool Equals(object obj)
        {
            if(obj.GetType()==typeof(GenericItem))
            {
                var gI = (GenericItem)obj;
                if (Name != gI.Name)
                {
                    return false;
                }
                foreach (string stat in StatType.Values)
                {
                    if (Modifers.Get(stat) != gI.Modifers.Get(stat))
                    {
                        return false;
                    }
                }    
                return true;
            }
            return false;
        }

        public override int GetHashCode()
        {
            return Name.GetHashCode() + Modifers.GetHashCode();
        }

        public static bool operator ==(GenericItem a, GenericItem b)
        {
            if (System.Object.ReferenceEquals(a, b))
            {
                return true;
            }

            if (((object) a == null) || ((object) b == null))
            {
                return false;
            }

            return a.Equals(b);
        }

        public static bool operator !=(GenericItem a, GenericItem b)
        {
            return !(a == b);
        }

        public float GetStatBonus(string stat)
        {
            return Modifers.Get(stat);
        }
    }
}