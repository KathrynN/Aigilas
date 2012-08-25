package aigilas.hud; import java.util.Arrays;import java.util.List;import spx.core.XnaManager;import spx.io.Input;import aigilas.creatures.ICreature;import aigilas.items.GenericItem;import aigilas.management.Commands;public class HotkeyHud extends IHud {	public HotkeyHud(ICreature owner) {		super(owner, XnaManager.WindowWidth / 2, XnaManager.WindowHeight / 2);		_isVisible = true;	}	public void Draw() {	}	private static List<Integer> _hotSkills = Arrays.asList(Commands.HotSkill1,			Commands.HotSkill2, Commands.HotSkill3);	public void Update(GenericItem item, boolean refresh) {		if (Input.IsActive(Commands.LockSkill, _parent.GetPlayerIndex(), false)) {			for (Integer hotSkill : _hotSkills) {				if (Input.IsActive(hotSkill, _parent.GetPlayerIndex(), false)) {					_parent.MarkHotSkill(hotSkill);				}			}		}		;	}}