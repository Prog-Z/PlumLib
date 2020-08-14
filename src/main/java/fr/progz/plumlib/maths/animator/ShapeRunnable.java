package fr.progz.plumlib.maths.animator;

import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import fr.progz.plumlib.maths.shape.Shape;

public class ShapeRunnable extends BukkitRunnable {

    protected Animator _animator;
    protected Shape _shape;

    protected long _tick;

    public Animator getAnim() {return _animator;}

    // #####################################################
    // SECTION Constructor
    // #####################################################
    public ShapeRunnable(@NotNull Shape shape, @NotNull Animator anim) { // For drawing & animating (for shapes)
        _shape = shape;
        _animator = anim;
        _tick = _animator.getDelay();
    }
    protected ShapeRunnable(@NotNull Animator anim) { // For drawing & animating (for extends)
        _animator = anim;
        _tick = _animator.getDelay();
    }
    // !SECTION


    // #####################################################
    // SECTION Running
    // #####################################################
    @Override
    public final void run() {
        animation();
        execute();

        _tick += _animator.getRefreshTime();
    }

    protected void animation() {
        // Rotation
        if (_animator.getRotator()!=null) _shape.getReferentiel().rotate(_animator.getRotator());

        // Translation
        if (_animator.getCurve()!=null) _shape.getOffset().set(_animator.getCurve().getX(_tick), _animator.getCurve().getY(_tick), _animator.getCurve().getZ(_tick));

        _shape.projectToOriginRef();
        //_shape.getReferentiel().setDefaultAxes();
    }

    protected void execute() {}
    // !SECTION
}