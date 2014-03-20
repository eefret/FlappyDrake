/**
 * Copyright (C) 2014 Christopher Herrera <eefretsoul@gmail.com>
 */
package com.kaissersoft.FlappyDrake;

import android.util.Log;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.andengine.util.adt.pool.GenericPool;

import java.util.Random;

public class PillarFactory {

    //==================================================================================================================
    // CONSTANTS
    //==================================================================================================================
    private static final PillarFactory INSTANCE = new PillarFactory();

    //==================================================================================================================
    // CONSTRUCTORS
    //==================================================================================================================
    private PillarFactory() {
    }

    //==================================================================================================================
    // FIELDS
    //==================================================================================================================

    GenericPool<Pillar> pool;

    int nextX;
    int nextY;
    int dy;

    final int dx = 300;

    final int maxY = 550;
    final int minY = 350;


    //==================================================================================================================
    // METHODS
    //==================================================================================================================

    public static final PillarFactory getInstance() {
        return INSTANCE;
    }

    public void create(final PhysicsWorld physics) {
        reset();
        pool = new GenericPool<Pillar>(3) {

            @Override
            protected Pillar onAllocatePoolItem() {
                Pillar p = new Pillar(0, 0,
                        ResourceManager.getInstance().pillarRegion,
                        ResourceManager.getInstance().vbom,
                        physics);
                return p;
            }
        };

    }

    public Pillar next() {
        Pillar p = pool.obtainPoolItem();
        p.setPosition(nextX, nextY);

        p.getScoreSensor().setTransform(nextX / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
                nextY / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);

        p.getPillarUpBody().setTransform(nextX / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
                (nextY + p.getPillarShift()) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);

        p.getPillarDownBody().setTransform(nextX / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
                (nextY - p.getPillarShift()) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);

        p.getScoreSensor().setActive(true);
        p.getPillarUpBody().setActive(true);
        p.getPillarDownBody().setActive(true);

        Random rand = new Random();

        nextX += dx;
        nextY += dy * rand.nextFloat() * 3;

        Log.d("PillarHeight", "nextY: " + nextY);
        Log.d("PillarHeight", "dy: " + dy);

        if (nextY < (maxY - 50) || nextY > (minY - 50)) {
            dy = -dy;
        }


        return p;
    }

    public void recycle(Pillar p) {
        p.detachSelf();
        p.getScoreSensor().setActive(false);
        p.getPillarUpBody().setActive(false);
        p.getPillarDownBody().setActive(false);
        p.getScoreSensor().setTransform(-1000, -1000, 0);
        p.getPillarUpBody().setTransform(-1000, -1000, 0);
        p.getPillarDownBody().setTransform(-1000, -1000, 0);
        pool.recyclePoolItem(p);
    }

    public void reset() {
        nextX = 650;
        nextY = 350;
        dy = 50;
    }

}
