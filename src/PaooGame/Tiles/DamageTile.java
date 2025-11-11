package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \class public class DamageTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class DamageTile extends Tile
{

    /*! \fn public DamageTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public DamageTile(int id)
    {

        /// Apel al constructorului clasei de baza
        super(GetId(id), id);
        IsSolid = false;
    }

    static private BufferedImage GetId(int id){
        switch(id){
            case 222: return Assets.cageSpikes;
            case 444: return Assets.redSpikes;
            case 460: return Assets.bush;
            default: return Assets.grass;
        }
    }

    @Override
    public boolean IsDeath() { return true; }
}
