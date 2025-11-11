package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \class public class GrassTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class GrassTile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public GrassTile(int id)
    {
            /// Apel al constructorului clasei de baza
        super(GetId(id), id);
        IsSolid = true;
    }

    static private BufferedImage GetId(int id){
        switch(id){
            case 26: return Assets.grassLeft;
            case 30: return Assets.grassRight;
            case 27: return Assets.grassBottomLeft;
            case 29:return Assets.grassBottomRight;
            default: return Assets.grass;
        }
    }

}
