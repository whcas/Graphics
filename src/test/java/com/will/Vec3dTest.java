package com.will;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;


public class Vec3dTest {
    public static final double DELTA = 0.01;
    Random r = new Random();
    double x;
    double y;
    double z;
    double w;

    @Before
    public void setup() {
        x = r.nextDouble();
        y = r.nextDouble();
        z = r.nextDouble();
        w = r.nextDouble();
    }

    @Test
    public void zeroVecTest() {
        Vec3d v = new Vec3d();

        assertEquals(0, v.get(0), DELTA);
        assertEquals(0, v.get(1), DELTA);
        assertEquals(0, v.get(2), DELTA);
        assertEquals(0, v.get(3), DELTA);
    }

    @Test
    public void varInitTest() {
        Vec3d v = new Vec3d(x, y, z, w);

        assertEquals(x, v.get(0), DELTA);
        assertEquals(y, v.get(1), DELTA);
        assertEquals(z, v.get(2), DELTA);
        assertEquals(w, v.get(3), DELTA);
    }

    @Test
    public void setTest() {
        Vec3d v = new Vec3d();

        v.set(0, x);
        v.set(1, y);
        v.set(2, z);
        v.set(3, w);

        assertEquals(x, v.get(0), DELTA);
        assertEquals(y, v.get(1), DELTA);
        assertEquals(z, v.get(2), DELTA);
        assertEquals(w, v.get(3), DELTA);
    }

    @Test
    public void plusTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);
        double r1 = r.nextDouble();

        Vec3d v2 = v1.plus(r1);

        assertEquals(v1.get(0) + r1, v2.get(0), DELTA);
        assertEquals(v1.get(1) + r1, v2.get(1), DELTA);
        assertEquals(v1.get(2) + r1, v2.get(2), DELTA);
        assertEquals(v1.get(3) + r1, v2.get(3), DELTA);
    }

    @Test
    public void vectorPlusTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);

        x = r.nextDouble();
        y = r.nextDouble();
        z = r.nextDouble();
        w = r.nextDouble();
        Vec3d v2 = new Vec3d(x, y, z, w);

        Vec3d v3 = v1.plus(v2);

        assertEquals(v1.get(0) + v2.get(0), v3.get(0), DELTA);
        assertEquals(v1.get(1) + v2.get(1), v3.get(1), DELTA);
        assertEquals(v1.get(2) + v2.get(2), v3.get(2), DELTA);
        assertEquals(v1.get(3) + v2.get(3), v3.get(3), DELTA);
    }

    @Test
    public void subtractionTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);
        double r1 = r.nextDouble();

        Vec3d v2 = v1.sub(r1);

        assertEquals(v1.get(0) - r1, v2.get(0), DELTA);
        assertEquals(v1.get(1) - r1, v2.get(1), DELTA);
        assertEquals(v1.get(2) - r1, v2.get(2), DELTA);
        assertEquals(v1.get(3) - r1, v2.get(3), DELTA);
    }

    @Test
    public void vectorSubtractionTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);

        x = r.nextDouble();
        y = r.nextDouble();
        z = r.nextDouble();
        w = r.nextDouble();
        Vec3d v2 = new Vec3d(x, y, z, w);

        Vec3d v3 = v1.sub(v2);

        assertEquals(v1.get(0) - v2.get(0), v3.get(0), DELTA);
        assertEquals(v1.get(1) - v2.get(1), v3.get(1), DELTA);
        assertEquals(v1.get(2) - v2.get(2), v3.get(2), DELTA);
        assertEquals(v1.get(3) - v2.get(3), v3.get(3), DELTA);
    }

    @Test
    public void multiplicationTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);
        double r1 = r.nextDouble();

        Vec3d v2 = v1.scale(r1);

        assertEquals(v1.get(0) * r1, v2.get(0), DELTA);
        assertEquals(v1.get(1) * r1, v2.get(1), DELTA);
        assertEquals(v1.get(2) * r1, v2.get(2), DELTA);
        assertEquals(v1.get(3) * r1, v2.get(3), DELTA);
    }

    @Test
    public void vectorMultiplicationTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);

        x = r.nextDouble();
        y = r.nextDouble();
        z = r.nextDouble();
        w = r.nextDouble();
        Vec3d v2 = new Vec3d(x, y, z, w);

        Vec3d v3 = v1.multiply(v2);

        assertEquals(v1.get(0) * v2.get(0), v3.get(0), DELTA);
        assertEquals(v1.get(1) * v2.get(1), v3.get(1), DELTA);
        assertEquals(v1.get(2) * v2.get(2), v3.get(2), DELTA);
        assertEquals(v1.get(3) * v2.get(3), v3.get(3), DELTA);
    }

    @Test
    public void divisionTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);
        double r1 = r.nextDouble();

        Vec3d v2 = v1.scale(1/r1);

        assertEquals(v1.get(0) / r1, v2.get(0), DELTA);
        assertEquals(v1.get(1) / r1, v2.get(1), DELTA);
        assertEquals(v1.get(2) / r1, v2.get(2), DELTA);
        assertEquals(v1.get(3) / r1, v2.get(3), DELTA);
    }

    @Test
    public void sumTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);

        double sum = v1.sum();

        assertEquals((x + y + z + w), sum, DELTA);
    }

    @Test
    public void dotProductTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);

        x = r.nextDouble();
        y = r.nextDouble();
        z = r.nextDouble();
        w = r.nextDouble();
        Vec3d v2 = new Vec3d(x, y, z, w);

        double dotProduct = v1.dotProduct(v2);

        assertEquals(
                v1.get(0) * v2.get(0) +
                        v1.get(1) * v2.get(1) +
                        v1.get(2) * v2.get(2) +
                        v1.get(3) * v2.get(3),
                dotProduct,
                DELTA
        );
    }

    @Test
    public void vectorNormalTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);

        x = r.nextDouble();
        y = r.nextDouble();
        z = r.nextDouble();
        w = r.nextDouble();
        Vec3d v2 = new Vec3d(x, y, z, w);

        Vec3d v3 = v1.normal(v2);

        assertEquals((v1.get(1) * v2.get(2)) - (v1.get(2) * v2.get(1)), v3.get(0), DELTA);
        assertEquals((v1.get(2) * v2.get(0)) - (v1.get(0) * v2.get(2)), v3.get(1), DELTA);
        assertEquals((v1.get(0) * v2.get(1)) - (v1.get(1) * v2.get(0)), v3.get(2), DELTA);
        assertEquals(v1.get(3), v3.get(3), DELTA);
    }

    @Test
    public void vectorMatrixMultiplicationTest() {
        Vec3d v1 = new Vec3d(x * 100, y * 100, z * 100, w * 100);
        Vec3d mv1 = new Vec3d(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);
        Vec3d mv2 = new Vec3d(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);
        Vec3d mv3 = new Vec3d(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);
        Vec3d mv4 = new Vec3d(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);
        Matrix m1 = new Matrix(mv1, mv2, mv3, mv4);

        Vec3d t1 = mv1.scale(v1.get(0));
        Vec3d t2 = mv2.scale(v1.get(1));
        Vec3d t3 = mv3.scale(v1.get(2));
        Vec3d t4 = mv4.scale(v1.get(3));
        Vec3d v2 = t1.plus(t2.plus(t3.plus(t4)));

        assertEquals(v2.toString(), v1.matrixMultiply(m1).toString());
    }

    @Test
    public void oldMatrixMultiplicationMethodTest() {
        Vec3d v1 = new Vec3d(x * 100, y * 100, z * 100, w * 100);
        Vec3d mv1 = new Vec3d(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);
        Vec3d mv2 = new Vec3d(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);
        Vec3d mv3 = new Vec3d(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);
        Vec3d mv4 = new Vec3d(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);
        Matrix m1 = new Matrix(mv1, mv2, mv3, mv4);
        
        Vec3d oldMatrixResult = new Vec3d(
                v1.get(0) * m1.get(0).get(0) + v1.get(1) * m1.get(1).get(0) + v1.get(2) * m1.get(2).get(0) + v1.get(3) * m1.get(3).get(0),
                v1.get(0) * m1.get(0).get(1) + v1.get(1) * m1.get(1).get(1) + v1.get(2) * m1.get(2).get(1) + v1.get(3) * m1.get(3).get(1),
                v1.get(0) * m1.get(0).get(2) + v1.get(1) * m1.get(1).get(2) + v1.get(2) * m1.get(2).get(2) + v1.get(3) * m1.get(3).get(2),
                v1.get(0) * m1.get(0).get(3) + v1.get(1) * m1.get(1).get(3) + v1.get(2) * m1.get(2).get(3) + v1.get(3) * m1.get(3).get(3)
        );

        assertEquals(oldMatrixResult.toString(), v1.matrixMultiply(m1).toString());
    }
}