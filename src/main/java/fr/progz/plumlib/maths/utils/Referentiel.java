package fr.progz.plumlib.maths.utils;

import fr.progz.plumlib.maths.utils.trigo.Angle;

public class Referentiel {
    protected Vec3 _x = new Vec3(1,0,0), _y = new Vec3(0,1,0), _z = new Vec3(0,0,1);

    public Referentiel() {}

    protected void setDefaultAxes() {
        _x.set(1, 0, 0);
        _y.set(0, 1, 0);
        _z.set(0, 0, 1);
    }

    public final void rotate(Rotator rot) {
        this.rotateX(rot.getAngleX());
        this.rotateY(rot.getAngleY());
        this.rotateZ(rot.getAngleZ());
    }

    protected void rotateX(Angle angleX) {
        double xY = angleX.cos()*_y.getX()+angleX.sin()*_z.getX();
        double yY = angleX.cos()*_y.getY()+angleX.sin()*_z.getY();
        double zY = angleX.cos()*_y.getZ()+angleX.sin()*_z.getZ();

        double xZ = -angleX.sin()*_y.getX()+angleX.cos()*_z.getX();
        double yZ = -angleX.sin()*_y.getY()+angleX.cos()*_z.getY();
        double zZ = -angleX.sin()*_y.getZ()+angleX.cos()*_z.getZ();

        _y.set(xY, yY, zY);
        _z.set(xZ, yZ, zZ);
    }
    protected void rotateY(Angle angleY) {
        double xX = -angleY.sin()*_z.getX()+angleY.cos()*_x.getX();
        double yX = -angleY.sin()*_z.getY()+angleY.cos()*_x.getY();
        double zX = -angleY.sin()*_z.getZ()+angleY.cos()*_x.getZ();

        double xZ = angleY.sin()*_x.getX()+angleY.cos()*_z.getX();
        double yZ = angleY.sin()*_x.getY()+angleY.cos()*_z.getY();
        double zZ = angleY.sin()*_x.getZ()+angleY.cos()*_z.getZ();

        _x.set(xX, yX, zX);
        _z.set(xZ, yZ, zZ);

    }
    protected void rotateZ(Angle angleZ) {
        double xX = angleZ.sin()*_y.getX()+angleZ.cos()*_x.getX();
        double yX = angleZ.sin()*_y.getY()+angleZ.cos()*_x.getY();
        double zX = angleZ.sin()*_y.getZ()+angleZ.cos()*_x.getZ();

        double xY = -angleZ.sin()*_x.getX()+angleZ.cos()*_y.getX();
        double yY = -angleZ.sin()*_x.getY()+angleZ.cos()*_y.getY();
        double zY = -angleZ.sin()*_x.getZ()+angleZ.cos()*_y.getZ();

        _x.set(xX, yX, zX);
        _y.set(xY, yY, zY);
    }

    public Vec3 getAxeX() {return _x;}
    public Vec3 getAxeY() {return _y;}
    public Vec3 getAxeZ() {return _z;}
}