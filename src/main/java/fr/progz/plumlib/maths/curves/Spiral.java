package fr.progz.plumlib.maths.curves;

import fr.progz.plumlib.maths.curves.config.SpiralConfig;

public class Spiral extends Shape {

    private SpiralConfig config;

    public Spiral(float eccentricity, float dilatation, float theta_min, float theta_max, int n_point) {
        config = new SpiralConfig(eccentricity,dilatation,theta_min,theta_max,n_point);
    }
    public Spiral(SpiralConfig conf) {config = conf;}
    
}