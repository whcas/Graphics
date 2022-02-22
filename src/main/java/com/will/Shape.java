package com.will;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Shape {
    // Returns Unit cubes mesh
    public static Mesh Cube() {
        // points on a unit cube
        ArrayList<Vec3d> vertList = new ArrayList<Vec3d>(8) {{
                add(new Vec3d(0.0f,0.0f,0.0f, 0.0f));
                add(new Vec3d(0.0f,0.0f,1.0f, 0.0f));
                add(new Vec3d(0.0f,1.0f,0.0f, 0.0f));
                add(new Vec3d(0.0f,1.0f,1.0f, 0.0f));
                add(new Vec3d(1.0f,0.0f,0.0f, 0.0f));
                add(new Vec3d(1.0f,0.0f,1.0f, 0.0f));
                add(new Vec3d(1.0f,1.0f,0.0f, 0.0f));
                add(new Vec3d(1.0f,1.0f,1.0f, 0.0f));
        }};

        // triangles in a unit cube
        Mesh Cube = new Mesh(new ArrayList<Triangle>() {{
            // South
            add(new Triangle(vertList.get(0), vertList.get(2), vertList.get(6), Color.RED));
            add(new Triangle(vertList.get(0), vertList.get(6), vertList.get(4), Color.RED));
            // West
            add(new Triangle(vertList.get(0), vertList.get(5), vertList.get(1), Color.RED));
            add(new Triangle(vertList.get(0), vertList.get(4), vertList.get(5), Color.RED));
            // Bottom
            add(new Triangle(vertList.get(0), vertList.get(1), vertList.get(3), Color.RED));
            add(new Triangle(vertList.get(0), vertList.get(3), vertList.get(2), Color.RED));
            // Top
            add(new Triangle(vertList.get(7), vertList.get(6), vertList.get(2), Color.RED));
            add(new Triangle(vertList.get(7), vertList.get(2), vertList.get(3), Color.RED));
            // East
            add(new Triangle(vertList.get(7), vertList.get(5), vertList.get(4), Color.RED));
            add(new Triangle(vertList.get(7), vertList.get(4), vertList.get(6), Color.RED));
            // North
            add(new Triangle(vertList.get(7), vertList.get(3), vertList.get(1), Color.RED));
            add(new Triangle(vertList.get(7), vertList.get(1), vertList.get(5), Color.RED));
        }});

        return Cube;
    }
    public static Mesh Sphere(int detailOrder, Color color) {
        ArrayList<Vec3d> unitPoints = new ArrayList<Vec3d>() {{
            add(new Vec3d(1.0f, 0.0f, 0.0f, 0.0f));
            add(new Vec3d(-1.0f, 0.0f, 0.0f, 0.0f));
            add(new Vec3d(0.0f, 1.0f, 0.0f, 0.0f));
            add(new Vec3d(0.0f, -1.0f, 0.0f, 0.0f));
            add(new Vec3d(0.0f, 0.0f, 1.0f, 0.0f));
            add(new Vec3d(0.0f, 0.0f, -1.0f, 0.0f));
        }};

        ArrayList<Triangle> octahedron = new ArrayList<Triangle>() {{
            add(new Triangle(unitPoints.get(2) ,unitPoints.get(0) ,unitPoints.get(5) , color));
            add(new Triangle(unitPoints.get(2) ,unitPoints.get(5) ,unitPoints.get(1) , color));
            add(new Triangle(unitPoints.get(2) ,unitPoints.get(1) ,unitPoints.get(4) , color));
            add(new Triangle(unitPoints.get(2) ,unitPoints.get(4) ,unitPoints.get(0) , color));
            add(new Triangle(unitPoints.get(3) ,unitPoints.get(0) ,unitPoints.get(4) , color));
            add(new Triangle(unitPoints.get(3) ,unitPoints.get(4) ,unitPoints.get(1) , color));
            add(new Triangle(unitPoints.get(3) ,unitPoints.get(1) ,unitPoints.get(5) , color));
            add(new Triangle(unitPoints.get(3) ,unitPoints.get(5) ,unitPoints.get(0) , color));
        }};

        Mesh sphereMesh = new Mesh();
        for (Triangle triangle : octahedron) {
            sphereMesh.add(SubDivideTriangle(triangle, detailOrder));
        }

        return sphereMesh;
    }

    private static Mesh SubDivideTriangle(Triangle triangle, int subDivisionDepth) {
        if (subDivisionDepth == 0) {
            return new Mesh(
                new ArrayList<Triangle>() {{
                    add(triangle);
                }}
            );
        }
        ArrayList<Triangle> newTriangles = new ArrayList<Triangle>();

        ArrayList<Vec3d> triangleVectors = triangle.getVectors();
        ArrayList<Vec3d> newVectors = new ArrayList<Vec3d>();
        for (int i = 0; i < triangle.getVectors().size(); i++) {
            newVectors.add(new Vec3d(
                triangleVectors.get(i).x + triangleVectors.get((i + 1) % triangle.getVectors().size()).x,
                triangleVectors.get(i).y + triangleVectors.get((i + 1) % triangle.getVectors().size()).y,
                triangleVectors.get(i).z + triangleVectors.get((i + 1) % triangle.getVectors().size()).z,
                0.0f
            ));
        }
        newTriangles.add(new Triangle(newVectors, triangle.color));

        for (int i = 0; i < triangle.getVectors().size(); i++) {
            newTriangles.add(new Triangle(triangleVectors.get(i), newVectors.get(i), newVectors.get((i + 1) % triangle.getVectors().size()), triangle.color));
        }

        subDivisionDepth --;
        Mesh output = new Mesh();

        for (Triangle newTriangle : newTriangles) {
            output.add(SubDivideTriangle(newTriangle, subDivisionDepth));
        }

        return output;
    }
}
