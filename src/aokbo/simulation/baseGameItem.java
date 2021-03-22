/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo.simulation;

import java.util.ArrayList;

/**
 *
 * @author Oytun
 */
public class baseGameItem {

    private String name;
    private int requiredFood, requiredWood, requiredGold, requiredStone, requiredAge;
    private int creationTime;
    private ArrayList<baseGameItem> preRequisites = new ArrayList<>();

    public baseGameItem(String name, int requiredFood, int requiredWood, int requiredGold, int requiredStone,
            int requiredAge, int creationTime) {
        this.name = name;
        this.creationTime = creationTime;
        this.requiredAge = requiredAge;
        this.requiredFood = requiredFood;
        this.requiredGold = requiredGold;
        this.requiredStone = requiredStone;
        this.requiredWood = requiredWood;
    }

    public void addPreRequisites(baseGameItem preRequisite) {
        this.preRequisites.add(preRequisite);
    }

    public ArrayList<baseGameItem> getPrerequisites() {
        return preRequisites;
    }

    public String getPrerequisiteName(int i) {
        return preRequisites.get(i).name;
    }

    public void listPrerequisiteNames() {
        for (int i = 0; i < preRequisites.size(); i++) {
            System.out.println('-' + preRequisites.get(i).name);
        }
    }

    public String getName() {
        return name;
    }

    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }

    public void setRequiredFood(int requiredFood) {
        this.requiredFood = requiredFood;
    }

    public void setRequiredGold(int requiredGold) {
        this.requiredGold = requiredGold;
    }

    public void setRequiredStone(int requiredStone) {
        this.requiredStone = requiredStone;
    }

    public void setRequiredWood(int requiredWood) {
        this.requiredWood = requiredWood;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public int getRequiredAge() {
        return requiredAge;
    }

    public int getRequiredFood() {
        return requiredFood;
    }

    public int getRequiredGold() {
        return requiredGold;
    }

    public int getRequiredStone() {
        return requiredStone;
    }

    public int getRequiredWood() {
        return requiredWood;
    }

}
