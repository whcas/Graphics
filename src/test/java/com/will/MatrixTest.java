package com.will;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Random;

public class MatrixTest {
    Random r = new Random();

    @Test
    public void zeroMatrixTest() {
        Matrix m = new Matrix();

        for (float[] a : m.toArray()) {
            for (float f : a) {
                assertEquals(0.0, f, 0.0);
            }
        }
    }

    @Test
    public void listMatrixTest() {
        float[][] a = new float[4][4];
        for (int i = 0; i < 16; i++) {
            a[Math.floorDiv(i, 4)][i % 4] = r.nextFloat();
        }
        Matrix m = new Matrix(a);

        for (int i = 0; i < 16; i++) {
            int x = Math.floorDiv(i, 4);
            int y = i % 4;

            assertEquals(a[x][y], m.get(x, y), 0.0);
        }
    }

    @Test
    public void vec3dMatrixTest() {
        Vec3d[] a = new Vec3d[4];
        for (int i = 0; i < 4; i++) {
            a[i] = new Vec3d(r.nextFloat(), r.nextFloat(), r.nextFloat(), r.nextFloat());
        }

        Matrix m = new Matrix(a[0], a[1], a[2], a[3]);

        for (int i = 0; i < 4; i++) {
            assertEquals(a[i].toString(), m.get(i).toString());
        }
    }

    @Test
    public void changeMatrixTest() {
        float[][] a = new float[4][4];
        for (int i = 0; i < 16; i++) {
            a[Math.floorDiv(i, 4)][i % 4] = r.nextFloat();
        }
        Matrix m = new Matrix(a);

        int x = (int) (r.nextDouble() * 3.0);
        int y = (int) (r.nextDouble() * 3.0);
        float v = r.nextFloat();

        a[x][y] = v;
        m.changeMatrix(x, y, v);

        assertEquals(a[x][y], m.get(x, y), 0.0);
    }
}