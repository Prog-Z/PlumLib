package fr.progz.plumlib.particle.config;

import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fr.progz.plumlib.maths.utils.Vec3;

public class ParticleConfig {

    protected Particle _particle;
    protected DustOptions _options;
    protected int _count;

    protected Vec3 _offset = new Vec3();

    public ParticleConfig(@NotNull Particle part, @Nullable Vec3 offset, int count) {
        _particle = part;
        _offset = offset;
        _count = count;
    }
    public ParticleConfig(@NotNull Particle part, int count) {
        _particle = part;
        _count = count;
    }

    /** @return the particle */
    public final Particle getParticle() {return _particle;}
    /** @return the number of particle wich be spawn */
    public final int getNumberOfParticle() {return _count;}
    /** @return the particles offset */
    public final Vec3 getOffset() {return _offset;}

    /** @return the particle option */
    @Nullable
    public final DustOptions getOptions() { return _options;}
    
}