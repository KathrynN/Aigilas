package aigilas.dungeons;

import java.util.HashMap;

import spx.particles.ParticleEngine;

public class DungeonSet {
	private int _currentFloor = 0;
	private final HashMap<Integer, Dungeon> _floors = new HashMap<Integer, Dungeon>();

	/*
	 * This whole "area" thing is very messy and doesn't work:an intuitive way.
	 * In order to pre-load, we need to make sure we can easily get back to
	 * start.
	 */
	public DungeonSet() {
		_floors.put(_currentFloor, new Dungeon());
	}

	public void GotoNext() {
		_floors.get(_currentFloor).CacheContents();
		_currentFloor++;
		LoadOrCreateDungeon(false);
	}

	public boolean GotoPrevious() {
		if (_currentFloor > 0) {
			_floors.get(_currentFloor).CacheContents();
			_currentFloor--;
			LoadOrCreateDungeon(true);
			return true;
		}
		return false;
	}

	private void LoadOrCreateDungeon(boolean goingUp) {
		if (!_floors.containsKey(_currentFloor)) {
			System.out.println("Creating a new dungeon for floor: " + _currentFloor);
			_floors.put(_currentFloor, new Dungeon(_floors.get(_currentFloor-1).getDownstairsLocation()));
			DungeonFactory.IncreaseFloorCount();
		}
		ParticleEngine.Reset();
		_floors.get(_currentFloor).LoadTiles(goingUp);
	}
}
