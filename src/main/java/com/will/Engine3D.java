package com.will;

public class Engine3D {
    final static float[] RESOLUTION = {1024, 940};
    final static double ASPECT_RATIO = RESOLUTION[1]/RESOLUTION[0];

    final static double FOV = 90.0;
    final static double FOV_SCALE = 1.0 / Math.tan( FOV / 2 );

    final static double Z_NEAR = 0.1;
    final static double Z_FAR = 1000.0;
    final static double Z_NORMALISATION = ( Z_FAR / ( Z_FAR - Z_NEAR ) );

    final static Vec3d V_CAMERA = new Vec3d(0.0f, 0.0f, 0.0f, 0f);
    final static Vec3d LIGHT_DIRECTION = new Vec3d(0.0f, 0.0f, 1.0f, 0f);


    final static Matrix PROJECTION_MATRIX = new Matrix(new double [][] {
        {ASPECT_RATIO * FOV_SCALE,  0,          0,                  0                               },
        {0,                         FOV_SCALE,  0,                  0                               },
        {0,                         0,          Z_NORMALISATION,    ( -Z_NEAR * Z_NORMALISATION )   },
        {0,                         0,          1,                  0                               }
    });

    public static Matrix rotationMatrix(float angle) {
        double sinTheta = Math.sin(angle);
        double cosTheta = Math.cos(angle);
        
        double sinHalfTheta = -Math.sin(angle / 2);
        double cosHalfTheta = -Math.cos(angle / 2);

        Matrix rotationMatrixZX = new Matrix(new double [][] {
            {cosTheta,      sinTheta * cosHalfTheta,    sinTheta * sinHalfTheta,    0},
            {-sinTheta,     cosTheta * cosHalfTheta,    cosTheta * sinHalfTheta,    0},
            {0,             -sinHalfTheta,              cosHalfTheta,               0},
            {0,             0,                          0,                          1}
        });

        return rotationMatrixZX;
    }

    public static Matrix lookAtMatrix(Vec3d forward, Vec3d right, Vec3d offset) {
        Vec3d up = forward.normal(right);

        return new Matrix(new double[][] {
            {forward.x,                     right.x,                    up.x,                       0},
            {forward.y,                     right.y,                    up.y,                       0},
            {forward.z,                     right.z,                    up.z,                       0},
            {-offset.dotProduct(forward),   -offset.dotProduct(right),  -offset.dotProduct(up),     1}
        });
    }
}
