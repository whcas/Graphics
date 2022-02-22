package com.will;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.paint.Color;

public class Mesh {
    Vec3d position = new Vec3d(0.0f, 0.0f, 5.0f, 0.0f);

    Matrix lookAt = pointAt(Engine3D.V_CAMERA, Engine3D.V_CAMERA.plus(new Vec3d(0, 0, 1, 0)), new Vec3d(0, 1, 0, 0));

    ArrayList<Triangle> triangles;

    public Mesh(ArrayList<Triangle> triangles) {
        this.triangles = triangles;
    }

    public Mesh() {
        triangles = new ArrayList<Triangle>();
    };

    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    public void add(Mesh newMesh) {
        triangles.addAll(newMesh.getTriangles());
    }

    public void sort() {
        Collections.sort(triangles);
        Collections.reverse(triangles);
    }

    public Mesh rotate(float angle) {
        ArrayList<Triangle> rotatedTriangles = new ArrayList<Triangle>();

        for (Triangle triangle : this.triangles) {
            rotatedTriangles.add(triangle.rotate(angle));
        }

        return new Mesh(rotatedTriangles);
    }
    public Mesh translate(Vec3d translation) {
        ArrayList<Triangle> translatedTriangles = new ArrayList<Triangle>();

        for (Triangle triangle : this.triangles) {
            translatedTriangles.add(triangle.translate(translation));
        }

        return new Mesh(translatedTriangles);
    }
    public Mesh project() {
        ArrayList<Triangle> projectedTriangles = new ArrayList<Triangle>();

        for (Triangle triangle : this.triangles) {
            if (triangle.normalDotProduct(Engine3D.V_CAMERA) < 0) {
                projectedTriangles.add(triangle.project());
            }
        }

        return new Mesh(projectedTriangles);
    }

    public Mesh view() {
        ArrayList<Triangle> viewedTriangles = new ArrayList<Triangle>();

        for (Triangle triangle : this.triangles) {
            if (triangle.normalDotProduct(Engine3D.V_CAMERA) < 0) {
                viewedTriangles.add(triangle.applyMatrix(lookAt));
            }
        }
        
        return new Mesh(viewedTriangles);
    }

    @Override
    public String toString() {
        String out = "";
        for (Triangle triangle : triangles) {
            out += triangle.toString() + "\r\n";
        }
        return out;
    }

    public Mesh toProjected(float angle) {
        Mesh rotatedMesh = this.rotate(angle);
        Mesh translatedMesh = rotatedMesh.translate(position);
        Mesh projectedMesh = translatedMesh.project();
        Mesh viewMesh = projectedMesh.view();
        projectedMesh.sort();
        return projectedMesh;
    }

    public Matrix pointAt(Vec3d pos, Vec3d target, Vec3d up) {
        Vec3d newFoward = target.sub(pos);
        newFoward = newFoward.normalize();

        Vec3d a = newFoward.scale(up.dotProduct(newFoward));
        Vec3d newUp = up.sub(a);

        Vec3d newRight = newUp.normal(newFoward);

        return Engine3D.lookAtMatrix(newFoward, newRight, pos);
    }

    public void updateLookAt(Vec3d pos, Vec3d target, Vec3d up) {
        lookAt = pointAt(pos, target, up);
    }

    public static Mesh loadFromFile(File f, Color color) throws IOException {
        BufferedReader buffReader = new BufferedReader(new FileReader(f));

        ArrayList<Vec3d> vectors = new ArrayList<Vec3d>();
        ArrayList<Triangle> triangles = new ArrayList<Triangle>();

        String line = buffReader.readLine();
        while (line != "" && line != null) {
            String[] parts = line.split("\\s+");
            if (parts[0].equals("v")) {
                vectors.add(
                    new Vec3d(
                        Double.valueOf(parts[1]),
                        Double.valueOf(parts[2]),
                        Double.valueOf(parts[3]),
                        0
                    )
                );
            }
            if (parts[0].equals("f")) {
                triangles.add(
                    new Triangle(
                        vectors.get(Integer.valueOf(parts[1]) - 1),
                        vectors.get(Integer.valueOf(parts[2]) - 1),
                        vectors.get(Integer.valueOf(parts[3]) - 1),

                        color)
                );
            }
            line = buffReader.readLine();
        }

        buffReader.close();
        return new Mesh(triangles);
    }
}