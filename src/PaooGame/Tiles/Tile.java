package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    public  boolean IsSolid;
    private static final int NO_TILES   = 512;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/


        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie
    public static Tile grassTile        = new GrassTile(3);     /*!< Dala de tip iarba*/
    public static Tile grassLeftTile= new GrassTile(26);
    public static Tile grassRightTile= new GrassTile(30);
    public static Tile grassBottomLeft= new GrassTile(27);
    public static Tile grassBottomRight= new GrassTile(29);

//    public static Tile mountainTile     = new MountainTile(1);  /*!< Dala de tip munte/piatra*/
//    public static Tile waterTile        = new WaterTile(2);     /*!< Dala de tip apa*/
//    public static Tile treeTile         = new TreeTile(3);      /*!< Dala de tip copac*/


    public static Tile soilTile         = new SoilTile(53);      /*!< Dala de tip sol/pamant/nisip*/
    public static Tile soilBottomTile= new SoilTile(253);
    public static Tile sandTopLeftTile = new SoilTile(450);
    public static Tile sandTopMiddleTile = new SoilTile(451);
    public static Tile sandTopRightTile = new SoilTile(452);
    public static Tile sandTopLeftUp11Tile = new SoilTile(453);
    public static Tile sandTopRightUp7Tile = new SoilTile(454);
    public static Tile hardSandTile = new SoilTile(455);
    public static Tile hardSandCutLeftTile = new SoilTile(456);
    public static Tile hardSandCutRightTile = new SoilTile(457);
    public static Tile crateTile = new SoilTile(458);
    public static Tile stoneBlockTile = new SoilTile(459);
    public static Tile hardSandTopLeftTile = new SoilTile(467);
    public static Tile hardSandTopRightTile = new SoilTile(468);

    public static Tile skullRightTile= new SkullTile(55);
    public static Tile skullRightDownTile= new SkullTile(80);
    public static Tile skullLeftTile= new SkullTile(51);

    public static Tile thinStickTile= new PlatformTile(418);
    public static Tile platformMiddleTile= new PlatformTile(416);
    public static Tile platformRightTile= new PlatformTile(417);
    public static Tile platformLeftTile= new PlatformTile(415);
    public static Tile platformTile= new PlatformTile(369);
    public static Tile treeSquareTile= new PlatformTile(86);
    public static Tile steelPlatformTile = new PlatformTile(77);



    public static Tile redSpikesTile=new DamageTile(444);
    public static Tile bushTile = new DamageTile(460);
    public static Tile cageSpikesTile = new DamageTile(222);

    public static Tile fenceLeftTile=new TreeTile(389);
    public static Tile fenceRightTile=new TreeTile(392);
    public static Tile fenceMiddleTile=new TreeTile(390);
    public static Tile treeTrunkLeft92Tile=new TreeTile(92);
    public static Tile treeTrunkRight94Tile=new TreeTile(94);
    public static Tile treeTrunkMiddle93Tile=new TreeTile(93);
    public static Tile tree270Tile=new TreeTile(270);
    public static Tile tree269Tile=new TreeTile(269);
    public static Tile tree245Tile=new TreeTile(245);
    public static Tile tree246Tile=new TreeTile(246);
    public static Tile tree247Tile=new TreeTile(247);
    public static Tile tree272Tile=new TreeTile(272);
    public static Tile tree273Tile=new TreeTile(273);
    public static Tile tree298Tile=new TreeTile(298);
    public static Tile tree323Tile=new TreeTile(323);
    public static Tile tree322Tile=new TreeTile(322);
    public static Tile tree347Tile=new TreeTile(347);
    public static Tile tree346Tile=new TreeTile(346);
    public static Tile tree345Tile=new TreeTile(345);
    public static Tile tree320Tile=new TreeTile(320);
    public static Tile tree319Tile=new TreeTile(319);
    public static Tile tree294Tile=new TreeTile(294);
    public static Tile tree144Tile=new TreeTile(144);
    public static Tile tree117Tile=new TreeTile(117);
    public static Tile doorLeftUp=new TreeTile(45);
    public static Tile doorRightUpTile=new TreeTile(46);
    public static Tile doorLeftDownTile=new TreeTile(95);
    public static Tile doorRightDownTile=new TreeTile(96);

    public static Tile cactus1Tile= new DecorTile(461);
    public static Tile cactus2Tile= new DecorTile(462);
    public static Tile cactus3Tile= new DecorTile(463);
    public static Tile grass1Tile= new DecorTile(464);
    public static Tile skeletonTile= new DecorTile(465);
    public static Tile stoneTile= new DecorTile(466);

    public static Tile waterTile = new WaterTile(484);

    public static Tile rockGrassTile = new MountainTile(182);
    public static Tile rockTile = new MountainTile(197);
    public static Tile rock248Tile = new MountainTile(248);
    public static Tile cageUpLeftTile = new SolidUnsolidTile(278);
    public static Tile cageUpRightTile = new SolidUnsolidTile(279);
    public static Tile cageDownLeftTile = new SolidUnsolidTile(308);
    public static Tile cageDownRightTile = new SolidUnsolidTile(309);


    public static final int TILE_WIDTH  = 64;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 64;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        IsSolid = false;
        img = image;
        id = idd;

        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */

    public boolean IsDeath() { return false; }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }

    public static void TurnToNotSolid(){
        cageUpLeftTile.IsSolid = false;
        cageUpRightTile.IsSolid = false;
        cageDownLeftTile.IsSolid = false;
        cageDownRightTile.IsSolid = false;
    }
}
