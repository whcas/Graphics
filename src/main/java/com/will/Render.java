package com.will;

import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Render {
    final static String SHAPE_PATH = "./src/main/Renders/teapot.obj";
    ArrayList<Mesh> objects = new ArrayList<>();

    public static void main(String[] args) {
        Render r = new Render();

        try {
            r.addObject(Mesh.loadFromFile(new File(SHAPE_PATH), Color.PINK));
            r.render(1, 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Output render to video
     * @param seconds the number of seconds to render
     * @param frameRate the number of frames to render per second
     */
    public void render(int seconds, int frameRate) {
        //ArrayList<BufferedImage> frames = new ArrayList<>();
        for (int i = 0; i < seconds * frameRate; i++) {
            for (Mesh mesh: objects) {
                //frames.add(toImage(mesh));
                BufferedImage image = toImage(mesh);

                File outputfile = new File("render" + Math.floorDiv(i, seconds) + "s" + i % seconds + "f.jpg");
                try {
                    ImageIO.write(image, "jpg", outputfile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
