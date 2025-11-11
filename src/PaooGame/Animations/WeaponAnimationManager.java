package PaooGame.Animations;

import PaooGame.CustomExceptions.InvalidWeaponIndexException;
import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

public class WeaponAnimationManager {
    private Animation[] rightAttackAnims;
    private Animation[] leftAttackAnims;
    private float[] damageDealt = {2f,8f,10f,5f};
    private int currentWeapon;

    public WeaponAnimationManager() {
        currentWeapon = 0; // Băț implicit
        rightAttackAnims = new Animation[4];
        leftAttackAnims = new Animation[4];

        initializeAnimations();
    }

    private void initializeAnimations() {
        // STICK
        rightAttackAnims[0] = createStickAttackRight();
        leftAttackAnims[0] = createStickAttackLeft();

        // Sabie argintie
        rightAttackAnims[1] = createSilverSwordAttackRight();
        leftAttackAnims[1] = createSilverSwordAttackLeft();


        // Sabie aurie
        rightAttackAnims[2] = createGoldSwordAttackRight();
        leftAttackAnims[2] = createGoldSwordAttackLeft();

        // Topor
        rightAttackAnims[3] = createAxeAttackRight();
        leftAttackAnims[3] = createAxeAttackLeft();
    }

    private Animation createStickAttackRight() {
        Animation anim = new Animation();
        anim.addFrame(Assets.attackStickRight1);
        anim.addFrame(Assets.attackStickRight2);
        anim.addFrame(Assets.attackStickRight3);
        anim.addFrame(Assets.attackStickRight2);
        anim.setFrameDelay(3);
        return anim;
    }
    private Animation createStickAttackLeft() {
        Animation anim = new Animation();
        anim.addFrame(Assets.attackStickLeft1);
        anim.addFrame(Assets.attackStickLeft2);
        anim.addFrame(Assets.attackStickLeft3);
        anim.addFrame(Assets.attackStickLeft2);
        anim.setFrameDelay(3);
        return anim;
    }
    private Animation createAxeAttackRight(){
        Animation anim = new Animation();
        anim.addFrame(Assets.attackAxeRight1);
        anim.addFrame(Assets.attackAxeRight2);
        anim.addFrame(Assets.attackAxeRight3);
        anim.addFrame(Assets.attackAxeRight2);
        anim.setFrameDelay(8);
        return anim;
    }
    private Animation createAxeAttackLeft(){
        Animation anim = new Animation();
        anim.addFrame(Assets.attackAxeLeft1);
        anim.addFrame(Assets.attackAxeLeft2);
        anim.addFrame(Assets.attackAxeLeft3);
        anim.addFrame(Assets.attackAxeLeft2);
        anim.setFrameDelay(8);
        return anim;
    }

    private Animation createGoldSwordAttackRight(){
        Animation anim = new Animation();
        anim.addFrame(Assets.attackGoldSwordRight1);
        anim.addFrame(Assets.attackGoldSwordRight2);
        anim.addFrame(Assets.attackGoldSwordRight3);
        anim.addFrame(Assets.attackGoldSwordRight2);
        anim.setFrameDelay(4);
        return anim;
    }
    private Animation createGoldSwordAttackLeft(){
        Animation anim = new Animation();
        anim.addFrame(Assets.attackGoldSwordLeft1);
        anim.addFrame(Assets.attackGoldSwordLeft2);
        anim.addFrame(Assets.attackGoldSwordLeft3);
        anim.addFrame(Assets.attackGoldSwordLeft2);
        anim.setFrameDelay(4);
        return anim;
    }

    private Animation createSilverSwordAttackRight(){
        Animation anim = new Animation();
        anim.addFrame(Assets.attackSilverSwordRight1);
        anim.addFrame(Assets.attackSilverSwordRight2);
        anim.addFrame(Assets.attackSilverSwordRight3);
        anim.addFrame(Assets.attackSilverSwordRight2);
        anim.setFrameDelay(4);
        return anim;
    }
    private Animation createSilverSwordAttackLeft(){
        Animation anim = new Animation();
        anim.addFrame(Assets.attackSilverSwordLeft1);
        anim.addFrame(Assets.attackSilverSwordLeft2);
        anim.addFrame(Assets.attackSilverSwordLeft3);
        anim.addFrame(Assets.attackSilverSwordLeft2);
        anim.setFrameDelay(4);
        return anim;
    }


    public void tick() {
        rightAttackAnims[currentWeapon].tick();
        leftAttackAnims[currentWeapon].tick();
    }
    public Animation getCurrentAnimation(boolean facingRight){
        if(facingRight){
            return rightAttackAnims[currentWeapon];
        }
        return leftAttackAnims[currentWeapon];
    }

    public BufferedImage getCurrentFrame(boolean facingRight) {
        if(facingRight)
                return rightAttackAnims[currentWeapon].getCurrentFrame();
        return leftAttackAnims[currentWeapon].getCurrentFrame();
    }

    public void switchWeapon(int index) {
        if(index >= 0 && index < 4) {
            currentWeapon = index;
        }
        else throw new InvalidWeaponIndexException(index);
    }
    public float getDamageDealt(){
        return damageDealt[currentWeapon];
    }

    public void resetAttack() {
        rightAttackAnims[currentWeapon].reset();
        leftAttackAnims[currentWeapon].reset();
    }
}