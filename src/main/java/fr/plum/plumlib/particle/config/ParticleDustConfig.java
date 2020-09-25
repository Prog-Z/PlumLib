package fr.plum.plumlib.particle.config;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fr.plum.plumlib.maths.utils.Vec3;

public class ParticleDustConfig extends ParticleConfig {

    public ParticleDustConfig(@Nullable Vec3 offset, @NotNull Color color, float size, int count) {
        super(Particle.REDSTONE,offset, count);
        _options = new DustOptions(color,size);
    }
    public ParticleDustConfig(@Nullable Vec3 offset, @NotNull DustOptions opt, int count) {
        super(Particle.REDSTONE,offset, count);
        _options = opt;
    }
    public ParticleDustConfig(@NotNull Color color, float size, int count) {
        super(Particle.REDSTONE, count);
        _options = new DustOptions(color,size);
    }
    public ParticleDustConfig(@NotNull DustOptions opt, int count) {
        super(Particle.REDSTONE, count);
        _options = opt;
    }
}