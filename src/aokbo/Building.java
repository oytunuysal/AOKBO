/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

/**
 *
 * @author Oytun
 */
public class Building {

    private baseGameItem baseUnit;
    int flag;

    public Building(int food, int wood, int gold, int stone, int age, int time, int reqBarrackFlag) {
        baseUnit = new baseGameItem(food, wood, gold, stone, age, time);
        this.flag = reqBarrackFlag;
    }

}
