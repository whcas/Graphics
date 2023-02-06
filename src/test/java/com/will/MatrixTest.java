package com.will;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Random;

public class MatrixTest {
    Random r = new Random();

    @Test
    public void zeroMatrixTest() {
        Matrix m = new Matrix();

        for (double v : m.toDense()) {
            assertEquals(0.0, v, 0.0);
        }
    }

    @Test
    public void listMatrixTest() {
        double[][] a = new double[4][4];
        for (int i = 0; i < 16; i++) {
            a[Math.floorDiv(i, 4)][i % 4] = r.nextDouble();
        }
        Matrix m = new Matrix(a);

        for (int i = 0; i < 16; i++) {
            int x = Math.floorDiv(i, 4);
            int y = i % 4;

            assertEquals(a[x][y], m.toDense().get(x, y), 0.0);
        }
    }

    @Test
    public void vec3dMatrixTest() {
        Vec3d[] a = new Vec3d[4];
        for (int i = 0; i < 4; i++) {
            a[i] = new Vec3d(r.nextDouble(), r.nextDouble(), r.nextDouble(), r.nextDouble());
        }

        Matrix m = new Matrix(a[0], a[1], a[2], a[3]);

        for (int i = 0; i < 4; i++) {
            assertEquals(a[i].toString(), m.get(i).toString());
        }
    }

    @Test
    public void changeMatrixTest() {
        double[][] a = new double[4][4];
        for (int i = 0; i < 16; i++) {
            a[Math.floorDiv(i, 4)][i % 4] = r.nextDouble();
        }
        Matrix m = new Matrix(a);

        int x = (int) (r.nextDouble() * 3.0);
        int y = (int) (r.nextDouble() * 3.0);
        double v = r.nextDouble();

        a[x][y] = v;
        m.changeMatrix(x, y, v);

        assertEquals(a[x][y], m.toDense().get(x, y), 0.0);
    }
}