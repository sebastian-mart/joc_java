package PaooGame.Items;



import PaooGame.Camera.Camera;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Heart extends Item {
    private BufferedImage texture;

    public Heart(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH-16, Tile.TILE_HEIGHT-16);
        this.texture = Assets.heart;
    }

    @Override
    public void Update() {
        if(!collected && getCollisionBounds().intersects(refLink.GetHero().getCollisionBounds()) && refLink.GetHero().GetHealth()<100) {
            refLink.GetHero().SetHealth(100);
            collected = true;

        }
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
        return collected;
    }

    @Override
    public int getScoreValue() {
        return 10;
    }

}
