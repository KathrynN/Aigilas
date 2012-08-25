package aigilas.items; import java.util.ArrayList;import java.util.Arrays;import spx.core.Point2;import spx.entities.ActorType;import spx.entities.Entity;import spx.entities.EntityManager;import spx.entities.IActor;import aigilas.creatures.Player;import aigilas.creatures.StatType;import aigilas.creatures.Stats;import aigilas.management.SpriteType;public class GenericItem extends Entity {	public Stats Modifers;	public String Name;	private String _suffix;	private String _prefix;	private int _type;	private Slots _targetSlots;	private static final String __spacingCharacter = " ";	public int GetItemClass() {		switch (_type) {		case ItemName.Sword:			return ItemClass.Melee_Weapon;		case ItemName.Arrow:			return ItemClass.Ranged_Ammo;		case ItemName.Bow:			return ItemClass.Ranged_Weapon;		case ItemName.Shield:			return ItemClass.Shield;		case ItemName.Pants:			return ItemClass.Leggings;		case ItemName.Dagger:			return ItemClass.Melee_Weapon;		case ItemName.Staff:			return ItemClass.Melee_Weapon;		case ItemName.Hood:			return ItemClass.Head_Gear;		case ItemName.Shirt:			return ItemClass.Torso_Garb;		default:			return ItemClass.NULL;		}	}	private void Initialize(String suffix, String prefix, int type,			Slots targetSlots, Stats modifiers, Point2 location) {		Setup(location, type);		_suffix = suffix;		_prefix = prefix;		_type = type;		_targetSlots = GetSlotFromType(type);		Name = (_prefix == ItemPrefix.NULL ? "" : _prefix + __spacingCharacter)				+ ItemName.Names.get(_type)				+ (_suffix == ItemSuffix.NULL ? "" : __spacingCharacter						+ _suffix);		Modifers = new Stats(modifiers);	}	public GenericItem(GenericItem item, Point2 location) {		Initialize(item._suffix, item._prefix, item._type, item._targetSlots,				item.Modifers, location);	}	public GenericItem(Stats modifiers, String suffix, String prefix, int type,			Point2 location, boolean onGround) {		Construct(modifiers, suffix, prefix, type, location, onGround);	}	public GenericItem(Stats modifiers, String suffix, String prefix, int type,			Point2 location) {		Construct(modifiers, suffix, prefix, type, location, true);	}	private void Construct(Stats modifiers, String suffix, String prefix,			int type, Point2 location, boolean onGround) {		if (type == ItemName.NULL) {			try {				throw new Exception(						"Invalid type NULL passed into the GenericItem factory!");			} catch (Exception e) {				e.printStackTrace();			}		}		Initialize(_suffix, _prefix, type, GetSlotFromType(type), modifiers,				location);	}	protected void Setup(Point2 location, int type) {		Initialize(location, SpriteFromItem(type), aigilas.EntityType.ITEM,				aigilas.Depth.Item);	}	private Player _currentTarget;	@Override	public void Update() {		super.Update();		if (_isOnBoard) {			IActor collider = EntityManager.GetTouchingCreature(this);			if (collider != null && collider.GetActorType() == ActorType.PLAYER) {				_currentTarget = (Player) collider;				if (_currentTarget != null) {					if (_currentTarget.IsInteracting()) {						_currentTarget.PickupItem(this);					}				}			}		}	}	private int SpriteFromItem(int item) {		return SpriteType.ITEM;	}	private Slots GetSlotFromType(int type) {		switch (type) {		case ItemName.Sword:			return new Slots(Arrays.asList(ItemSlot.RIGHT_HAND,					ItemSlot.LEFT_HAND));		case ItemName.Pants:			return new Slots(Arrays.asList(ItemSlot.LEGS));		default:			return new Slots(new ArrayList<Integer>());		}	}	@Override	public boolean equals(Object obj) {		if (obj.getClass() == GenericItem.class) {			GenericItem gI = (GenericItem) obj;			if (Name != gI.Name) {				return false;			}			for (String stat : StatType.Values) {				if (Modifers.Get(stat) != gI.Modifers.Get(stat)) {					return false;				}			}			return true;		}		return false;	}	@Override	public int hashCode() {		return Name.hashCode() + Modifers.hashCode();	}	public float GetStatBonus(String stat) {		return Modifers.Get(stat);	}}