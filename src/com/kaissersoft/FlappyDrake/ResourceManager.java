/**
 * Copyright (C) 2014 Christopher Herrera <eefretsoul@gmail.com>
 */
package com.kaissersoft.FlappyDrake;


import android.graphics.Typeface;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

public class ResourceManager {
    //==================================================================================================================
    // CONSTANTS
    //==================================================================================================================
    private static final ResourceManager INSTANCE = new ResourceManager();

    //==================================================================================================================
    // FIELDS
    //==================================================================================================================

    //font
    public Font font;

    //common objects
    public GameActivity activity;
    public Engine engine;
    public Camera camera;
    public VertexBufferObjectManager vbom;

    //gfx
    private BitmapTextureAtlas repeatingGroundAtlas;

    public TextureRegion repeatingGroundRegion;

    private BuildableBitmapTextureAtlas gameObjectsAtlas;

    public TextureRegion cloudRegion;

    public TextureRegion pillarRegion;
    public TiledTextureRegion drakeRegion;

    public TextureRegion bannerRegion;

    //sfx
    public Sound sndFly;
    public Sound sndFail;
    public Sound sndScore;

    //==================================================================================================================
    // CONSTRUCTORS
    //==================================================================================================================
    private ResourceManager() {
    }

    //==================================================================================================================
    // METHODS
    //==================================================================================================================
    public static ResourceManager getInstance() {
        return INSTANCE;
    }

    public void create(GameActivity activity, Engine engine, Camera camera, VertexBufferObjectManager vbom) {
        this.activity = activity;
        this.engine = engine;
        this.camera = camera;
        this.vbom = vbom;
    }

    public void loadFont() {
        font = FontFactory.createStroke(activity.getFontManager(), activity.getTextureManager(), 256, 256, Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), 50,
                true, Color.WHITE_ABGR_PACKED_INT, 2, Color.BLACK_ABGR_PACKED_INT);
        font.load();
    }

    public void unloadFont() {
        font.unload();
    }

    //splash
    public void loadGameResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        repeatingGroundAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.REPEATING_BILINEAR_PREMULTIPLYALPHA);
        repeatingGroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(repeatingGroundAtlas, activity, "ground.png", 0, 0);
        repeatingGroundAtlas.load();

        gameObjectsAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(),
                1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);

        cloudRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                gameObjectsAtlas, activity.getAssets(), "cloud.png");

        pillarRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                gameObjectsAtlas, activity.getAssets(), "pillar.png");

        bannerRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                gameObjectsAtlas, activity.getAssets(), "banner.png");

        drakeRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                gameObjectsAtlas, activity.getAssets(), "drake.png", 3, 1);

        try {
            gameObjectsAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(2, 0, 2));
            gameObjectsAtlas.load();

        } catch (final TextureAtlasBuilderException e) {
            throw new RuntimeException("Error while loading Splash textures", e);
        }

        try {
            sndFly = SoundFactory.createSoundFromAsset(activity.getEngine().getSoundManager(), activity, "sfx/fly.wav");
            sndFail = SoundFactory.createSoundFromAsset(activity.getEngine().getSoundManager(), activity, "sfx/fail.wav");
            sndScore = SoundFactory.createSoundFromAsset(activity.getEngine().getSoundManager(), activity, "sfx/coin.wav");
        } catch (Exception e) {
            throw new RuntimeException("Error while loading sounds", e);
        }
    }
}
