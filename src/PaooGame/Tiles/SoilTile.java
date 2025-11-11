package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \class public class SoilTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip sol/pamant.
 */
public class SoilTile extends Tile
{
    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public SoilTile(int id)
    {
        super(GetId(id), id);
        IsSolid = true;
    }

    static private BufferedImage GetId(int id){
        switch(id){
            case 53: return Assets.soil;
            case 253: return Assets.soilBottom;
            case 450: return Assets.sandTopLeft;
            case 451: return Assets.sandTopMiddle;
            case 452: return Assets.sandTopRight;
            case 453: return Assets.sandTopLeftUp11;
            case 454: return Assets.sandTopRightUp7;
            case 455: return Assets.hardSand;
            case 456: return Assets.hardSandCutLeft;
            case 457: return Assets.hardSandCutRight;
            case 458: return Assets.crate;
            case 459: return Assets.stoneBlock;//Stiu ca nu sunt soil, dar nu se justifica, crearea unei noi
            //clase pentru acestea
            case 467: return Assets.hardSandTopLeft;
            case 468: return Assets.hardSandTopRight;
            default: return Assets.grass;
        }
    }

}
