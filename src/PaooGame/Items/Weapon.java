package PaooGame.Items;

import java.awt.Rectangle;

public class Weapon {
    private float damage;//damage-ul dat de arma
    private Rectangle hitbox;//aria in care arma poate da damage


    public Weapon(float damage, int widthHitbox, int heightHitbox) {
        this.damage = damage;
        this.hitbox = new Rectangle(0, 0, widthHitbox, heightHitbox);
    }

    public Rectangle getHitbox(float x, float y, boolean facingRight) {
        int finalX = (int)x + (facingRight ? 115 : - hitbox.width);//se calculeaza pozitia de unde sa inceapa hitboxul
                                                                       // pe axa x fata de pozitia jucatorului
        return new Rectangle(finalX, (int)y +60, hitbox.width, hitbox.height);
    }

    public float getDamage() {
        return damage;
    }
}