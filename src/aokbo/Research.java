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
public class Research extends baseGameItem {

    //public baseGameItem baseUnit;
    // Research preResearch;
    // int availableFlag = 1;
    float gatherRateFactor;
    int upgradeType; //0:non-eco 1:gatherRate 2:walkingSpeed/efficiency

    public Research(String name, int food, int wood, int gold, int stone, int age, int time, int upgradeType, float gatherRateFactor) {
        super(name, food, wood, gold, stone, age, time);
        this.gatherRateFactor = gatherRateFactor;
    }
    
    public void applyResearch(Resource aResource) {
        if (upgradeType == 0 || gatherRateFactor == 0) {
            System.out.println("aokbo.Research.applyResearch() its not an Economic Research");
            
        } else {
            applyEcoResearch(aResource);
        }
    }
    
    public void applyEcoResearch(Resource aResource) {
        if (upgradeType == 1) {
            aResource.changeGatherRate(gatherRateFactor);
        } else {
            aResource.changeWalkingDistanceFactor(gatherRateFactor);
        }

        //maybe print a success messeage/error m.
    }

    // public void addPreResearch(Research preResearch) {
    //    this.preResearch = preResearch;
    //     availableFlag = 0;
    // }
}
