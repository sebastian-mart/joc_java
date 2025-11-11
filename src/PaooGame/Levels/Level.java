package PaooGame.Levels;

import PaooGame.Items.GoldenSwordPickup;
import PaooGame.Items.Heart;
import PaooGame.Items.Item;
import PaooGame.NPC.NPC;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.Camera.Camera;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public abstract class Level {
    protected RefLinks refLink;
    protected int width;
    protected int height;
    protected int[][] tiles;
    protected int spawnX,spawnY;
    protected ArrayList<Item> items;
    protected boolean[] itemsbool=new boolean[10];
    protected ArrayList<NPC> npc_list;
    protected boolean[] npcbool=new boolean[10];
    public Level(RefLinks refLink) {
        /// Retine referinta "shortcut".
        this.refLink = refLink;
        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat
        LoadWorld();
    }

    public int getWidthPixels() {
        return width * Tile.TILE_WIDTH;
    }

    public int GetMapWidth() {
        return width;
    }

    public int GetMapHeight() {
        return height;
    }

    /**
     * Implementarea fiecărui nivel trebuie să încarce matricea `tiles[x][y]`.
     */


    public void Update() {

//        for (Item item : items){
//            if ((item instanceof GoldenSwordPickup && ((GoldenSwordPickup)item).isCollected()) || (item instanceof Heart && ((Heart)item).isCollected())){
//                itemsbool[items.indexOf(item)]=false;
//            }
//        }
//        /// Afiseaza doar itemele ce sunt valide din vectorul itemsbool
//        for(Item item : items) {
//            if(itemsbool[items.indexOf(item)]){
//                item.Update();
//            }
//        }
        // Actualizare iteme
        for(int i = 0; i < items.size(); i++) {
            if(itemsbool[i]) {
                items.get(i).Update();
                if(items.get(i).isCollected()) {
                    itemsbool[i] = false;
                    refLink.GetHero().addScore(items.get(i).getScoreValue());
                }
            }
            else{
                items.get(i).setCollected(true);
            }
        }

        // Actualizare NPC
        for(int i = 0; i < npc_list.size(); i++) {
            if(npcbool[i]) {
                npc_list.get(i).Update();
                if(npc_list.get(i).isDead()) {
                    npcbool[i] = false;
                    refLink.GetHero().addScore(npc_list.get(i).getScoreValue());
                }
            }
        }
    }


    public void Draw(Graphics g) {
        Camera camera = refLink.GetCamera();
        float xOffset = camera.getXOffset();

        // desen background


        int startX = (int) (xOffset / Tile.TILE_WIDTH);
        int endX = (int) ((xOffset + refLink.GetWidth()) / Tile.TILE_WIDTH) + 2;

        startX = Math.max(0, startX);
        endX = Math.min(width, endX);

        for (int y = 0; y < height; y++) {
            for (int x = startX; x < endX; x++) {
                Tile tile = GetTile(x, y);
                if (tile != null) {
                    int drawX = (int) (x * Tile.TILE_WIDTH - xOffset);
                    int drawY = y * Tile.TILE_HEIGHT;
                    tile.Draw(g, drawX, drawY);
                }
            }
        }
    }

    public boolean isSolidTile(int x, int y) {
        // Converteste coordonatele world în coordonate tile
        int tileX = x / Tile.TILE_WIDTH;
        int tileY = y / Tile.TILE_HEIGHT;

        // Verifica daca pozitia este în afara hartii
        if (x <= 0 || y < 0 || x >= width || y >= height) {
            return true; // Considera marginile ca solide
        }

        Tile tile = GetTile(x, y);
        return tile != null && tile.IsSolid;
    }
    public boolean isDeathTile(int x, int y) {
        // Converteste coordonatele world în coordonate tile
        int tileX = x / Tile.TILE_WIDTH;
        int tileY = y / Tile.TILE_HEIGHT;


        Tile tile = GetTile(x, y);
        return tile != null && tile.IsDeath();
    }

    public int checkType(Rectangle bounds) {
        // Verifica coliziunea pe toate cele 4 laturi
        int leftTile = bounds.x / Tile.TILE_WIDTH;
        int rightTile = (bounds.x + bounds.width) / Tile.TILE_WIDTH;
        int topTile = bounds.y / Tile.TILE_HEIGHT;
        int bottomTile = (bounds.y + bounds.height) / Tile.TILE_HEIGHT;

        for (int y = topTile; y <= bottomTile; y++) {
            for (int x = leftTile; x <= rightTile; x++) {
                if (isSolidTile(x, y)) {
                    return 1;
                }
                if (isDeathTile(x,y)){
                    return 2;
                }
            }
        }
        return 0;
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }

        int tileId = tiles[x][y];
        if (tileId == -1) {
            return null; // Ignoră tile-urile marcate cu -1
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            System.out.println("Nu exista tile facut" + tiles[x][y]);
            return null;
        }
        return t;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld() {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de dale.
        width = 200;
        ///Se stabileste inaltimea hartii in numar de dale
        height = 15;
        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
        //Se incarca matricea cu coduri
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = MiddleEastMap(y, x);///Incearca sa incarc
            }
        }
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public ArrayList<NPC> getNPC() {
        return npc_list;
    }

    abstract protected int MiddleEastMap(int x, int y);
    public abstract boolean isExitReached(int heroX, int heroY);
    public int GetSpawnX(){return spawnX;}
    public int GetSpawnY(){return spawnY;}

    public boolean[] getItemsbool() {
        return itemsbool;
    }
    public void setItemsbool(boolean bool,int index) {this.itemsbool[index] = bool;}
    public void setItemsbool(boolean[] bool) {this.itemsbool=bool;}

    public boolean[] getNpcbool() {
        return npcbool;
    }
    public void setNpcbool(boolean bool,int index) {this.npcbool[index] = bool;}
    public void setNpcbool(boolean[] bool) {this.npcbool=bool;}

    public boolean isBossBeat(){return !npcbool[0];}
}
