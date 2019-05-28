package com.jpl.games;

import com.jpl.games.model.Rubik;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** @author jpereda, April 2014 - @JPeredaDnr */
public class LiteRubikFX extends Application {

  private final BorderPane pane = new BorderPane();
  private Rubik rubik;

  @Override
  public void start(Stage stage) {
    /*
    Import Rubik's Cube model and arrows
    */
    rubik = new Rubik(pane);
    // create toolbars
    ToolBar tbTop =
        new ToolBar(new Button("U"), new Button("Ui"), new Button("F"), new Button("Fi"));
    pane.setTop(tbTop);
    ToolBar tbBottom =
        new ToolBar(
            new Button("B"),
            new Button("Bi"),
            new Button("D"),
            new Button("Di"),
            new Separator(),
            new Button("Shuffle"),
            new Button("Solve"));

    pane.setBottom(tbBottom);
    ToolBar tbRight = new ToolBar(new Button("R"), new Button("Ri"), new Separator());
    tbRight.setOrientation(Orientation.VERTICAL);
    pane.setRight(tbRight);
    ToolBar tbLeft = new ToolBar(new Button("L"), new Button("Li"));
    tbLeft.setOrientation(Orientation.VERTICAL);
    pane.setLeft(tbLeft);

    pane.setCenter(rubik.getSubScene());

    pane.getChildren().stream()
        .filter(n -> (n instanceof ToolBar))
        .forEach(
            tb ->
                ((ToolBar) tb)
                    .getItems().stream()
                        .filter(n -> (n instanceof Button))
                        .forEach(
                            n -> {
                              if (((Button) n).getText().equals("Shuffle")) {
                                ((Button) n).setOnAction(e -> rubik.shuffle());
                              } else if (((Button) n).getText().equals("Solve")) {
                                ((Button) n).setOnAction(e -> rubik.solve());
                              } else {
                                ((Button) n)
                                    .setOnAction(e -> rubik.rotateFace(((Button) n).getText()));
                              }
                            }));
    rubik
        .isOnRotation()
        .addListener(
            (ov, b, b1) -> {
              pane.getChildren().stream()
                  .filter(n -> (n instanceof ToolBar))
                  .forEach(tb -> tb.setDisable(b1));
            });
    final Scene scene = new Scene(pane, 880, 680, true);
    scene.setFill(Color.ALICEBLUE);
    stage.setTitle("Rubik's Cube - JavaFX3D");
    stage.setScene(scene);
    stage.show();
  }

  /** @param args the command line arguments */
  public static void main(String[] args) {
    launch(args);
  }
}
