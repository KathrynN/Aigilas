package aigilas.skills; import java.util.ArrayList;import java.util.Arrays;import java.util.HashMap;import java.util.List;import spx.core.RNG;import aigilas.skills.animations.NoAnimation;import aigilas.skills.animations.RangedAnimation;import aigilas.skills.animations.RotateAnimation;import aigilas.skills.animations.SelfAnimation;import aigilas.skills.animations.SkillAnimation;import aigilas.skills.behaviors.CloudBehavior;import aigilas.skills.behaviors.RangedBehavior;import aigilas.skills.behaviors.RotateBehavior;import aigilas.skills.behaviors.SelfBehavior;import aigilas.skills.behaviors.SkillBehavior;import aigilas.skills.behaviors.StationaryBehavior;import aigilas.skills.impl.AbsorbSkill;import aigilas.skills.impl.AcidDripSkill;import aigilas.skills.impl.AcidNozzleSkill;import aigilas.skills.impl.BoilSkill;import aigilas.skills.impl.BreakingWheelSkill;import aigilas.skills.impl.BrimstoneSkill;import aigilas.skills.impl.CavalrySkill;import aigilas.skills.impl.ColdShoulderSkill;import aigilas.skills.impl.CombustSkill;import aigilas.skills.impl.ConfusionSkill;import aigilas.skills.impl.DartSkill;import aigilas.skills.impl.DartTrapSkill;import aigilas.skills.impl.DismembermentSkill;import aigilas.skills.impl.ElectrifySkill;import aigilas.skills.impl.EnvenomSkill;import aigilas.skills.impl.ExplodeSkill;import aigilas.skills.impl.FireballSkill;import aigilas.skills.impl.FlameHammerSkill;import aigilas.skills.impl.FloorSpikesSkill;import aigilas.skills.impl.ForgetSkill;import aigilas.skills.impl.GushSkill;import aigilas.skills.impl.HorderSkill;import aigilas.skills.impl.HorrifySkill;import aigilas.skills.impl.HypothermiaSkill;import aigilas.skills.impl.IceShardSkill;import aigilas.skills.impl.MagicMapSkill;import aigilas.skills.impl.ManaUpSkill;import aigilas.skills.impl.MimicSkill;import aigilas.skills.impl.MutinySkill;import aigilas.skills.impl.NoSkill;import aigilas.skills.impl.PlagueSkill;import aigilas.skills.impl.PoisonCloudSkill;import aigilas.skills.impl.RegenAllSkill;import aigilas.skills.impl.RemoteMineSkill;import aigilas.skills.impl.SerpentSupperSkill;import aigilas.skills.impl.SoulCrushSkill;import aigilas.skills.impl.SoulReinforcementSkill;import aigilas.skills.impl.SpawnItemSkill;import aigilas.skills.impl.SpeedUpSkill;import aigilas.skills.impl.StealItemSkill;import aigilas.skills.impl.StrengthUpSkill;import aigilas.skills.impl.ThrowItemSkill;import aigilas.skills.impl.ValedictorianSkill;import aigilas.skills.impl.VaporCloudSkill;import aigilas.skills.impl.VaporImplantSkill;import aigilas.skills.impl.VenomFistSkill;import aigilas.skills.impl.WallPunchSkill;import aigilas.skills.impl.WeakKneesSkill;public class SkillFactory {	public static ISkill Create(String idSkill) {		switch (idSkill) {		case SkillId.ABSORB:			return new AbsorbSkill();		case SkillId.ACID_DRIP:			return new AcidDripSkill();		case SkillId.ACID_NOZZLE:			return new AcidNozzleSkill();		case SkillId.BRIMSTONE:			return new BrimstoneSkill();		case SkillId.BOIL:			return new BoilSkill();		case SkillId.BREAKING_WHEEL:			return new BreakingWheelSkill();		case SkillId.CAVALRY:			return new CavalrySkill();		case SkillId.COLD_SHOULDER:			return new ColdShoulderSkill();		case SkillId.COMBUST:			return new CombustSkill();		case SkillId.CONFUSION:			return new ConfusionSkill();		case SkillId.DART:			return new DartSkill();		case SkillId.DART_TRAP:			return new DartTrapSkill();		case SkillId.DISMEMBERMENT:			return new DismembermentSkill();		case SkillId.ELECTRIFY:			return new ElectrifySkill();		case SkillId.ENVENOM:			return new EnvenomSkill();		case SkillId.EXPLODE:			return new ExplodeSkill();		case SkillId.FIREBALL:			return new FireballSkill();		case SkillId.FLAME_HAMMER:			return new FlameHammerSkill();		case SkillId.FLOOR_SPIKES:			return new FloorSpikesSkill();		case SkillId.FORGET_SKILL:			return new ForgetSkill();		case SkillId.GUSH:			return new GushSkill();		case SkillId.HORDER:			return new HorderSkill();		case SkillId.HORRIFY:			return new HorrifySkill();		case SkillId.HYPOTHERMIA:			return new HypothermiaSkill();		case SkillId.ICE_SHARD:			return new IceShardSkill();		case SkillId.MAGIC_MAP:			return new MagicMapSkill();		case SkillId.MANA_UP:			return new ManaUpSkill();		case SkillId.MIMIC:			return new MimicSkill();		case SkillId.MUTINY:			return new MutinySkill();		case SkillId.NO_SKILL:			return new NoSkill();		case SkillId.PLAGUE:			return new PlagueSkill();		case SkillId.POISON_CLOUD:			return new PoisonCloudSkill();		case SkillId.REGEN_ALL:			return new RegenAllSkill();		case SkillId.REMOTE_MINE:			return new RemoteMineSkill();		case SkillId.SERPENT_SUPPER:			return new SerpentSupperSkill();		case SkillId.SOUL_CRUSH:			return new SoulCrushSkill();		case SkillId.SOUL_REINFORCEMENT:			return new SoulReinforcementSkill();		case SkillId.SPAWN_ITEM:			return new SpawnItemSkill();		case SkillId.SPEED_UP:			return new SpeedUpSkill();		case SkillId.STEAL_ITEM:			return new StealItemSkill();		case SkillId.STRENGTH_UP:			return new StrengthUpSkill();		case SkillId.THROW_ITEM:			return new ThrowItemSkill();		case SkillId.VALEDICTORIAN:			return new ValedictorianSkill();		case SkillId.VAPOR_IMPLANT:			return new VaporImplantSkill();		case SkillId.VENOM_FIST:			return new VenomFistSkill();		case SkillId.WALL_PUNCH:			return new WallPunchSkill();		case SkillId.WEAK_KNEEES:			return new WeakKneesSkill();		case SkillId.VAPOR_CLOUD:			return new VaporCloudSkill();		default:			try {				throw new Exception(						"A SkillId to Skill mapping was not defined:the SkillFactory for ("								+ idSkill								+ "). This is 100% the fault of whoever wrote the code.");			} catch (Exception e) {				e.printStackTrace();			}			return null;		}	}	private static HashMap<Integer, List<String>> __elementMap = new HashMap<Integer, List<String>>();	private static HashMap<String, Integer> __skillAnimationMap = new HashMap<String, Integer>();	private static void GenerateStaticSkillMaps() {		if (__elementMap.size() == 0) {			ISkill skill;			for (String skillId : SkillId.Values) {				if (skillId != SkillId.NO_SKILL) {					skill = Create(skillId);					for (Integer elem : skill.GetElements()) {						if (!__elementMap.containsKey(elem)) {							__elementMap.put(elem, new ArrayList<String>());						}						__elementMap.get(elem).add(skillId);					}					__skillAnimationMap.put(skillId, skill.GetAnimationType());				}			}			__skillAnimationMap.put(SkillId.NO_SKILL, -1);		}	}	private static int skillPick = 0;	private static List<String> InvalidRandomSkills = Arrays.asList(			SkillId.BRIMSTONE, SkillId.BOIL, SkillId.SERPENT_SUPPER,			SkillId.BREAKING_WHEEL, SkillId.DISMEMBERMENT, SkillId.HYPOTHERMIA,			SkillId.PLAGUE);	public static String GetElementalSkill(int elementId) {		GenerateStaticSkillMaps();		while (InvalidRandomSkills.contains(SkillId.Values[skillPick])) {			skillPick = RNG.Next(0, __elementMap.get(elementId).size());		}		return __elementMap.get(elementId).get(skillPick);	}	public static boolean IsSkill(String skillId, int animationType) {		GenerateStaticSkillMaps();		return __skillAnimationMap.get(skillId) == animationType;	}	public static SkillBehavior Create(int animation, int skillGraphic,			ISkill parentSkill) {		switch (animation) {		case AnimationType.CLOUD:			return new CloudBehavior(skillGraphic, parentSkill);		case AnimationType.RANGED:			return new RangedBehavior(skillGraphic, parentSkill);		case AnimationType.SELF:			return new SelfBehavior(skillGraphic, parentSkill);		case AnimationType.STATIONARY:			return new StationaryBehavior(skillGraphic, parentSkill);		case AnimationType.ROTATE:			return new RotateBehavior(skillGraphic, parentSkill);		default:			try {				throw new Exception(						"How dare you create a new Anim type for skills without defining a proper behavior for them!");			} catch (Exception e) {				e.printStackTrace();			}			return null;		}	}	public static SkillAnimation Create(int animation) {		switch (animation) {		case AnimationType.RANGED:			return new RangedAnimation();		case AnimationType.SELF:			return new SelfAnimation();		case AnimationType.ROTATE:			return new RotateAnimation();		default:			return new NoAnimation();		}	}	public static HashMap<Integer, List<String>> __actorToSkillMapping = new HashMap<Integer, List<String>>();	public static List<String> GetElementalSkills(int actorType,			List<Integer> _elementalComposition) {		if (!__actorToSkillMapping.containsKey(actorType)) {			__actorToSkillMapping.put(actorType, new ArrayList<String>());			for (Integer elem : _elementalComposition) {				__actorToSkillMapping.get(actorType).add(						SkillFactory.GetElementalSkill(elem));			}		}		return __actorToSkillMapping.get(actorType);	}	public static float GetCost(String skillId) {		return Create(skillId).GetCost();	}}