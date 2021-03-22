/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo.simulation;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Oytun
 */
public class Research extends baseGameItem {

    //a list with both upgrade type and gatherRateFactor. This way a reseach can change more than one attribute.
    private ArrayList<UpgradeAffect> upgradeAffects;

    /*
    0-non eco(do I even need it now ?)
    1-gather rate
    2-walking speed
    3-carry capacity
     */
    public Research(String name, int food, int wood, int gold, int stone, int age, int time) {
        super(name, food, wood, gold, stone, age, time);
        this.upgradeAffects = new ArrayList<>();
    }

    public void addUpgradeAffect(int resourceType, int type, int factor) { //factor is actually float..
        this.upgradeAffects.add(new UpgradeAffect(resourceType, type, factor));
    }

    public void applyResearch(Resource aResource) {

        for (Iterator<UpgradeAffect> iterator = upgradeAffects.iterator(); iterator.hasNext();) {
            UpgradeAffect next = iterator.next();
            switch (next.upgradeType) {
                case 0:
                    //System.out.println("aokbo.Research.applyResearch() its not an Economic Research");
                    break;
                case 1:
                    aResource.changeGatherRate(next.gatherRateFactor);
                    break;
                case 2:
                    aResource.changeWalkingDistanceFactor(next.gatherRateFactor);
                    break;
                case 3:
                    aResource.changeCarryCap(next.gatherRateFactor);
                    break;
            }

        }

    }

    public class UpgradeAffect {
        int resourceType;
        int upgradeType;
        float gatherRateFactor;

        /*
        1-wood
        2-food
        3-gold
        4-stone
         */
        public UpgradeAffect(int resourceType, int type, int factor) {
            this.resourceType = resourceType;
            this.upgradeType = type;
            this.gatherRateFactor = factor;
        }

        public int getResourceType(){
            return resourceType;
        }
    }

    public ArrayList<UpgradeAffect> getUpgradeAffects() {
        return upgradeAffects;
    }

    public void setUpgradeAffects(ArrayList<UpgradeAffect> upgradeAffects) {
        this.upgradeAffects = upgradeAffects;
    }
}
