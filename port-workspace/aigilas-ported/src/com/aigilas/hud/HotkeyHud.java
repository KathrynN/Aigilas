package com.aigilas.hud;
    public class HotkeyHud extends IHud
    {
        public HotkeyHud(ICreature owner)
            {
        }

        public void Draw()
        {
        }

        private static List<Integer> _hotSkills = Arrays.asList(
            Commands.HotSkill1,
            Commands.HotSkill2,
            Commands.HotSkill3);

        public void Update(GenericItem item, boolean refresh)
        {
            if (Input.IsActive(Commands.LockSkill, _parent.GetPlayerIndex(), false))
            {
                for (Integer hotSkill:_hotSkills)
                {
                    if (Input.IsActive(hotSkill, _parent.GetPlayerIndex(), false))
                    {
                        _parent.MarkHotSkill(hotSkill);
                    }
                }
            };
        }
    }