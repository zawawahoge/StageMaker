import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Main extends Application{
    int width,height;
    GraphicsContext gc;
    Point2D p0,pf;
    @Override
    public void start(Stage stage){
        width = 800;
        height = 512;
        stage.setWidth(width);
        stage.setHeight(height);
        Group root = new Group();
        Canvas cvs = new Canvas(width,height);
        MyStage ms1 = new MyStage();

        gc = cvs.getGraphicsContext2D();
        cvs.setOnMousePressed(e -> {
            p0 = new Point2D(e.getX(),e.getY());
        });
        cvs.setOnMouseDragged(e -> {
            pf = new Point2D(e.getX(),e.getY());
            gc.clearRect(0,0,width,height);
            ms1.drawAllFigure(gc);
            gc.strokeRect(p0.getX(),p0.getY(),pf.getX()-p0.getX(),pf.getY()-p0.getY());
        });
        cvs.setOnMouseReleased(e -> {
            double w = e.getX() - p0.getX();
            double h = e.getY() - p0.getY();
            gc.clearRect(0,0,width,height);
            if(w>0 && h>0){
                ms1.addFigure(new Figure(p0.getX(),p0.getY(),w,h));
            }
            ms1.drawAllFigure(gc);
            ms1.printAllFigure();
        });
        // ms1.addFigure(new Figure(1,1,100,100));
        // ms1.printAllFigure();
        // ms1.drawAllFigure(gc);
        root.setCursor(Cursor.CROSSHAIR);
        root.getChildren().add(cvs);

        stage.setScene(new Scene(root,0,0));
        stage.show();
    }


}