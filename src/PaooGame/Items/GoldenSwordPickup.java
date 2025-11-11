package PaooGame.Items;

import PaooGame.Camera.Camera;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GoldenSwordPickup extends Item {
    private BufferedImage texture;

    public GoldenSwordPickup(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH-15, Tile.TILE_HEIGHT-15);
        this.texture = Assets.goldSword; // Asigură-te că ai această textură în Assets
    }

    @Override
    public void Update() {
        if(!collected && getCollisionBounds().intersects(refLink.GetHero().getCollisionBounds())) {
            refLink.GetHero().setWeaponUnlocked(2);
            refLink.GetHero().switchWeapon(2); // Indexul pentru sabia aurie
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
        return 100;
    }
}