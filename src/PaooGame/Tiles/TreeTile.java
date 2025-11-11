package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \class public class TreeTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip tree.
 */
public class TreeTile extends Tile
{
    /*! \fn public TreeTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public TreeTile(int id)
    {
        super(GetId(id), id);
        IsSolid = false;
    }

    static private BufferedImage GetId(int id){
        switch(id){
            case 92: return Assets.treeTrunkLeft92;
            case 93: return Assets.treeTrunkMiddle93;
            case 94: return Assets.treeTrunkRight94;
            case 245: return Assets.tree245;
            case 270: return Assets.tree270;
            case 269: return Assets.tree269;
            case 246: return Assets.tree246;
            case 247: return Assets.tree247;
            case 272: return Assets.tree272;
            case 273: return Assets.tree273;
            case 298: return Assets.tree298;
            case 323: return Assets.tree323;
            case 322: return Assets.tree322;
            case 347: return Assets.tree347;
            case 346: return Assets.tree346;
            case 345: return Assets.tree345;
            case 320: return Assets.tree320;
            case 319: return Assets.tree319;
            case 294: return Assets.tree294;
            case 144: return Assets.tree144;
            case 117: return Assets.tree117;
            case 389: return Assets.fenceLeft;
            case 392: return Assets.fenceRight;
            case 390: return Assets.fenceMiddle;
            //
            case 45: return Assets.doorLeftUp;
            case 46: return Assets.doorRightUp;
            case 95: return Assets.doorLeftDown;
            case 96: return Assets.doorRightDown;
            default: return Assets.grass;
        }
    }
}
