package PaooGame.NPC;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import java.awt.*;

public class Asterion extends NPC{

    private long lastAttackTime = 0;
    //private static final long ATTACK_COOLDOWN_MS = 1000; // 1 second cooldown

    private static final float DETECTION_RANGE = 300.0f;
    private static final float ATTACK_RANGE = 90.0f;
    private static final int ATTACK_COOLDOWN = 70;
    private static final int ATTACK_ANIMATION_SPEED = 20; //cu cat e mai mare, cu atat miscarea de atac e mai lenta


    private int attackTimer = 0;
    private int animationTimer = 0;
    private int currentAttackFrame = 0;


    public Asterion(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        image = Assets.AsterionWalkLeft;
        isSpawned = true;
        speed = 4.0f;
        spawnX = (int) x;
    }

    @Override
    public void Update() {
        if (isSpawned) {
            if(health<=0){
                Despawn();
                return;
            }

            Rectangle futureBoundsX = new Rectangle((int) (x + xMove + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
            if(refLink.GetLevelManager().getCurrentLevel().checkType(futureBoundsX) !=0)
                xMove=0;

            ApplyGravity();
            Move();
            CheckCollisions();
            UpdateAttack();
            UpdateAttackAnimation();
        }
    }

    private void UpdateAttackAnimation(){
        if (isAttacking) {
            animationTimer++;
            if (animationTimer >= ATTACK_ANIMATION_SPEED) {
                animationTimer = 0;
                currentAttackFrame = (currentAttackFrame + 1) % 2; // Alternăm între 0 și 1
                // Actualizăm imaginea în funcție de frame-ul curent
                if (xMove < 0) { // Merge spre stânga
                    if (currentAttackFrame == 0) {
                        image = Assets.AsterionAttack1Left;
                    } else {
                        image = Assets.AsterionAttack2Left;
                    }
                } else { // Merge spre dreapta
                    if (currentAttackFrame == 0) {
                        image = Assets.AsterionAttack1Right;
                    } else {
                        image = Assets.AsterionAttack2Right;
                    }
                }
            }
        } else {
            // Resetăm animația când nu atacă
            animationTimer = ATTACK_COOLDOWN;
            currentAttackFrame = 0;
            image = (xMove<0)? Assets.AsterionWalkLeft : Assets.AsterionWalkRight;
        }
    }

    @Override
    public void Draw(Graphics g) {
        if (isSpawned) {
            float xOffset = refLink.GetCamera().getXOffset();
            g.drawImage(image, (int)(x - xOffset), (int)y, width, height, null);

            // Desenăm bara de viață
            if (health < 100) {
                g.setColor(Color.RED);
                g.fillRect((int)(x - xOffset), (int)y - 20, width, 5);
                g.setColor(Color.GREEN);
                g.fillRect((int)(x - xOffset), (int)y - 20, (int)(width * (health / 100.0)), 5);
            }
        }
    }

    @Override
    public void Move() {
        if (!isSpawned) return;

        Hero hero = refLink.GetHero();
        if (hero == null) return;

        float heroX = hero.GetX();
        float heroY = hero.GetY();
        float horizontal_distance = Math.abs(heroX - x);
        float vertical_distance = Math.abs(heroY - y);

        /*/// depanare
        System.out.println(
                "HeroX: " + heroX +
                        " | SacalX: " + x +
                        " | isAttacking: " + isAttacking +
                        " | xMove: " + xMove
        );*/

        // Verifică dacă eroul este în raza de ATAC (nu doar detecție)
        if (horizontal_distance < ATTACK_RANGE && vertical_distance < VERTICAL_ATTACK_THRESHOLD) {
            isAttacking = true;
            xMove = 0; // Oprește mișcarea pentru atac
            image = (x < heroX) ? Assets.AsterionAttack1Right : Assets.AsterionAttack1Left;
        }
        // Dacă eroul este în raza de DETECȚIE (dar nu și atac)
        else if (horizontal_distance < DETECTION_RANGE) {
            isAttacking = false;
            if (heroX < x) {
                xMove = -speed; // Mergi spre stânga
                image = Assets.AsterionWalkLeft;
            } else {
                xMove = speed; // Mergi spre dreapta
                image = Assets.AsterionWalkRight;
            }
        }
        // Dacă eroul este în afara razei de detecție
        else {
            isAttacking = false;
            xMove = 0; // Oprit pe loc
        }
    }
    @Override
    protected void UpdateAttack() {
        if (isAttacking) {
            if (attackTimer <= 0) {
                PerformAttack();
                attackTimer = ATTACK_COOLDOWN;
            } else {
                attackTimer--;
            }
        } else {
            attackTimer = ATTACK_COOLDOWN-30;
        }
    }

    @Override
    protected void PerformAttack() {
        Hero hero = refLink.GetHero();
        if (hero == null) return;

        float distance = Math.abs(hero.GetX() - x);
        if (distance < ATTACK_RANGE) {
            hero.TakeDamage(10); // Presupunem că Hero are o metodă TakeDamage
            // Aici poți adăuga efecte vizuale sau sonore pentru atac
        }
    }

    @Override
    public int getScoreValue() {
        return 70;
    }



}
