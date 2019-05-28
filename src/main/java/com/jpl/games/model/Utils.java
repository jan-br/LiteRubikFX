package com.jpl.games.model;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import static javafx.scene.paint.Color.*;

/** @author jpereda, April 2014 - @JPeredaDnr */
public class Utils {

  public static Affine getAffine(double dimCube, double d0, boolean bFaceArrow, String face) {
    Affine aff;
    double d = 2d * dimCube / 3d;
    if (!bFaceArrow) {
      aff = new Affine(new Scale(80, 80, 50));
      aff.append(new Translate(-d0, -d0, d0));
    } else {
      aff = new Affine(new Scale(3, 3, 3));
      aff.append(new Translate(0, -d0, 0));
    }
    switch (face) {
      case "F":
      case "Fi":
        aff.prepend(new Rotate(face.equals("F") ? 90 : -90, Rotate.X_AXIS));
        aff.prepend(new Rotate(face.equals("F") ? 45 : -45, Rotate.Z_AXIS));
        aff.prepend(new Translate(0, 0, dimCube / 2d));
        break;
      case "B":
      case "Bi":
        aff.prepend(new Rotate(face.equals("Bi") ? 90 : -90, Rotate.X_AXIS));
        aff.prepend(new Rotate(face.equals("Bi") ? 45 : -45, Rotate.Z_AXIS));
        aff.prepend(new Translate(0, 0, -dimCube / 2d));
        break;
      case "R":
      case "Ri":
        aff.prepend(new Rotate(face.equals("Ri") ? 90 : -90, Rotate.Z_AXIS));
        aff.prepend(new Rotate(face.equals("Ri") ? 45 : -45, Rotate.X_AXIS));
        aff.prepend(new Translate(dimCube / 2d, 0, 0));
        break;
      case "L":
      case "Li":
        aff.prepend(new Rotate(face.equals("L") ? 90 : -90, Rotate.Z_AXIS));
        aff.prepend(new Rotate(face.equals("L") ? 45 : -45, Rotate.X_AXIS));
        aff.prepend(new Translate(-dimCube / 2d, 0, 0));
        break;
      case "U":
      case "Ui":
        aff.prepend(new Rotate(face.equals("Ui") ? 180 : 0, Rotate.Z_AXIS));
        aff.prepend(new Rotate(face.equals("Ui") ? 45 : -45, Rotate.Y_AXIS));
        aff.prepend(new Translate(0, dimCube / 2d, 0));
        break;
      case "D":
      case "Di":
        aff.prepend(new Rotate(face.equals("D") ? 180 : 0, Rotate.Z_AXIS));
        aff.prepend(new Rotate(face.equals("D") ? 45 : -45, Rotate.Y_AXIS));
        aff.prepend(new Translate(0, -dimCube / 2d, 0));
        break;
      case "Z":
      case "Zi":
        aff.prepend(new Rotate(face.equals("Zi") ? 180 : 0, Rotate.Y_AXIS));
        aff.prepend(new Rotate(face.equals("Zi") ? 45 : -45, Rotate.Z_AXIS));
        aff.prepend(new Translate(0, 0, d));
        break;
      case "X":
      case "Xi":
        aff.prepend(new Rotate(face.equals("X") ? 90 : -90, Rotate.Y_AXIS));
        aff.prepend(new Rotate(face.equals("Xi") ? 45 : -45, Rotate.X_AXIS));
        aff.prepend(new Translate(d, 0, 0));
        break;
      case "Y":
      case "Yi":
        aff.prepend(new Rotate(face.equals("Yi") ? 90 : -90, Rotate.X_AXIS));
        aff.prepend(new Rotate(face.equals("Yi") ? 45 : -45, Rotate.Y_AXIS));
        aff.prepend(new Translate(0, d, 0));
        break;
    }
    return aff;
  }

  public static PhongMaterial getMaterial(String face) {
    PhongMaterial arrowMat = new PhongMaterial();
    arrowMat.setSpecularColor(WHITESMOKE);
    Color color = WHITE;
    switch (face) {
      case "F":
      case "Fi":
      case "Z":
      case "Zi":
      case "B":
      case "Bi":
        color = BLUE.brighter();
        break;
      case "R":
      case "Ri":
      case "X":
      case "Xi":
      case "L":
      case "Li":
        color = RED.brighter();
        break;
      case "U":
      case "Ui":
      case "Y":
      case "Yi":
      case "D":
      case "Di":
        color = FORESTGREEN.brighter();
        break;
    }
    arrowMat.setDiffuseColor(color);
    return arrowMat;
  }

  public static Point3D getAxis(String face) {
    Point3D p = new Point3D(0, 0, 0);
    switch (face.substring(0, 1)) {
      case "L":
      case "M":
        p = new Point3D(-1, 0, 0);
        break;
      case "R":
      case "X":
        p = new Point3D(1, 0, 0);
        break;
      case "U":
      case "Y":
        p = new Point3D(0, 1, 0);
        break;
      case "E":
      case "D":
        p = new Point3D(0, -1, 0);
        break;
      case "F":
      case "S":
      case "Z":
        p = new Point3D(0, 0, 1);
        break;
      case "B":
        p = new Point3D(0, 0, -1);
        break;
    }
    return p;
  }

  public static int getCenter(String face) {
    int c = 0;
    switch (face.substring(0, 1)) {
      case "L":
        c = 12;
        break;
      case "M":
      case "E":
      case "S":
        c = 13;
        break;
      case "R":
        c = 14;
        break;
      case "U":
        c = 10;
        break;
      case "D":
        c = 16;
        break;
      case "F":
        c = 4;
        break;
      case "B":
        c = 22;
        break;
    }
    return c;
  }
}
