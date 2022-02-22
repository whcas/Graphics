package com.will;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * JavaFX App
 */
public class App extends Application {
    final static Timer clockTimer = new Timer(true);

    static Group meshGroup = new Group();
    public static void main(String[] args) {
        // JavaFX stuff
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("3D Engine");

        // JavaFX stuff
        Scene scene = setupScene(primaryStage);
        Pane pane = setupPane(); 

        meshGroup = new Group();
        
        // Get triangles to draw
        //Mesh shape = Mesh.Cube();
        Mesh shape = null;
        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath.concat("path to the property file"));
        try {
            shape = Mesh.loadFromFile(new File("./src/main/Renders/teapot.obj"), Color.PINK);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (shape == null) {
                shape = Shape.Cube();
            }
        }
        Mesh projectedShape = shape.toProjected(0.0f);
        ArrayList<Polygon> triangles = toScreen( projectedShape );

        // Add to screen
        meshGroup.getChildren().addAll(triangles);
        pane.getChildren().add(meshGroup);
        scene.setRoot(pane);

        // Show
        primaryStage.show();

        final Mesh BASE_SHAPE = shape;
        clockTimer.scheduleAtFixedRate(new TimerTask(){

            float theta = 0.0f;
            Vec3d camera = Engine3D.V_CAMERA;
            Vec3d lookDir = new Vec3d(0, 0, 1, 0);
            Vec3d up = new Vec3d(0, 1, 0, 0);
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        theta += 0.01f;
                        
                        // BASE_SHAPE.updateLookAt(camera, camera.plus(lookDir), up);
                        Mesh rotatedShape = BASE_SHAPE.toProjected(theta);
                        updateMeshGroup(rotatedShape);
                    }
                });
            }
        }, 0, 1000/60
        );
    }

    private Scene setupScene(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, Engine3D.RESOLUTION[0], Engine3D.RESOLUTION[1]);
        primaryStage.setScene(scene);
        return scene;
    }

    private Pane setupPane() {
        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        return pane;
    }

    // Mesh to FX polys
    public ArrayList<Polygon> toScreen(Mesh mesh) {
        // Output
        ArrayList<Polygon> polyTris = new ArrayList<Polygon>();

        // For each triangle in mesh add poly to output
        for (Triangle tri : mesh.getTriangles()) {
            Polygon polyTri = new Polygon();
            polyTri.getPoints().addAll(tri.toDoubles());
            polyTri.setFill(tri.color);
            polyTris.add(polyTri);
        }
        return polyTris;
    }

    public void updateMeshGroup(Mesh mesh) {
        meshGroup.getChildren().clear();
        meshGroup.getChildren().addAll( toScreen(mesh) );
    }

}