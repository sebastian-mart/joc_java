package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \class public class GrassTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class PlatformTile extends Tile
{
    /*! \fn public PlatformTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public PlatformTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(GetId(id), id);
        IsSolid = true;
    }

    static private BufferedImage GetId(int id){
        switch(id){
            case 77: return Assets.steelPlatform;
            case 86: return Assets.treeSquare;
            case 415: return Assets.platformLeft;
            case 416: return Assets.platformMiddle;
            case 417: return Assets.platformRight;
            case 418: return Assets.thinStick;
            case 369: return Assets.platform;
            default: return Assets.grass;
        }
    }
    }
