package com.walker;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    static GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public static void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 23 * Constants.TILE_SIZE;
        gp.obj[0].worldY = 7 * Constants.TILE_SIZE;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * Constants.TILE_SIZE;
        gp.obj[1].worldY = 40 * Constants.TILE_SIZE;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 38 * Constants.TILE_SIZE;
        gp.obj[2].worldY = 8 * Constants.TILE_SIZE;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX =  10 * Constants.TILE_SIZE;
        gp.obj[3].worldY =  11 * Constants.TILE_SIZE;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX =  8 * Constants.TILE_SIZE;
        gp.obj[4].worldY = 28 * Constants.TILE_SIZE;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 12 * Constants.TILE_SIZE;
        gp.obj[5].worldY = 22 * Constants.TILE_SIZE;

        gp.obj[5] = new OBJ_Chest();
        gp.obj[5].worldX =  10 * Constants.TILE_SIZE;
        gp.obj[5].worldY =  7 * Constants.TILE_SIZE;

    }
}
