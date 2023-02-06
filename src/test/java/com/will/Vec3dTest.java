package com.will;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
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
        float r1 = r.nextFloat();

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
        float r1 = r.nextFloat();

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

        float sum = v1.sum();

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

        float dotProduct = v1.dotProduct(v2);

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
}