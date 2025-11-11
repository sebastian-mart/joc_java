package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \class public class SolidUnsolidTile extends Tile
    \brief Tile solid ce va putea fi schimbat la finalul jocului.
 */
public class SolidUnsolidTile extends Tile {

    /*! \fn public MountainTile(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public SolidUnsolidTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(GetId(id), id);
        IsSolid = true;
    }

    static private BufferedImage GetId(int id){
        switch (id){
            case 278: return Assets.cageUpLeft;
            case 279: return Assets.cageUpRight;
            case 308: return Assets.cageDownLeft;
            case 309: return Assets.cageDownRight;
            default: return Assets.grass;
        }
    }

}
