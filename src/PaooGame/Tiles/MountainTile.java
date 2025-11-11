package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \class public class MountainTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip munte sau piatra.
 */
public class MountainTile extends Tile {

    /*! \fn public MountainTile(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public MountainTile(int id)
    {
            /// Apel al constructorului clasei de baza
        super(GetId(id), id);
        IsSolid = true;
    }

    static private BufferedImage GetId(int id){
        switch (id){
            case 182: return Assets.rockGrass;
            case 197: return Assets.rock;
            case 248: return Assets.rock248;
            case 278: return Assets.cageUpLeft;
            case 279: return Assets.cageUpRight;
            case 308: return Assets.cageDownLeft;
            case 309: return Assets.cageDownRight;
            default: return Assets.grass;
        }
    }

}
