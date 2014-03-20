/**
 * Copyright (C) 2014 Christopher Herrera <eefretsoul@gmail.com>
 */
package com.kaissersoft.FlappyDrake;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.util.Constants;

public class FollowCamera extends Camera {


    //==================================================================================================================
    // FIELDS
    //==================================================================================================================

    private IEntity mChaseEntity;

    //==================================================================================================================
    // CONSTRUCTORS
    //==================================================================================================================

    public FollowCamera(float pX, float pY, float pWidth, float pHeight) {
        super(pX, pY, pWidth, pHeight);
    }

    //==================================================================================================================
    // METHODS
    //==================================================================================================================

    public void setChaseEntity(final IEntity pChaseEntity) {
        this.mChaseEntity = pChaseEntity;
    }


    //==================================================================================================================
    // OVERRIDEN METHODS
    //==================================================================================================================
    @Override
    public void updateChaseEntity() {
        if (this.mChaseEntity != null) {
            final float[] centerCoordinates = this.mChaseEntity.getSceneCenterCoordinates();
            this.setCenter(centerCoordinates[Constants.VERTEX_INDEX_X] + 150, this.getCenterY());
        }
    }


}
