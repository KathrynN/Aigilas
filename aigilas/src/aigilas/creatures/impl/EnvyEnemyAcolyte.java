package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import sps.bridge.ActorType;

public class EnvyEnemyAcolyte extends BaseEnemy {
    public EnvyEnemyAcolyte() {
        super(ActorType.ENVY_ACOLYTE);
        weaknesses(StatType.STRENGTH, StatType.HEALTH, StatType.MOVE_COOL_DOWN);
        compose(Elements.WATER);
    }
}