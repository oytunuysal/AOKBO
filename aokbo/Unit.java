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
public class Unit {

    public baseGameItem baseUnit;

    public Unit(int food, int wood, int gold, int age, int time) {
        baseUnit = new baseGameItem(food, wood, 0, gold, age, time);
    }

}
