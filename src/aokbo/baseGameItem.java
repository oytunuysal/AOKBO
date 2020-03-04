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

    String name;
    int requiredFood, requiredWood, requiredGold, requiredStone, requiredAge;
    int creationTime;
    ArrayList<baseGameItem> preRequisites = new ArrayList<>();

    //for listing all requirements
    ArrayList<baseGameItem> allReq = new ArrayList<>();

    public baseGameItem(String name, int requiredFood, int requiredWood, int requiredGold, int requiredStone, int requiredAge, int creationTime) {
        this.name = name;
        this.creationTime = creationTime;
        this.requiredAge = requiredAge;
        this.requiredFood = requiredFood;
        this.requiredWood = requiredGold;
        this.requiredStone = requiredStone;
        this.requiredWood = requiredWood;
    }

    //Lists all requirements
    public void listingAllReq(baseGameItem target) {
        for (int i = 0; i < target.getPrerequisites().size(); i++) {
            //System.out.println(target.getPrerequisiteName(i));
            //listingAllReq((baseGameItem) target.getPrerequisites().get(i));
            if (!allReq.contains(target.getPrerequisites().get(i))) {
                allReq.add((baseGameItem) target.getPrerequisites().get(i));
                System.out.println(target.getPrerequisiteName(i));
                listingAllReq((baseGameItem) target.getPrerequisites().get(i));
            }
        }
    }

    public void totalWoodCost() {
        int temp = 0;
        // allReq.forEach(targetCost -> {temp += cost(targetCost)} );
        for (baseGameItem targetCost : allReq) {
            temp += targetCost.requiredWood;
        }
        System.out.println(temp);
    }

    private int cost(baseGameItem anItem) {
        return anItem.requiredWood;
    }

    public void addPreRequisites(baseGameItem preRequisite) {
        this.preRequisites.add(preRequisite);
    }

    public ArrayList getPrerequisites() {
        return preRequisites;
    }

    public String getPrerequisiteName(int i) {
        return preRequisites.get(i).name;
    }

    public void listPrerequisiteNames() {
        //int i;
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