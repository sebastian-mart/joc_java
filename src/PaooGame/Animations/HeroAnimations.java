package PaooGame.Animations;

import PaooGame.Graphics.Assets;

public class HeroAnimations {
    private Animation walkRight;
    private Animation walkLeft;
    private Animation idleRight;
    private Animation idleLeft;
    private Animation jumpRight;
    private Animation jumpLeft;
    //private Animation attackStickRight;
    private WeaponAnimationManager weaponAnims;

    public HeroAnimations() {
        walkRight = new Animation();//animatii mers la dreapta
        walkLeft = new Animation();//animatii mers la stanga
        idleRight = new Animation();//animatii idle
        idleLeft = new Animation();
        jumpRight = new Animation();//animatii sarit dreapta
        jumpLeft = new Animation();//animatii sarit stanga
        //attackStickRight = new Animation();//animatie atac stick dreapta
        weaponAnims = new WeaponAnimationManager();
        setupAnimations();
    }

    private void setupAnimations() {

        walkRight.addFrame(Assets.walkRight120);
        walkRight.addFrame(Assets.walkRight121);
        walkRight.addFrame(Assets.walkRight122);
        walkRight.addFrame(Assets.walkRight123);
        walkRight.setFrameDelay(4);//se schimba frame-ul la fiecare 5 secunde


        walkLeft.addFrame(Assets.walkLeft149);
        walkLeft.addFrame(Assets.walkLeft148);
        walkLeft.addFrame(Assets.walkLeft147);
        walkLeft.addFrame(Assets.walkLeft146);
        walkLeft.setFrameDelay(4);


        idleRight.addFrame(Assets.base30);
        idleRight.addFrame(Assets.base31);
        idleRight.setFrameDelay(20);

        idleLeft.addFrame(Assets.baseleft30);
        idleLeft.addFrame(Assets.baseleft31);
        idleLeft.setFrameDelay(20);

        jumpRight.addFrame(Assets.jumpFall180);
        jumpRight.setFrameDelay(0); // Este o singura imagine, asa ca nu are nevoie de delay

        jumpLeft.addFrame(Assets.jumpFallLeft);
        jumpLeft.setFrameDelay(0);// la fel

//        attackStickRight.addFrame(Assets.attackStickRight1);
//        attackStickRight.addFrame(Assets.attackStickRight2);
//        attackStickRight.addFrame(Assets.attackStickRight3);
//        attackStickRight.addFrame(Assets.attackStickRight2);
//        attackStickRight.setFrameDelay(4);
    }

    public void tick() {
        walkRight.tick();
        walkLeft.tick();
        idleRight.tick();
        idleLeft.tick();
        jumpLeft.tick();
        jumpRight.tick();
        //attackStickRight.tick();
        weaponAnims.tick();
    }


    public Animation getWalkRight() { return walkRight; }
    public Animation getWalkLeft() { return walkLeft; }
    public Animation getIdleRight() { return idleRight; }
    public Animation getIdleLeft() {return idleLeft;}
    public Animation getJumpRight() { return jumpRight; }
    public Animation getJumpLeft() {return jumpLeft;}
    public WeaponAnimationManager getWeaponManager() {return weaponAnims;}
}