package Interfaces;

import Backpack.Backpack;

public interface Fighter {
    String getName();
    boolean isChaked();
    void showChar();
    boolean isAlive();
    void HPMinus(double hit);
    double getHp(int i);
    Backpack getBackpack();
    void nullShaking(String typeOpponent);
    String getType();
    void activeSkill(Fighter opponent);
    void steelHp(double quantity);
}
