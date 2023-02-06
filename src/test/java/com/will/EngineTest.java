package com.will;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Random;

public class EngineTest {
    Random r = new Random();

    @Test
    public void projectionTest() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        double z = r.nextDouble();
        Vec3d v = new Vec3d(x, y, z, 1);

        Vec3d r1 = new Vec3d(
                (Engine3D.ASPECT_RATIO * Engine3D.FOV_SCALE * x)/z,
                (Engine3D.FOV_SCALE * y)/z,
                (z * Engine3D.Z_NORMALISATION - (Engine3D.Z_NEAR * Engine3D.Z_NORMALISATION))/z,
                1
        );

        Vec3d r2 = v.matrixMultiply(Engine3D.PROJECTION_MATRIX);

        if (r2.get(3) != 0) r2 = r2.scale(1 / r2.get(3));

        assertEquals(r1.toString(), r2.toString());
    }
}
