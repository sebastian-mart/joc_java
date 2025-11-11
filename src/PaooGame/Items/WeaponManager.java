package PaooGame.Items;

import PaooGame.CustomExceptions.InvalidWeaponIndexException;
import PaooGame.Items.Weapon;

public class WeaponManager {
    private Weapon []weapons;
    private int currentWeapon;

    public WeaponManager(){
        weapons = new Weapon[4];
        currentWeapon = 0;
        weapons[0] = new Weapon(10f,50,50 );//Stick
        weapons[1] = new Weapon(15f,64,64 );//sabie de argint
        weapons[2] = new Weapon(30f,64,64 );//sabie aurie
        weapons[3] = new Weapon(25f,64,64 );//topor
    }

    public Weapon getCurrentWeapon(){
        return weapons[currentWeapon];
    }

    public int getCurrentWeaponIndex(){
        return currentWeapon;
    }

    public float getCurrentWeaponDamage(){
        return getCurrentWeapon().getDamage();
    }

    public void switchWeapon(int index){
        if(index>=0 && index<4){
            currentWeapon=index;
        }else {
            throw new InvalidWeaponIndexException(index);
        }
    }
}
