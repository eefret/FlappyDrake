/**
 * Copyright (C) 2014 Christopher Herrera <eefretsoul@gmail.com>
 */
package com.kaissersoft.FlappyDrake;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.andengine.extension.physics.box2d.PhysicsFactory;

public class Constants {

    //==================================================================================================================
    // CONSTANTS
    //==================================================================================================================

    public static final int CW = 480;
    public static final int CH = 800;
    public static final int FPS_LIMIT = 60;


    public final static String BODY_WALL = "wall";
    public final static String BODY_ACTOR = "actor";
    public final static String BODY_SENSOR = "sensor";

    public static final FixtureDef DRAKE_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, false);
    public static final FixtureDef WALL_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, false);
    public static final FixtureDef CEILLING_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 0f, false);
    public static final FixtureDef SENSOR_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, true);

    public static final float SPEED_X = 8;
    public static final float GRAVITY = -40;
    public static final float SPEED_Y = 15;

    public static final String KEY_HISCORE = "high score";
}
