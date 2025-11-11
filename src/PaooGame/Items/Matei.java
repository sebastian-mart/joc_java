package PaooGame.Items;



import PaooGame.Camera.Camera;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Matei extends Item {
    private BufferedImage texture;

    public Matei(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        this.texture = Assets.matei;
    }

    @Override
    public void Update() {
        /// Nothing, cannot be collected
    }

    @Override
    public void Draw(Graphics g) {
        if(!collected) {
            Camera camera = refLink.GetCamera();
            g.drawImage(texture, (int)(x - camera.getXOffset()), (int)y, width, height, null);
        }
    }

    public Rectangle getCollisionBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    @Override
    public boolean isCollected() {
        return false;
    }

    @Override
    public int getScoreValue() {
        return 10;
    }

}
