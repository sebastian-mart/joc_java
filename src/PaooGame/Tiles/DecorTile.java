package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

public class DecorTile extends Tile{
    public DecorTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(GetId(id), id);
        IsSolid = false;
    }

    static private BufferedImage GetId(int id){
        switch(id){
            case 461: return Assets.cactus1;
            case 462: return Assets.cactus2;
            case 463: return Assets.cactus3;
            case 464: return Assets.grass1;
            case 465: return Assets.skeleton;
            case 466: return Assets.stone;
            default: return Assets.grass;
        }
    }
}
