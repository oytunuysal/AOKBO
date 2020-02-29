/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

import java.util.ArrayList;

/**
 *
 * @author Oytun
 */
public class baseGameItem {

    int requiredFood, requiredWood, requiredGold, requiredStone, requiredAge;
    int creationTime;
    ArrayList<baseGameItem> preResearchs = new ArrayList<>();

    public baseGameItem(int requiredFood, int requiredWood, int requiredGold, int requiredStone, int requiredAge, int creationTime) {
        this.creationTime = creationTime;
        this.requiredAge = requiredAge;
        this.requiredFood = requiredFood;
        this.requiredWood = requiredGold;
        this.requiredStone = requiredStone;
        this.requiredWood = requiredWood;
    }

    public void addPreResearchs(baseGameItem preResearch) {
        this.preResearchs.add(preResearch);
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
