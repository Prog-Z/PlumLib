package fr.progz.plumlib.maths.utils;

import fr.progz.plumlib.maths.utils.trigo.Angle;
import fr.progz.plumlib.maths.utils.trigo.AngleType;

public class Rotator {
    
    private Angle angle_x = new Angle(0), angle_y = new Angle(0), angle_z = new Angle(0);

    public Rotator(double angle_x, double angle_y, double angle_z, AngleType type) {
        this.angle_x = new Angle(angle_x,type);
        this.angle_y = new Angle(angle_y,type);
        this.angle_z = new Angle(angle_z,type);
    }
    public Rotator(double angle_x, double angle_y, double angle_z) {
        this.angle_x = new Angle(angle_x);
        this.angle_y = new Angle(angle_y);
        this.angle_z = new Angle(angle_z);
    }
    public Rotator() {}

    public void setAngleX(double x) {this.angle_x = new Angle(x);}
    public void setAngleY(double y) {this.angle_y = new Angle(y);}
    public void setAngleZ(double z) {this.angle_z = new Angle(z);}
    public void setAngleX(double x, AngleType type) {this.angle_x = new Angle(x,type);}
    public void setAngleY(double y, AngleType type) {this.angle_y = new Angle(y,type);}
    public void setAngleZ(double z, AngleType type) {this.angle_z = new Angle(z,type);}

    public Angle getAngleX() {return this.angle_x;}
    public Angle getAngleY() {return this.angle_y;}
    public Angle getAngleZ() {return this.angle_z;}
}