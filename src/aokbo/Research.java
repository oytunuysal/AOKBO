/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Oytun
 */
public class Research extends baseGameItem {

    //public baseGameItem baseUnit;
    // Research preResearch;
    // int availableFlag = 1;
    //float gatherRateFactor;
    //int upgradeType; //0:non-eco 1:gatherRate 2:walkingSpeed/efficiency
    // what about wheelbarrow 
    //a list with both upgrade type and gatherRateFactor. This way a reseach can change more than one attribute.
    ArrayList<UpgradeAffect> upgradeAffects;

    /*
    0-non eco(do I even need it now ?)
    1-gather rate
    2-walking speed
    3-carry capacity
     */
    
    public Research(String name, int food, int wood, int gold, int stone, int age, int time) {
        super(name, food, wood, gold, stone, age, time);
        this.upgradeAffects = new ArrayList<>();
        //this.gatherRateFactor = gatherRateFactor;
        //this.upgradeType = upgradeType;
    }
    
    public void addUpgradeAffect(int type, int factor) { //factor is actually float..
        upgradeAffects.add(new UpgradeAffect(type, factor));
    }
    
    public void applyResearch(Resource aResource) {
        
        for (Iterator<UpgradeAffect> iterator = upgradeAffects.iterator(); iterator.hasNext();) {
            UpgradeAffect next = iterator.next();
            switch (next.upgradeType) {
                case 0:
                    System.out.println("aokbo.Research.applyResearch() its not an Economic Research");
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

    //private void applyEcoResearch(Resource aResource) {
    //  if (upgradeType == 1) {
    //     aResource.changeGatherRate(gatherRateFactor);
    //} else {
    //   aResource.changeWalkingDistanceFactor(gatherRateFactor);
    //}
    //maybe print a success messeage/error m.
    //}
    // public void addPreResearch(Research preResearch) {
    //    this.preResearch = preResearch;
    //     availableFlag = 0;
    // }
    //or private ?
    public class UpgradeAffect {
        
        int upgradeType;
        float gatherRateFactor;
        
        public UpgradeAffect(int type, int factor) {
            this.upgradeType = type;
            this.gatherRateFactor = factor;
        }
    }
}
