package fr.progz.plumlib.maths.shape;

import java.util.ArrayList;

import fr.progz.plumlib.maths.shape.config.SpiralConfig;
import fr.progz.plumlib.maths.utils.Vec3;

public class Spiral extends Shape {

    private SpiralConfig config;
    private double dephasase;

    public Spiral(float eccentricity, float dilatation, float theta_min, float theta_max, int n_point, double dephasase) {
        config = new SpiralConfig(eccentricity,dilatation,theta_min,theta_max,n_point);
        this.dephasase = dephasase;
    }
    public Spiral(SpiralConfig conf, double dephasase) {config = conf; this.dephasase = dephasase;}
    public Spiral(SpiralConfig conf) {config = conf;}

    @Override
    public void calcPoints() {
        float dtheta = (config.theta_max-config.theta_min)/config.n_point;
        for (float theta = config.theta_min; theta < config.theta_max; theta += dtheta) {
            double x = (config.eccentricity+config.dilatation*theta)*Math.cos(theta + dephasase);
            double y = (config.eccentricity+config.dilatation*theta)*Math.sin(theta + dephasase);

            _points.add(new Vec3(x,y,0));
        }
    }
    
}