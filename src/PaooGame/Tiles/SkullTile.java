package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \class public class SkullTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class SkullTile extends Tile
{
    /*! \fn public PlatformTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public SkullTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(GetId(id), id);
        IsSolid = true;
    }

    static private BufferedImage GetId(int id){
        switch(id){
            case 51: return Assets.skullLeft;
            case 55: return Assets.skullRight;
            case 80: return Assets.skullRightDown;
            default: return Assets.grass;
        }
    }

}
