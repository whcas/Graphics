package com.will;

public class Engine3D {
    final static int[] RESOLUTION = {1024, 940};
    final static Float ASPECT_RATIO = (Float.valueOf(RESOLUTION[1])/Float.valueOf(RESOLUTION[0]));

    final static Float FOV = 90.0f;
    final static Float FOV_SCALE = 1.0f / (float)Math.tan( FOV / 2 );

    final static Float Z_NEAR = 0.1f;
    final static Float Z_FAR = 1000.0f;
    final static Float Z_NORMALISATION = ( Z_FAR / ( Z_FAR - Z_NEAR ) );

    final static Vec3d V_CAMERA = new Vec3d(0.0f, 0.0f, 0.0f, 0f);
    final static Vec3d LIGHT_DIRECTION = new Vec3d(0.0f, 0.0f, 1.0f, 0f);


    final static Matrix PROJECTION_MATRIX = new Matrix(new Float [][] {
        {ASPECT_RATIO * FOV_SCALE,  0f,          0f,                  0f                               },
        {0f,                         FOV_SCALE,  0f,                  0f                               },
        {0f,                         0f,          Z_NORMALISATION,    ( -Z_NEAR * Z_NORMALISATION )   },
        {0f,                         0f,          1f,                  0f                               }
    });

    public static Matrix rotationMatrix(float angle) {
        Float sinTheta = (float)Math.sin(angle);
        Float cosTheta = (float)Math.cos(angle);
        
        Float sinHalfTheta = -(float)Math.sin(angle / 2);
        Float cosHalfTheta = -(float)Math.cos(angle / 2);

        Matrix rotationMatrixZX = new Matrix(new Float [][] {
            {cosTheta,      sinTheta * cosHalfTheta,    sinTheta * sinHalfTheta,    0f},
            {-sinTheta,     cosTheta * cosHalfTheta,    cosTheta * sinHalfTheta,    0f},
            {0f,             -sinHalfTheta,              cosHalfTheta,               0f},
            {0f,             0f,                          0f,                          1f}
        });

        return rotationMatrixZX;
    }

    public static Matrix lookAtMatrix(Vec3d forward, Vec3d right, Vec3d offset) {
        Vec3d up = forward.normal(right);

        return new Matrix(new Float[][] {
            {forward.x,                     right.x,                    up.x,                       0f},
            {forward.y,                     right.y,                    up.y,                       0f},
            {forward.z,                     right.z,                    up.z,                       0f},
            {-offset.dotProduct(forward),   -offset.dotProduct(right),  -offset.dotProduct(up),     1f}
        });
    }
}
