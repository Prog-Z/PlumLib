package fr.plum.plumlib.maths.shape.spiral;

import fr.plum.plumlib.maths.shape.config.ShapeConfig;

public class SpiralConfig extends ShapeConfig {
    public float eccentricity = 0f;
    public float dilatation = 0f;

    public float theta_min = 0f;
    public float theta_max = 2f*3.14f;

    // #####################################################
    // SECTION Constructors
    // #####################################################
    public SpiralConfig(float ex, float dil, float theta_m, float theta_M, int nbPoint) {
        this.eccentricity = ex;
        this.dilatation = dil;
        this.theta_min = theta_m;
        this.theta_max = theta_M;
        this.n_point = nbPoint;
    }
    public SpiralConfig(float dil, float theta_m, float theta_M, int nbPoint) {
        this.dilatation = dil;
        this.theta_min = theta_m;
        this.theta_max = theta_M;
        this.n_point = nbPoint;
    }
    public SpiralConfig(float dil, float theta_M, int nbPoint) {
        this.dilatation = dil;
        this.theta_max = theta_M;
        this.n_point = nbPoint;
    }
    public SpiralConfig(float theta_M, int nbPoint) {
        this.theta_max = theta_M;
        this.n_point = nbPoint;
    }
    public SpiralConfig(int nbPoint) {
        this.n_point = nbPoint;
    }
    // !SECTION

    // #####################################################
    // SECTION SETTER
    // #####################################################
    public SpiralConfig setEccentricity(float ex) {this.eccentricity=ex; return this;}
    public SpiralConfig setDilatation(float dil) {this.dilatation=dil; return this;}
    public SpiralConfig setAngleMin(float t_min) {this.theta_min=t_min; return this;}
    public SpiralConfig setAngleMax(float t_max) {this.theta_max=t_max; return this;}
    public SpiralConfig setNumberOfPoint(int point) {this.n_point=point; return this;}
}