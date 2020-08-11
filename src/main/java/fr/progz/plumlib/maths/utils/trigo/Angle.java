package fr.progz.plumlib.maths.utils.trigo;

public final class Angle {

    private AngleType type = AngleType.RADIANS;
    private double angle;
    private double cos;
    private double sin;

    public Angle(double angle) {
        this.angle = angle;
        calcTrigo();
    }

    public Angle(double angle, AngleType type) {
        this.angle = angle;
        this.type = type;
        calcTrigo();
    }

    private final void calcTrigo() {
        switch (this.type) {
            case DEGREES:
                this.cos = Math.cos(Math.PI*angle/180);
                this.sin = Math.sin(Math.PI*angle/180);
                return;
            case RADIANS:
                this.cos = Math.cos(angle);
                this.sin = Math.sin(angle);
                return;
        }
    }

    public final AngleType type() {return type;}
    public final double angle() {return angle;}
    public final double cos() {return cos;}
    public final double sin() {return sin;}
}