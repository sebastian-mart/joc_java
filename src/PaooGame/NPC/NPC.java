package PaooGame.NPC;

import PaooGame.CustomExceptions.NegativeDamageException;
import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class NPC extends Character {
    protected BufferedImage image;
    protected Boolean isSpawned;
    protected Boolean isDead;
    protected boolean isFalling = false;
    protected boolean isAttacking = false;
    protected int spawnX;

    protected static final float VERTICAL_ATTACK_THRESHOLD = 30.0f;
    protected static final int MAX_DISTANCE_FROM_SPAWN = 5 * Tile.TILE_WIDTH;


    public NPC(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH,Character.DEFAULT_CREATURE_WIDTH);

        normalBounds.x = 16;//32;
        normalBounds.y = 48;//90;//32;
        normalBounds.width = 32;//64;
        normalBounds.height = 48;//32;//90;

        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;

        spawnX = (int) (x* Tile.TILE_WIDTH);
    }

    public abstract void Update();
    public abstract void Draw(Graphics g);
    public abstract void Move();

    protected abstract void UpdateAttack();
    protected abstract void PerformAttack();

    public void Spawn(){
        isSpawned=true;
        health=100;
        isDead=false;
    }
    public void Despawn(){
        isSpawned=false;
    }
    protected void CheckCollisions() {
        // Coliziune pe axa X
        if (xMove != 0) {
            Rectangle futureBoundsX = new Rectangle((int) (x + xMove + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);

            if (refLink.GetLevelManager().getCurrentLevel().checkType(futureBoundsX)==0) {
                x+=xMove;
            }
        }

        // Coliziune pe axa Y

        if (yMove != 0) {
            Rectangle futureBoundsY = new Rectangle((int) (x + bounds.x), (int) (y + yMove + bounds.y), bounds.width, bounds.height);
            if (refLink.GetLevelManager().getCurrentLevel().checkType(futureBoundsY)==1) {
                if (yMove > 0) {
                    // Coliziune cu soluldw
                    isFalling = false;
                    y = (int)(y + yMove) - (normalBounds.y + normalBounds.height - height);
                }
                yMove = 0;
            } else if (yMove > 0) {
                isFalling = true;
            }
        }
        /// Conditie pentru cazut atunci cand cazi de pe o platforma
        if (yMove == 0 && !isFalling) {
            //Rectangle futureBoundsX = new Rectangle((int) (x + 7 + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
            //Rectangle futureBoundsY = new Rectangle((int) (x + bounds.x), (int) (y + 5+ bounds.y), bounds.width, bounds.height);
            //if (refLink.GetLevelManager().getCurrentLevel().checkType(futureBoundsY)==0 && refLink.GetLevelManager().getCurrentLevel().checkType(futureBoundsX)==0) {
               // isFalling = true;
            //}
            Rectangle checkBelow = new Rectangle(
                    (int) (x + bounds.x),
                    (int) (y + bounds.y + 1), // 1 pixel sub NPC
                    bounds.width,
                    1//bounds.height
            );
            if (refLink.GetLevelManager().getCurrentLevel().checkType(checkBelow) == 0)
                isFalling=true;
        }

    }

    protected void ApplyGravity() {
        /*if (isFalling) {
            yMove += 0.5f; // Gravitație
            if (yMove > 10) {
                yMove = 10; // Viteză maximă de cădere
            }
        }*/
        if(isFalling){
            yMove+=0.5f;
            yMove=Math.min(yMove,10);
        }else{
            yMove=0;
        }
    }
    public void TakeDamage(int damage) {
        if (damage < 0) {
            throw new NegativeDamageException(damage);
        }
        health -= damage;
        if (health <= 0) {
            Despawn();
            isDead=true;
        }
    }
    public Rectangle GetHitBox(){
        return new Rectangle((int)(x-refLink.GetCamera().getXOffset()), (int)y, width, height);
    }

    public Boolean isDead() {
        return isDead;
    }
}
