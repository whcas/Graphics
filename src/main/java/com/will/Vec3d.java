package com.will;

import java.text.DecimalFormat;

public class Vec3d {
    float x, y, z, w;

    public Vec3d(Float x, Float y, Float z, Float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec3d() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
    }

    public boolean equalTo(Vec3d o) {
        return (this.x == o.x && this.y == o.y && this.z == o.z && this.w == o.w);
    }

    public void changeVector(int row, Float in) {
        switch(row) {
            case 0:
                this.x = in;
                break;
            case 1:
                this.y = in;
                break;
            case 2:
                this.z = in;
                break;
            case 3:
                this.w = in;
                break;
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.###");
        return "x = " + df.format(x) + " y = " + df.format(y) + " z = " + df.format(z) + " w = " + df.format(w);
    }

    public Vec3d plus(Vec3d other) {
        return new Vec3d(
            this.x + other.x,
            this.y + other.y,
            this.z + other.z,
            this.w + other.w
        );
    }

    public Vec3d plus(Number other) {
        return new Vec3d(
                this.x + other.floatValue(),
                this.y + other.floatValue(),
                this.z + other.floatValue(),
                this.w + other.floatValue()
        );
    }

    public Vec3d sub(Vec3d other) {
        return new Vec3d(
            this.x - other.x,
            this.y - other.y,
            this.z - other.z,
            this.w - other.w
        );
    }

    public Vec3d sub(Number other) {
        return new Vec3d(
                this.x - other.floatValue(),
                this.y - other.floatValue(),
                this.z - other.floatValue(),
                this.w - other.floatValue()
        );
    }

    public Vec3d multiply(Vec3d other) {
        return new Vec3d(
                this.x * other.x,
                this.y * other.y,
                this.z * other.z,
                this.w * other.w
        );
    }

    public float sum() {
        return (
                this.x +
                this.y +
                this.z +
                this.w
        );
    }

    public Vec3d scale(Float scaler) {
        return new Vec3d(
            this.x * scaler,
            this.y * scaler,
            this.z * scaler,
            this.w * scaler
        );
    }

    public Float dotProduct(Vec3d other) {
        return this.multiply(other).sum();
    }

    public Vec3d normal(Vec3d other) {
        return new Vec3d(
            (this.y * other.z) - (this.z * other.y),
            (this.z * other.x) - (this.x * other.z),
            (this.x * other.y) - (this.y * other.x),
            this.w
        );
    }

    public Vec3d matrixMultiply(Matrix matrix) {
        return new Vec3d(
                this.x * matrix.x.x + this.x * matrix.x.y + this.x * matrix.x.z + this.x * matrix.x.w,
                this.y * matrix.y.x + this.y * matrix.y.y + this.y * matrix.y.z + this.y * matrix.y.w,
                this.z * matrix.z.x + this.z * matrix.z.y + this.z * matrix.z.z + this.z * matrix.z.w,
                this.w * matrix.w.x + this.w * matrix.w.y + this.w * matrix.w.z + this.w * matrix.w.w
        );
    }

    public Float length() {
        return (float)Math.sqrt(dotProduct(this));
    }

    public Vec3d normalize() {
        return this.scale(1 / this.length());
    }

    public Vec3d rotate(float angle) {
        return Engine3D.rotationMatrix(angle).vectorMatrixMultipli(this);
    }
    public Vec3d project() {
        Vec3d newVec = this;
        newVec.w = 1;
        newVec = Engine3D.PROJECTION_MATRIX.vectorMatrixMultipli(newVec);

        if (newVec.w != 0) {
            newVec = newVec.scale(1 / newVec.w);
        }

        newVec = newVec.plus(new Vec3d(1.0f, 1.0f, 0.0f, 0.0f));
        Matrix scaleMatrix = new Matrix() {{
            changeMatrix(0, 0, 0.5f * Engine3D.RESOLUTION[0]);
            changeMatrix(1, 1, 0.5f * Engine3D.RESOLUTION[1]);
            changeMatrix(2, 2, 1f);
        }};
        return scaleMatrix.vectorMatrixMultipli(newVec);
    }

    public float get(int i) {
        switch (i) {
            case 0: return x;
            case 1: return y;
            case 2: return z;
            case 3: return w;
            default: throw new ArrayIndexOutOfBoundsException();
        }
    }

    public float[] toArray() {
        return new float[] {x, y, z, w};
    }
}
