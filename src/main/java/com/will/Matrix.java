package com.will;

public class Matrix {
    Vec3d x, y, z, w;

    public Matrix(Vec3d x, Vec3d y, Vec3d z, Vec3d w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Matrix(double[][] m) {
        this.x = new Vec3d(m[0][0], m[0][1], m[0][2], m[0][3]);
        this.y = new Vec3d(m[1][0], m[1][1], m[1][2], m[1][3]);
        this.z = new Vec3d(m[2][0], m[2][1], m[2][2], m[2][3]);
        this.w = new Vec3d(m[3][0], m[3][1], m[3][2], m[3][3]);
    }

    public Matrix() {
        this.x = new Vec3d();
        this.y = new Vec3d();
        this.z = new Vec3d();
        this.w = new Vec3d();
    }

    public void changeMatrix(int collum, int row, double in) {
        switch(collum) {
            case 0:
                x.changeVector(row, in);
                break;
            case 1:
                y.changeVector(row, in);
                break;
            case 2:
                z.changeVector(row, in);
                break;
            case 3:
                w.changeVector(row, in);
                break;
        }
    }

    public Vec3d vectorMatrixMultipli(Vec3d vetor) {
        return new Vec3d(
            vetor.x * this.x.x + vetor.y * this.x.y + vetor.z * this.x.z + vetor.w * this.x.w,
            vetor.x * this.y.x + vetor.y * this.y.y + vetor.z * this.y.z + vetor.w * this.y.w,
            vetor.x * this.z.x + vetor.y * this.z.y + vetor.z * this.z.z + vetor.w * this.z.w,
            vetor.x * this.w.x + vetor.y * this.w.y + vetor.z * this.w.z + vetor.w * this.w.w
        );

        /*Vec3d xVec = this.x.scale(vetor.x);
        Vec3d yVec = this.y.scale(vetor.y);
        Vec3d zVec = this.z.scale(vetor.z);
        Vec3d wVec = this.w.scale(vetor.w);

        return 
            xVec.plus(
            yVec.plus(
            zVec.plus(
            wVec
        )));*/
    }
}
