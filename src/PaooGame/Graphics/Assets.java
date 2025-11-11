package PaooGame.Graphics;

import PaooGame.Levels.Level1;
import PaooGame.NPC.Oblivion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    private static boolean levelsPreloaded = false;


    public static int[][] level1;
    public static int[][] level2;
    public static int[][] level3;

    public static BufferedImage matei;

    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage grassBottomLeft;
    public static BufferedImage grassBottomRight;
    public static BufferedImage soilBottom;
    public static BufferedImage grassLeft;
    public static BufferedImage grassRight;
    public static BufferedImage skullRight;
    public static BufferedImage skullRightDown;
    public static BufferedImage skullLeft;
    public static BufferedImage thinStick;
    public static BufferedImage platformMiddle;
    public static BufferedImage platformRight;
    public static BufferedImage platformLeft;
    public static BufferedImage treeSquare;
    public static BufferedImage fenceLeft;
    public static BufferedImage fenceRight;
    public static BufferedImage fenceMiddle;
    public static BufferedImage redSpikes;
    public static BufferedImage treeTrunkLeft92;
    public static BufferedImage treeTrunkRight94;
    public static BufferedImage treeTrunkMiddle93;
    public static BufferedImage tree270;
    public static BufferedImage tree269;
    public static BufferedImage tree245;
    public static BufferedImage tree246;
    public static BufferedImage tree247;
    public static BufferedImage tree272;
    public static BufferedImage tree273;
    public static BufferedImage tree298;
    public static BufferedImage tree323;
    public static BufferedImage tree322;
    public static BufferedImage tree347;
    public static BufferedImage tree346;
    public static BufferedImage tree345;
    public static BufferedImage tree320;
    public static BufferedImage tree319;
    public static BufferedImage tree294;
    public static BufferedImage tree144;
    public static BufferedImage tree117;
    public static BufferedImage platform;
    public static BufferedImage water484;
    public static BufferedImage doorLeftDown;
    public static BufferedImage doorLeftUp;
    public static BufferedImage doorRightDown;
    public static BufferedImage doorRightUp;

    public static BufferedImage sandTopLeft;
    public static BufferedImage sandTopMiddle;
    public static BufferedImage sandTopRight;
    public static BufferedImage sandTopLeftUp11;
    public static BufferedImage sandTopRightUp7;
    public static BufferedImage hardSandCutLeft;
    public static BufferedImage hardSandCutRight;
    public static BufferedImage hardSand;
    public static BufferedImage hardSandTopLeft;
    public static BufferedImage hardSandTopRight;
    public static BufferedImage crate;
    public static BufferedImage stoneBlock;

    public static BufferedImage bush;
    public static BufferedImage cactus1;
    public static BufferedImage cactus2;
    public static BufferedImage cactus3;
    public static BufferedImage grass1;
    public static BufferedImage skeleton;
    public static BufferedImage stone;

    public static BufferedImage steelPlatform;
    public static BufferedImage rock;
    public static BufferedImage rockGrass;
    public static BufferedImage rock248;
    public static BufferedImage cageUpRight;
    public static BufferedImage cageUpLeft;
    public static BufferedImage cageDownRight;
    public static BufferedImage cageDownLeft;
    public static BufferedImage cageSpikes;






    public static BufferedImage background1;
    public static BufferedImage background2;
    public static BufferedImage backgroundLevel2;
    public static BufferedImage backgroundLevel3;


    /// Tile-uri pentru miscare personaj principal

    public static BufferedImage base30;
    public static BufferedImage base31;
    public static BufferedImage walkRight120;
    public static BufferedImage walkRight121;
    public static BufferedImage walkRight122;
    public static BufferedImage walkRight123;
    public static BufferedImage jumpFall180;
    public static BufferedImage jumpFallLeft;

    public static BufferedImage baseleft30;
    public static BufferedImage baseleft31;
    public static BufferedImage walkLeft149;
    public static BufferedImage walkLeft148;
    public static BufferedImage walkLeft147;
    public static BufferedImage walkLeft146;

    public static BufferedImage attackStickRight1;
    public static BufferedImage attackStickRight2;
    public static BufferedImage attackStickRight3;

    public static BufferedImage attackStickLeft1;
    public static BufferedImage attackStickLeft2;
    public static BufferedImage attackStickLeft3;

    public static BufferedImage attackSilverSwordLeft1;
    public static BufferedImage attackSilverSwordLeft2;
    public static BufferedImage attackSilverSwordLeft3;
    public static BufferedImage attackSilverSwordRight1;
    public static BufferedImage attackSilverSwordRight2;
    public static BufferedImage attackSilverSwordRight3;

    public static BufferedImage attackGoldSwordLeft1;
    public static BufferedImage attackGoldSwordLeft2;
    public static BufferedImage attackGoldSwordLeft3;

    public static BufferedImage attackGoldSwordRight1;
    public static BufferedImage attackGoldSwordRight2;
    public static BufferedImage attackGoldSwordRight3;

    public static BufferedImage attackAxeRight1;
    public static BufferedImage attackAxeRight2;
    public static BufferedImage attackAxeRight3;

    public static BufferedImage attackAxeLeft1;
    public static BufferedImage attackAxeLeft2;
    public static BufferedImage attackAxeLeft3;



    ///Tile pentru obiecte ce pot fi colectate
    public static BufferedImage heart;
    public static BufferedImage goldSword;



    /// Tile-uri de miscare NPC-uri
    /// 1. Forest Goblin
    public static BufferedImage GoblinWalkLeft;
    public static BufferedImage GoblinWalkRight;
    public static BufferedImage GoblinAttack1Left;
    public static BufferedImage GoblinAttack2Left;
    public static BufferedImage GoblinAttack1Right;
    public static BufferedImage GoblinAttack2Right;


    /// 2. Asterion
    public static BufferedImage AsterionWalkLeft;
    public static BufferedImage AsterionWalkRight;
    public static BufferedImage AsterionAttack1Left;
    public static BufferedImage AsterionAttack2Left;
    public static BufferedImage AsterionAttack1Right;
    public static BufferedImage AsterionAttack2Right;


    /// 3. Sacal
    public static BufferedImage SacalWalkLeft;
    public static BufferedImage SacalRunningLeft;
    public static BufferedImage SacalAttackLeft;
    public static BufferedImage SacalAttackRight;
    public static BufferedImage SacalWalkRight;
    public static BufferedImage SacalRunningRight;


    /// 4. Oblivion
    public static BufferedImage OblivionWalkLeft;
    public static BufferedImage OblivionWalkRight;
    public static BufferedImage OblivionAttack1Left;
    public static BufferedImage OblivionAttack2Left;
    public static BufferedImage OblivionAttack1Right;
    public static BufferedImage OblivionAttack2Right;





    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        level1 = new int[15][200];
        level2 = new int[15][200];
        level3 = new int[15][200];
        level1=preloadLevels("res/levels/level1duplicate.txt");
        level2=preloadLevels("res/levels/Level2.txt");
        level3=preloadLevels("res/levels/Level3.txt");

        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        //SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/PaooGameSpriteSheet.png"));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/ForrestAssets.png"));
        SpriteSheet hero = new SpriteSheet(ImageLoader.LoadImage("/textures/Hero2.png"));
        SpriteSheet heroInverted = new SpriteSheet(ImageLoader.LoadImage("/textures/Hero2Inverted.png"));
        SpriteSheet level3 = new SpriteSheet(ImageLoader.LoadImage("/textures/Level3.png"));
        SpriteSheet attackStickRight = new SpriteSheet(ImageLoader.LoadImage("/textures/attacks/animatieAtac1-sheet.png"));
        SpriteSheet attackStickLeft = new SpriteSheet(ImageLoader.LoadImage("/textures/attacks/animatieAtac1-sheetInv.png"));
        SpriteSheet attackSilverSwordRight = new SpriteSheet(ImageLoader.LoadImage("/textures/attacks/animatieAtacSabieStanga.png"));
        SpriteSheet attackSilverSwordLeft = new SpriteSheet(ImageLoader.LoadImage("/textures/attacks/animatieAtacSabieStangaInv.png"));
        SpriteSheet attackGoldSwordLeft = new SpriteSheet(ImageLoader.LoadImage("/textures/attacks/animatieAtacSabieAurInv.png"));
        SpriteSheet attackGoldSwordRight = new SpriteSheet(ImageLoader.LoadImage("/textures/attacks/animatieAtacSabieAur.png"));
        SpriteSheet attackAxeRight = new SpriteSheet(ImageLoader.LoadImage("/textures/attacks/animatieAtacAxe.png"));
        SpriteSheet attackAxeLeft = new SpriteSheet(ImageLoader.LoadImage("/textures/attacks/animatieAtacAxeInv.png"));

        SpriteSheet asterion = new SpriteSheet(ImageLoader.LoadImage("/NPC's/Asterion.png"));
        SpriteSheet goblin = new SpriteSheet(ImageLoader.LoadImage("/NPC's/Goblin.png"));
        SpriteSheet oblivion = new SpriteSheet(ImageLoader.LoadImage("/NPC's/Oblivion.png"));
        SpriteSheet sacal = new SpriteSheet(ImageLoader.LoadImage("/NPC's/Sacal.png"));
        //SpriteSheet sheet2 = new SpriteSheet();
        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass = sheet.crop(3, 0);//3
        soil = sheet.crop(3, 2);//53
        soilBottom=sheet.crop(3,10);//253
        grassLeft = sheet.crop(1, 1);//26
        grassRight = sheet.crop(5, 1);
        grassBottomLeft= sheet.crop(2,1);
        grassBottomRight= sheet.crop(4,1);
        skullRight = sheet.crop(5, 2);
        skullRightDown = sheet.crop(5, 3);
        skullLeft = sheet.crop(1, 2);
        thinStick = sheet.crop(18, 16);
        platformMiddle = sheet.crop(16, 16);
        platformRight = sheet.crop(17, 16);
        platformLeft = sheet.crop(15, 16);
        platform = sheet.crop(19,14);
        treeSquare = sheet.crop(11,3);
        fenceLeft = sheet.crop(14, 15);
        fenceRight = sheet.crop(17, 15);
        fenceMiddle = sheet.crop(15, 15);
        redSpikes = sheet.crop(19, 17);
        treeTrunkLeft92 = sheet.crop(17, 3);
        treeTrunkRight94 = sheet.crop(18, 3);
        treeTrunkMiddle93 = sheet.crop(19, 3);
        tree270 = sheet.crop(20, 10);
        tree269 = sheet.crop(19, 10);
        tree245 = sheet.crop(20, 9);
        tree246 = sheet.crop(21, 9);
        tree247 = sheet.crop(22, 9);
        tree272 = sheet.crop(22, 10);
        tree273 = sheet.crop(23, 10);
        tree298 = sheet.crop(23, 11);
        tree323 = sheet.crop(23, 12);
        tree322 = sheet.crop(22, 12);
        tree347 = sheet.crop(22, 13);
        tree346 = sheet.crop(21, 13);
        tree345 = sheet.crop(20, 13);
        tree320 = sheet.crop(20, 12);
        tree319 = sheet.crop(19, 12);
        tree294 = sheet.crop(19, 11);
        tree144 = sheet.crop(19, 5);
        tree117 = sheet.crop(17, 4);
        doorLeftDown = sheet.crop(20,3);
        doorLeftUp = sheet.crop(20,1);
        doorRightDown = sheet.crop(21,3);
        doorRightUp = sheet.crop(21,1);

        water484 = ImageLoader.LoadImage("/textures/Water.png");


        /// Assets pentru nivelul 2
        sandTopLeft = ImageLoader.LoadImage("/textures/level2/1.png");
        sandTopMiddle = ImageLoader.LoadImage("/textures/level2/2.png");
        sandTopRight = ImageLoader.LoadImage("/textures/level2/3.png");
        sandTopLeftUp11 = ImageLoader.LoadImage("/textures/level2/11.png");
        sandTopRightUp7 = ImageLoader.LoadImage("/textures/level2/7.png");
        hardSandCutLeft = ImageLoader.LoadImage("/textures/level2/4.png");
        hardSandCutRight = ImageLoader.LoadImage("/textures/level2/6.png");
        hardSand = ImageLoader.LoadImage("/textures/level2/5.png");
        hardSandTopLeft = ImageLoader.LoadImage("/textures/level2/8.png");
        hardSandTopRight = ImageLoader.LoadImage("/textures/level2/10.png");
        crate = ImageLoader.LoadImage("/textures/level2/Crate.png");
        stoneBlock = ImageLoader.LoadImage("/textures/level2/StoneBlock.png");

        bush = ImageLoader.LoadImage("/textures/level2/Bush (2).png");
        cactus1 = ImageLoader.LoadImage("/textures/level2/Cactus (1).png");
        cactus2 = ImageLoader.LoadImage("/textures/level2/Cactus (2).png");
        cactus3 = ImageLoader.LoadImage("/textures/level2/Cactus (3).png");
        grass1 = ImageLoader.LoadImage("/textures/level2/Grass (1).png");
        skeleton = ImageLoader.LoadImage("/textures/level2/Skeleton.png");
        stone = ImageLoader.LoadImage("/textures/level2/Stone.png");


        /// Assets pentru nivelul 3

        steelPlatform = level3.crop(2,5);
        rock = level3.crop(2,13);
        rockGrass = level3.crop(2,12);
        rock248 = level3.crop(8,16);
        cageUpRight = level3.crop(9,18);
        cageUpLeft = level3.crop(8,18);
        cageDownRight = level3.crop(9,20);
        cageDownLeft = level3.crop(8,20);
        cageSpikes = level3.crop(12,14);



        /// Assets pentru personajul principal
        base30= hero.crop(0,1);
        base31= hero.crop(1,1);

        walkRight120= hero.crop(0,4);
        walkRight121= hero.crop(1,4);
        walkRight122= hero.crop(2,4);
        walkRight123= hero.crop(3,4);

        jumpFall180=hero.crop(0,6);
        jumpFallLeft=heroInverted.crop(29,6);


        baseleft30 = heroInverted.crop(29,1);
        baseleft31 = heroInverted.crop(28,1);
        walkLeft149=heroInverted.crop(29,4);
        walkLeft148=heroInverted.crop(28,4);
        walkLeft147=heroInverted.crop(27,4);
        walkLeft146=heroInverted.crop(26,4);

        /// Assets personaj principal atac
        attackStickRight1 = attackStickRight.crop(0,0);
        attackStickRight2 = attackStickRight.crop(1,0);
        attackStickRight3 = attackStickRight.crop(2,0);
        attackStickLeft1 = attackStickLeft.crop(2,0);
        attackStickLeft2 = attackStickLeft.crop(1,0);
        attackStickLeft3 = attackStickLeft.crop(0,0);


        attackSilverSwordRight1 = attackSilverSwordRight.crop(0,0);
        attackSilverSwordRight2 = attackSilverSwordRight.crop(1,0);
        attackSilverSwordRight3 = attackSilverSwordRight.crop(2,0);

        attackSilverSwordLeft1 = attackSilverSwordLeft.crop(2,0);
        attackSilverSwordLeft2 = attackSilverSwordLeft.crop(1,0);
        attackSilverSwordLeft3 = attackSilverSwordLeft.crop(0,0);


        attackGoldSwordRight1 = attackGoldSwordRight.crop(0,0);
        attackGoldSwordRight2 = attackGoldSwordRight.crop(1,0);
        attackGoldSwordRight3 = attackGoldSwordRight.crop(2,0);

        attackGoldSwordLeft1 = attackGoldSwordLeft.crop(2,0);
        attackGoldSwordLeft2 = attackGoldSwordLeft.crop(1,0);
        attackGoldSwordLeft3 = attackGoldSwordLeft.crop(0,0);

        attackAxeRight1 = attackAxeRight.crop(0,0);
        attackAxeRight2 = attackAxeRight.crop(1,0);
        attackAxeRight3 = attackAxeRight.crop(2,0);

        attackAxeLeft1 = attackAxeLeft.crop(2,0);
        attackAxeLeft2 = attackAxeLeft.crop(1,0);
        attackAxeLeft3 = attackAxeLeft.crop(0,0);

        /// Assets collectable
        heart = ImageLoader.LoadImage("/textures/heart.png");
        goldSword = ImageLoader.LoadImage("/textures/sabieAurie1.png");


        /// Se extrag asset-urile pt fiecare NPC in parte
        /// 1. Asterion
        AsterionWalkLeft=asterion.crop(4,16,72,72);
        AsterionWalkRight=asterion.crop(3,0,72,72);
        AsterionAttack1Left=flipHorizontally(asterion.crop(0,4,64,72));
        AsterionAttack2Left=flipHorizontally(asterion.crop(2,4,64,72));
        AsterionAttack1Right=asterion.crop(0,4,64,72);
        AsterionAttack2Right=asterion.crop(2,4,64,72);

        /// 2. Forest Goblin
        GoblinWalkLeft=goblin.crop(16,3,72,72);
        GoblinWalkRight=flipHorizontally(GoblinWalkLeft);
        GoblinAttack1Left=goblin.crop(9,3,92,72);
        GoblinAttack2Left=goblin.crop(7,3,74,72);
        GoblinAttack1Right=flipHorizontally(GoblinAttack1Left);
        GoblinAttack2Right=flipHorizontally(GoblinAttack2Left);

        /// 3. Oblivion
        OblivionWalkLeft=oblivion.crop(0,0);
        OblivionWalkRight=flipHorizontally(OblivionWalkLeft);
        OblivionAttack1Left=oblivion.crop(3,2);
        OblivionAttack2Left=oblivion.crop(4,2);
        OblivionAttack1Right=flipHorizontally(OblivionAttack1Left);
        OblivionAttack2Right=flipHorizontally(OblivionAttack2Left);

        /// 4.Sacal
        SacalWalkLeft=cutTopHalf(sacal.crop(5,4));
        SacalWalkRight=cutTopHalf(sacal.crop(5,1));
        SacalRunningLeft=sacal.crop(9,4);
        SacalRunningRight=sacal.crop(9,1);
        SacalAttackLeft=cutTopHalf(sacal.crop(7,5));
        SacalAttackRight=cutTopHalf(sacal.crop(7,2));

        ///  Sprite pentru Matei
        matei=ImageLoader.LoadImage("/textures/Matei.png");


//        water = sheet.crop(2, 0);
//        mountain = sheet.crop(3, 0);
//        townGrass = sheet.crop(0, 1);
//        townGrassDestroyed = sheet.crop(1, 1);
//        townSoil = sheet.crop(2, 1);
//        tree = sheet.crop(3, 1);
//        heroLeft = sheet.crop(0, 2);
//        heroRight = sheet.crop(1, 2);
//        rockUp = sheet.crop(2, 2);
//        rockDown = sheet.crop(3, 2);
//        rockLeft = sheet.crop(0, 3);
//        rockRight = sheet.crop(1, 3);
        /// Imagini background
        background1 = ImageLoader.LoadImage("/levels/test2.png");
        background2 = ImageLoader.LoadImage("/levels/test.png");
        backgroundLevel2 = ImageLoader.LoadImage("/levels/BG.png");
        backgroundLevel3 = ImageLoader.LoadImage("/levels/Level3.png");
    }
    private static int[][] preloadLevels(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter("[\\s\\n]+");
            /// citire din fisier a matricii de date
            int i = 0;
            int j = 0;
            int[][] map = new int[15][200];
            for (i = 0; i < 15; i++) {
                for (j = 0; j < 200; j++) {
                    if (scanner.hasNext()) {
                        map[i][j] = Integer.parseInt(scanner.next());
                    }
                }
            }
            return map;
        }catch (Exception e) {
            System.out.println("Error in preloadLevels");
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage flipHorizontally(BufferedImage original) {
        if (original == null) {
            return null;
        }

        // Creează o nouă imagine cu aceleași dimensiuni și tip
        BufferedImage flipped = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                original.getType()
        );

        Graphics2D g = flipped.createGraphics();
        // Aplică transformarea de oglindire orizontală
        g.drawImage(original, original.getWidth(), 0, -original.getWidth(), original.getHeight(), null);
        g.dispose();

        return flipped;
    }

    public static BufferedImage cutTopHalf(BufferedImage original) {
        if (original == null) {
            return null;
        }

        int width = original.getWidth();
        int height = original.getHeight();

        // Crează o imagine nouă cu aceleași dimensiuni și transparență (dacă există)
        BufferedImage result = new BufferedImage(
                width,
                height,
                original.getTransparency() == Transparency.OPAQUE
                        ? BufferedImage.TYPE_INT_RGB
                        : BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = result.createGraphics();

        // Desenează întreaga imagine originală
        g.drawImage(original, 0, 0, null);

        // Acoperă jumătatea superioară cu alb (sau transparent)
        g.setColor(new Color(255, 255, 255, 0)); // Transparent
        // SAU: g.setColor(Color.WHITE); // Alb solid
        g.setComposite(AlphaComposite.Src); // Resetează pixelii

        // Desenează un dreptunghi peste jumătatea superioară
        g.fillRect(0, 0, width, height / 2);

        g.dispose();
        return result;
    }

}
