package fr.progz.plumlib.maths.curves;

import fr.progz.plumlib.maths.utils.Vec3;

public abstract class Curve {
    protected Vec3 _position = new Vec3(0,0,0);

    public abstract double getX(long tick);
    public abstract double getY(long tick);
    public abstract double getZ(long tick);
}