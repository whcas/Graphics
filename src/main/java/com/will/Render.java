package com.will;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

public class Render {
    ArrayList<Mesh> objects;

    /**
     * Output render to video
     * @param seconds the number of seconds to render
     * @param frameRate the number of frames to render per second
     */
    public void render(int seconds, int frameRate) {
        ArrayList<BufferedImage> frames = new ArrayList<>();
        for (int i = 0; i < seconds * frameRate; i++) {
            for (Mesh mesh: objects) {

            }
        }
    }

    public void addObject(Mesh object) {
        objects.add(object);
    }

    public BufferedImage toImage(Mesh mesh) {
        // Output
        BufferedImage img = new BufferedImage(Engine3D.RESOLUTION[0], Engine3D.RESOLUTION[0], BufferedImage.TYPE_INT_ARGB);
        Graphics gr = img.getGraphics();

        // For each triangle in mesh add poly to output
        for (Triangle tri : mesh.getTriangles()) {
            gr.drawPolygon(triToPoly(tri));
        }
        return img;
    }

    private Polygon triToPoly(Triangle tri) {
        return new Polygon(tri.xpoints(), tri.ypoints(), 3);
    }
}
