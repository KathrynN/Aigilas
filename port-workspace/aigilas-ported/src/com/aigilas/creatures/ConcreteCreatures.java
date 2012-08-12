package com.aigilas.creatures;
    public class Player  extends  AbstractCreature
    {
        private static List<Color> __colors = Arrays.asList(Color.Red,Color.Green,Color.Blue,Color.White);
        public Player(int playerIndex){
            _graphic.SetColor(__colors.get(_playerIndex));
            _strategy = StrategyFactory.Create(Strategy.ControlledByPlayer, this);
            _baseStats = new Stats(100f, 100f, 1f, 10f, 11f, 10f, 35f, 50f, 6.0f, 6, 1);
            _maxStats = new Stats(_baseStats);
            AssignGod(God.Get(GodId.GLUTTONY));
            Compose(Elements.PHYSICAL);
        }
    }
    class Peon  extends  AbstractCreature
    {
        public Peon(){
            Compose(Elements.EARTH);
        }
    }
    class Zorb  extends  AbstractCreature
    {
        public Zorb(){
            Strengths(StatType.MANA,StatType.HEALTH);
            Weaknesses(StatType.WISDOM,StatType.DEFENSE);
            Add(SkillId.FIREBALL);
        }
    }
    class Wrath  extends  AbstractCreature
    {
        public Wrath(){
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.DISMEMBERMENT);
        }
    }
    class Hand extends  AbstractCreature
    {
        public Hand(){
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            _isBlocking = false;
            _strategy = StrategyFactory.Create(Strategy.StraightLine, this);
        }
    }
    class Envy  extends  AbstractCreature
    {
        public Envy()
            {
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.HYPOTHERMIA);
        }
    }
    class Gluttony  extends  AbstractCreature
    {
        public Gluttony()
            {
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.PLAGUE);
        }
    }
    class Sloth extends  AbstractCreature
    {
        public Sloth()
            {
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.SERPENT_SUPPER);
        }
    }
    public class Serpent  extends  AbstractCreature
    {
        public Serpent()
            {
            Strengths(StatType.HEALTH, StatType.HEALTH, StatType.HEALTH);
            _strategy = StrategyFactory.Create(Strategy.ConfusedAndDying,this);
        }
    }
    class Pride extends  AbstractCreature
    {
        public Pride()
            {
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.BREAKING_WHEEL);
        }
    }
    class Greed extends  AbstractCreature
    {
        public Greed()
            {
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.BOIL);
        }
    }
    class Lust extends  AbstractCreature
    {
        public Lust()
            {
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.BRIMSTONE);
        }
    }
    public class BreakingWheel  extends  AbstractCreature
    {
        public BreakingWheel()
            {
            _composition.add(Elements.DARK);
            Strengths(StatType.MOVE_COOL_DOWN, StatType.MOVE_COOL_DOWN);
        }
    }