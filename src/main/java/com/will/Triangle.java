package com.will;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Triangle implements Comparable<Triangle> {
    Vec3d a, b, c;
    Color color;

    public Triangle(Vec3d a, Vec3d b, Vec3d c, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    public Triangle(ArrayList<Vec3d> vectors, Color color) {
        this.a = vectors.get(0);
        this.b = vectors.get(1);
        this.c = vectors.get(2);
        this.color = color;
    }

    public Double[] toDoubles() {
        return new Double[] {
            (double)a.x, (double)a.y,
            (double)b.x, (double)b.y,
            (double)c.x, (double)c.y,
        };
    }

    public ArrayList<Vec3d> getVectors() {
        return new ArrayList<Vec3d>() {{
            add(a);
            add(b);
            add(c);
        }};
    }

    public Triangle applyMatrix(Matrix matrix) {
        return new Triangle(
            a.matrixMultiply(matrix),
            b.matrixMultiply(matrix),
            c.matrixMultiply(matrix),
            color
        );
    }

    public Triangle rotate(float angle) {
        return new Triangle(
            a.rotate(angle),
            b.rotate(angle),
            c.rotate(angle),
            this.color);
    }
    public Triangle translate(Vec3d translation) {
        return new Triangle(
            a.plus(translation),
            b.plus(translation),
            c.plus(translation),
            this.color);
    }

    public Triangle project() {
        Vec3d lightDir = Engine3D.LIGHT_DIRECTION;
        lightDir = lightDir.normalize();

        Float lightDotProd = lightDir.dotProduct(this.getNormal());
        lightDotProd = 1 - lightDotProd;
        lightDotProd = lightDotProd / 2;
        return new Triangle(
            a.project(),
            b.project(),
            c.project(),
            Color.color(
                this.color.getRed() * lightDotProd,
                this.color.getGreen() * lightDotProd,
                this.color.getBlue() * lightDotProd,
                1.0
            )
        );
    }
    public Vec3d getNormal() {
        Vec3d normal, line1, line2;
        line1 = this.b.sub(this.a);
        
        line2 = this.c.sub(this.a);

        normal = line1.normal(line2);

        return normal.normalize();
    }
    public Float normalDotProduct(Vec3d vec) {
        Vec3d cameraAdjusted = this.a.sub(vec);
        Vec3d normal = this.getNormal();

        return normal.dotProduct(cameraAdjusted);
    }

    @Override
    public String toString() {
        return "a = (" + a + ") b = (" + b + ") c = (" + c + ")";
    }

    @Override
    public int compareTo(Triangle o) {
        Float thisAverageZ = ( this.a.z + this.b.z + this.c.z ) / 3;
        Float otherAverageZ = ( o.a.z + o.b.z + o.c.z ) / 3;
        if (thisAverageZ < otherAverageZ) { return -1; }
        if (thisAverageZ > otherAverageZ) { return 1; }
        return 0;
    }

}
