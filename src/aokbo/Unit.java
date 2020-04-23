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
public class Unit extends baseGameItem{
    float walkingSpeed; //havent created a vil class yet.
    int carryCapacity; //havent created a vil class yet.
    //public baseGameItem baseUnit;

    public Unit(String name, int food, int wood, int gold, int age, int time, float walkingSpeed, int carryCap) {
        super(name, food, wood, 0, gold, age, time);
        this.walkingSpeed = walkingSpeed;
        this.carryCapacity = carryCap;
    }

}
