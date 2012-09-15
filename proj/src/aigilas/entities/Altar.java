package aigilas.entities;

import java.util.List;

import spx.bridge.DrawDepth;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.entities.Entity;
import spx.entities.EntityManager;
import spx.entities.IEntity;
import spx.text.ActionText;
import spx.text.TextManager;
import aigilas.creatures.Player;
import aigilas.gods.God;
import aigilas.gods.GodId;
import aigilas.items.GenericItem;
import aigilas.management.SpriteType;

public class Altar extends Entity {
	private God _god;
	private Player _currentTarget;
	private List<IEntity> _offerings;

	public Altar(Point2 location, GodId godName) {
		_god = godName.GetInstance();
		_graphic.SetColor(_god.GetColor());
		Initialize(location, SpriteType.ALTAR, EntityType.ALTAR, DrawDepth.Altar);
	}

	@Override
	public void Update() {
		_currentTarget = (Player) EntityManager.GetTouchingCreature(this);
		if (_currentTarget != null) {
			if (_currentTarget.IsInteracting()) {
				_currentTarget.Pray(_god);
			}
			_offerings = EntityManager.GetEntities(EntityType.ITEM, _location);
			for (IEntity offering : _offerings) {
				_currentTarget.Sacrifice(_god, (GenericItem) offering);
			}
			TextManager.Add(new ActionText(_god.NameText, 1, (int) GetLocation().PosX, (int) GetLocation().PosY));
		}
	}

	public God GetGod() {
		return _god;
	}
}