package com.will;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;


public class Vec3dTest {
    public static final double DELTA = 0.01;
    Random r = new Random();
    float x;
    float y;
    float z;
    float w;

    @Before
    public void setup() {
        x = r.nextFloat();
        y = r.nextFloat();
        z = r.nextFloat();
        w = r.nextFloat();
    }

    @Test
    public void zeroVecTest() {
        Vec3d v = new Vec3d();

        assertEquals(0, v.x, DELTA);
        assertEquals(0, v.y, DELTA);
        assertEquals(0, v.z, DELTA);
        assertEquals(0, v.w, DELTA);
    }

    @Test
    public void varInitTest() {
        Vec3d v = new Vec3d(x, y, z, w);

        assertEquals(x, v.x, DELTA);
        assertEquals(y, v.y, DELTA);
        assertEquals(z, v.z, DELTA);
        assertEquals(w, v.w, DELTA);
    }

    @Test
    public void setTest() {
        Vec3d v = new Vec3d();

        v.x = x;
        v.y = y;
        v.z = z;
        v.w = w;

        assertEquals(x, v.x, DELTA);
        assertEquals(y, v.y, DELTA);
        assertEquals(z, v.z, DELTA);
        assertEquals(w, v.w, DELTA);
    }

    @Test
    public void plusTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);
        double r1 = r.nextFloat();

        Vec3d v2 = v1.plus(r1);

        assertEquals(v1.x + r1, v2.x, DELTA);
        assertEquals(v1.y + r1, v2.y, DELTA);
        assertEquals(v1.z + r1, v2.z, DELTA);
        assertEquals(v1.w + r1, v2.w, DELTA);
    }

    @Test
    public void vectorPlusTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);

        x = r.nextFloat();
        y = r.nextFloat();
        z = r.nextFloat();
        w = r.nextFloat();
        Vec3d v2 = new Vec3d(x, y, z, w);

        Vec3d v3 = v1.plus(v2);

        assertEquals(v1.x + v2.x, v3.x, DELTA);
        assertEquals(v1.y + v2.y, v3.y, DELTA);
        assertEquals(v1.z + v2.z, v3.z, DELTA);
        assertEquals(v1.w + v2.w, v3.w, DELTA);
    }

    @Test
    public void subtractionTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);
        double r1 = r.nextFloat();

        Vec3d v2 = v1.sub(r1);

        assertEquals(v1.x - r1, v2.x, DELTA);
        assertEquals(v1.y - r1, v2.y, DELTA);
        assertEquals(v1.z - r1, v2.z, DELTA);
        assertEquals(v1.w - r1, v2.w, DELTA);
    }

    @Test
    public void vectorSubtractionTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);

        x = r.nextFloat();
        y = r.nextFloat();
        z = r.nextFloat();
        w = r.nextFloat();
        Vec3d v2 = new Vec3d(x, y, z, w);

        Vec3d v3 = v1.sub(v2);

        assertEquals(v1.x - v2.x, v3.x, DELTA);
        assertEquals(v1.y - v2.y, v3.y, DELTA);
        assertEquals(v1.z - v2.z, v3.z, DELTA);
        assertEquals(v1.w - v2.w, v3.w, DELTA);
    }

    @Test
    public void multiplicationTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);
        float r1 = r.nextFloat();

        Vec3d v2 = v1.scale(r1);

        assertEquals(v1.x * r1, v2.x, DELTA);
        assertEquals(v1.y * r1, v2.y, DELTA);
        assertEquals(v1.z * r1, v2.z, DELTA);
        assertEquals(v1.w * r1, v2.w, DELTA);
    }

    @Test
    public void vectorMultiplicationTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);

        x = r.nextFloat();
        y = r.nextFloat();
        z = r.nextFloat();
        w = r.nextFloat();
        Vec3d v2 = new Vec3d(x, y, z, w);

        Vec3d v3 = v1.multiply(v2);

        assertEquals(v1.x * v2.x, v3.x, DELTA);
        assertEquals(v1.y * v2.y, v3.y, DELTA);
        assertEquals(v1.z * v2.z, v3.z, DELTA);
        assertEquals(v1.w * v2.w, v3.w, DELTA);
    }

    @Test
    public void divisionTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);
        float r1 = r.nextFloat();

        Vec3d v2 = v1.scale(1/r1);

        assertEquals(v1.x / r1, v2.x, DELTA);
        assertEquals(v1.y / r1, v2.y, DELTA);
        assertEquals(v1.z / r1, v2.z, DELTA);
        assertEquals(v1.w / r1, v2.w, DELTA);
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

        x = r.nextFloat();
        y = r.nextFloat();
        z = r.nextFloat();
        w = r.nextFloat();
        Vec3d v2 = new Vec3d(x, y, z, w);

        double dotProduct = v1.dotProduct(v2);

        assertEquals(
                v1.x * v2.x +
                        v1.y * v2.y +
                        v1.z * v2.z +
                        v1.w * v2.w,
                dotProduct,
                DELTA
        );
    }

    @Test
    public void vectorNormalTest() {
        Vec3d v1 = new Vec3d(x, y, z, w);

        x = r.nextFloat();
        y = r.nextFloat();
        z = r.nextFloat();
        w = r.nextFloat();
        Vec3d v2 = new Vec3d(x, y, z, w);

        Vec3d v3 = v1.normal(v2);

        assertEquals((v1.y * v2.z) - (v1.z * v2.y), v3.x, DELTA);
        assertEquals((v1.z * v2.x) - (v1.x * v2.z), v3.y, DELTA);
        assertEquals((v1.x * v2.y) - (v1.y * v2.x), v3.z, DELTA);
        assertEquals(v1.w, v3.w, DELTA);
    }

    @Test
    public void vectorMatrixMultiplicationTest() {
        Vec3d v1 = new Vec3d(x * 100, y * 100, z * 100, w * 100);
        Vec3d mv1 = new Vec3d(r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100);
        Vec3d mv2 = new Vec3d(r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100);
        Vec3d mv3 = new Vec3d(r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100);
        Vec3d mv4 = new Vec3d(r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100);
        Matrix m1 = new Matrix(mv1, mv2, mv3, mv4);

        Vec3d t1 = mv1.scale(v1.x);
        Vec3d t2 = mv2.scale(v1.y);
        Vec3d t3 = mv3.scale(v1.z);
        Vec3d t4 = mv4.scale(v1.w);
        Vec3d v2 = t1.plus(t2.plus(t3.plus(t4)));

        assertEquals(v2.toString(), v1.matrixMultiply(m1).toString());
    }

    @Test
    public void oldMatrixMultiplicationMethodTest() {
        Vec3d v1 = new Vec3d(x * 100, y * 100, z * 100, w * 100);
        Vec3d mv1 = new Vec3d(r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100);
        Vec3d mv2 = new Vec3d(r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100);
        Vec3d mv3 = new Vec3d(r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100);
        Vec3d mv4 = new Vec3d(r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100, r.nextFloat() * 100);
        Matrix m1 = new Matrix(mv1, mv2, mv3, mv4);
        
        Vec3d oldMatrixResult = new Vec3d(
                v1.x * m1.x.x + v1.y * m1.y.x + v1.z * m1.z.x + v1.w * m1.w.x,
                v1.x * m1.x.y + v1.y * m1.y.y + v1.z * m1.z.y + v1.w * m1.w.y,
                v1.x * m1.x.z + v1.y * m1.y.z + v1.z * m1.z.z + v1.w * m1.w.z,
                v1.x * m1.x.w + v1.y * m1.y.w + v1.z * m1.z.w + v1.w * m1.w.w
        );

        assertEquals(oldMatrixResult.toString(), v1.matrixMultiply(m1).toString());
    }
}