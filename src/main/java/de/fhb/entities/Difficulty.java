package de.fhb.entities;

public enum Difficulty {

  EASY, MEDIUM, HARD;

  @Override
  public String toString() {
    switch (this) {
      case EASY:
        return "Anfänger";
      case MEDIUM:
        return "Fortgeschrittene";
      case HARD:
        return "Profis";
      default:
        return "-";
    }
  }
}
