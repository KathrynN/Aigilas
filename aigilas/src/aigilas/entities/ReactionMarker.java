package aigilas.entities;

import aigilas.creatures.BaseCreature;
import sps.bridge.DrawDepth;
import sps.bridge.EntityType;
import sps.entities.Entity;
import sps.particles.Emitter;
import sps.particles.ParticleEngine;

public class ReactionMarker extends Entity {
    private final BaseCreature _parent;
    private final Emitter emitter;

    public ReactionMarker(BaseCreature source, Elements elementId) {
        initialize(source.getLocation(), null, EntityType.Combo_Marker, DrawDepth.ComboMarker);
        _graphic.setColor(elementId.Tint);
        _graphic.setAlpha(0);
        _parent = source;
        emitter = ParticleEngine.emit(sps.particles.behaviors.RotateBehavior.getInstance(), _parent, _graphic.getColor());
    }

    @Override
    public void update() {
    }

    @Override
    public void draw() {
    }

    @Override
    public void setInactive() {
        super.setInactive();
        emitter.clear();
    }
}