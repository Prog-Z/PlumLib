package fr.progz.plumlib.particle;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import fr.progz.plumlib.maths.animator.Animator;
import fr.progz.plumlib.maths.animator.ShapeRunnable;
import fr.progz.plumlib.maths.shape.Shape;
import fr.progz.plumlib.maths.utils.Vec3;
import fr.progz.plumlib.particle.config.ParticleConfig;

public class ParticleRunnable extends ShapeRunnable {

    private final ParticleConfig _particle;

    public ParticleRunnable(ParticleConfig part, @NotNull Animator anim, @NotNull Shape shape) {
        super(shape, anim);
        _particle = part;
    }

    @Override
    protected final void execute() {
        for (Vec3 point : _shape.getRealPoints()) {
            _shape.getReferentiel().getLocation().getWorld().spawnParticle(
                _particle.getParticle(), 
                new Location(_shape.getReferentiel().getLocation().getWorld(), point.getX(), point.getY(), point.getZ()),
                _particle.getNumberOfParticle(),
                _particle.getOffset().getX(),
                _particle.getOffset().getY(),
                _particle.getOffset().getZ(),
                _particle.getOptions()
            );
        }
    }    
}