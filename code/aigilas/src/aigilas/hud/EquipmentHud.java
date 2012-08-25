package aigilas.hud;import java.util.HashMap;import spx.core.XnaManager;import spx.util.StringSquisher;import aigilas.creatures.ICreature;import aigilas.items.Equipment;import aigilas.items.GenericItem;import aigilas.items.ItemSlot;public class EquipmentHud extends IHud {	private Equipment _equipment;	public EquipmentHud(ICreature owner, Equipment equipment) {		super(owner, XnaManager.WindowWidth / 2, XnaManager.WindowHeight / 2);		_equipment = equipment;	}	public void Draw() {		if (_isVisible) {			_textHandler.Draw();		}	}	private static final String sep = ":";	private static final String newline = "\n";	private String display = "EMPTY";	private String title = "Equipped\n";	public void Update(boolean refresh) {		if (_isVisible) {			_textHandler.Update();			_textHandler.Clear();			if (refresh) {				StringSquisher.Clear();				StringSquisher.Squish(title);				HashMap<Integer, GenericItem> items = _equipment.GetItems();				for (Integer item : items.keySet()) {					StringSquisher.Squish(ItemSlot.Names[item].substring(0, 1), sep, items.get(item).Name, newline);				}				display = StringSquisher.Flush();			}			_textHandler.WriteDefault(display, 20, 60, GetHudOrigin());		}	}}