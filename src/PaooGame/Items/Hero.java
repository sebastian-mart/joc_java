package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.CustomExceptions.NegativeDamageException;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Camera.Camera;
import PaooGame.Animations.HeroAnimations;
import PaooGame.States.State;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character {
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private HeroAnimations animations; //manager pentru animatii
    private WeaponManager weapon; //manager pentru arme
    private boolean[] weaponUnlocked=new boolean[4];//array care indica ce arme sunt deblocate
    private Rectangle attackHitbox;
    private int score = 0;


    private boolean isJumping = false;
    private boolean isFalling = false;

    private boolean isSpawned;

    private boolean isAttacking = false;
    private boolean facingRight;

    private boolean hasDealtDamage = false;//variabila care asigura ca personajul nu va ataca pe tot timpul animatiei

    private long lastAttackTime = 0;//timpul la care eroul a atacat ultima data
    private static final long ATTACK_COOLDOWN = 500; // 500ms cooldown (0.5 seconds)
    private boolean canAttack = true;//variabila care verifica daca ne aflam in cooldown

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        health = 100;
        /// Initializare animatii pentru erou
        animations=new HeroAnimations();
        /// initializare manager-ul de arme
        weapon = new WeaponManager();
        /// variabila care arata daca personajul are fata spre stanga sau dreapta
        facingRight= true;
        ///Seteaza imaginea de start a eroului
        image = Assets.base30;
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 32;
        normalBounds.y = 32;
        normalBounds.width = 64;
        normalBounds.height = 90;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 64;
        attackBounds.height = 64;
        isSpawned=true;


    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update() {
        ///Verifica daca a fost apasata o tasta
        GetInput();

        /// Verifica daca a atins un tile normal
        CheckLife();

        /// Aplica gravitatia
        ApplyGravity();

        /// Coliziuni basic
        CheckCollisionsAndDeath();

        ///Actualizeaza pozitia
        Move();

        long currentTime = System.currentTimeMillis();
        canAttack = ((currentTime - lastAttackTime) >= ATTACK_COOLDOWN);

        animations.tick();
        // Gestionare atac
        if(isAttacking) {
            if(animations.getWeaponManager().getCurrentAnimation(facingRight).hasPlayedOnce()) {
                isAttacking = false;
                hasDealtDamage = false;
            }
        }
        else if(refLink.GetKeyManager().space && canAttack) {
            isAttacking = true;
            hasDealtDamage = false;
            updateAttackHitbox();
            animations.getWeaponManager().resetAttack();
            lastAttackTime = currentTime;
        }

        // Actualizare imagine
        updateImage();

//
//        ///Actualizeaza imaginea
//        if (isJumping || isFalling) {
//            if(refLink.GetKeyManager().left){
//                image = animations.getJumpLeft().getCurrentFrame();
//            }
//            else {
//                image = animations.getJumpRight().getCurrentFrame();
//            }
//        }
//        else if (refLink.GetKeyManager().space) {
//            image = animations.getAttackStickRight().getCurrentFrame();
//        }
//        else if (refLink.GetKeyManager().left) {
//            image = animations.getWalkLeft().getCurrentFrame();
//            facingRight = false;
//        }
//        else if (refLink.GetKeyManager().right) {
//            image = animations.getWalkRight().getCurrentFrame();
//            facingRight = true;
//        }
//        else if(facingRight){
//            image = animations.getIdleRight().getCurrentFrame();
//        }
//        else{
//            image = animations.getIdleLeft().getCurrentFrame();
//        }


    }
    private void CheckLife(){
        if(GetHealth() <= 0) {
            State state = refLink.GetGame().GetDeathState();
            State.SetState(state);
        }
    }
    /// updateImage() se ocupa de alegerea imaginii ce va fi afisata
    private void updateImage(){
        ///Actualizeaza imaginea
        if (isJumping || isFalling) {
            if(facingRight){
                image = animations.getJumpRight().getCurrentFrame();
            }
            else {
                image = animations.getJumpLeft().getCurrentFrame();
            }
        }
        else if (refLink.GetKeyManager().left) {
            image = animations.getWalkLeft().getCurrentFrame();
        }
        else if (refLink.GetKeyManager().right) {
            image = animations.getWalkRight().getCurrentFrame();
        }
        else if(isAttacking) {
            image = animations.getWeaponManager().getCurrentFrame(facingRight);
        }
        else if(facingRight){
            image = animations.getIdleRight().getCurrentFrame();
        }
        else{
            image = animations.getIdleLeft().getCurrentFrame();
        }
    }
    private void updateAttackHitbox() {
        Weapon currentWeapon = weapon.getCurrentWeapon();
        attackHitbox = currentWeapon.getHitbox(x, y, facingRight);
    }
    protected void switchWeapon(int index){
        animations.getWeaponManager().switchWeapon(index);
        weapon.switchWeapon(index);
    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput() {
        if(refLink.GetKeyManager().key1 && !isAttacking){
            switchWeapon(0);
        }
        if(refLink.GetKeyManager().key2 && weaponUnlocked[1] && !isAttacking) {/// Verifica daca personajul ataca si daca arma e dobandita
            switchWeapon(1);
        }
        if(refLink.GetKeyManager().key3 && weaponUnlocked[2] && !isAttacking){
            switchWeapon(2);
        }
        if(refLink.GetKeyManager().key4 && weaponUnlocked[3] && !isAttacking){
            switchWeapon(3);
        }
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        ///Verificare apasare tasta "sus"
        if (refLink.GetKeyManager().up && !this.isFalling && !this.isJumping) {
            yMove = -speed * 2.4f;
            isJumping = true;
        }
        ///Verificare apasare tasta "left"
        if (refLink.GetKeyManager().left) {
            xMove = -speed;
            facingRight = false;
        }
        ///Verificare apasare tasta "dreapta"
        if (refLink.GetKeyManager().right) {
            xMove = speed;
            facingRight = true;
        }
        /// Verificare tasta "atac"
        if (refLink.GetKeyManager().space){

        }
        if (!refLink.GetKeyManager().space) {
            canAttack = true;
        }
    }
/// ApplyGravity() se asigura ca personajul principal va cadea daca nu are niciun bloc solid sub el(adica respectarea gravitatiei)
    private void ApplyGravity() {
        if (isJumping || isFalling) {
            yMove += 0.5f; // Gravitație
            if (yMove > 10) {
                yMove = 10; // Viteză maximă de cădere
            }
        }
    }
/// CheckCollisions() se ocupa de verificarea coliziunilor in diferite cazuri
    private void CheckCollisionsAndDeath() {
        if(refLink.GetLevelManager().getCurrentLevel().checkType(new Rectangle((int)x,(int)y,bounds.width,bounds.height))==2){
            health = 0;
        }

        // Coliziune pe axa X
        if (xMove != 0) {
            Rectangle futureBoundsX = new Rectangle((int) (x + xMove + bounds.x), (int) (y + bounds.y-1), bounds.width, bounds.height);

            if (refLink.GetLevelManager().getCurrentLevel().checkType(futureBoundsX)==1) {
                xMove = 0;
            }
        }

        /// Coliziune pe axa Y
        if (yMove != 0) {
            Rectangle futureBoundsY = new Rectangle((int) (x + bounds.x), (int) (y + yMove + bounds.y), bounds.width, bounds.height);
            if (refLink.GetLevelManager().getCurrentLevel().checkType(futureBoundsY)==1) {
                if (yMove > 0) {
                    // Coliziune cu solul
                    isJumping = false;
                    isFalling = false;
                }
                yMove = 0;
            } else if (yMove > 0) {
                isFalling = true;
            }
        }

        /// Conditie pentru cazut atunci cand cazi de pe o platforma
        if (yMove == 0) {
            Rectangle futureBoundsX = new Rectangle((int) (x + 7 + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
            Rectangle futureBoundsY = new Rectangle((int) (x + bounds.x), (int) (y + 5+ bounds.y), bounds.width, bounds.height);
            if (!(refLink.GetLevelManager().getCurrentLevel().checkType(futureBoundsY)==1) && !(refLink.GetLevelManager().getCurrentLevel().checkType(futureBoundsX)==1)) {
                    isFalling = true;
            }
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g) {

        if(isSpawned) {
            Camera camera = refLink.GetCamera();
            float xOffset = camera.getXOffset();
            g.drawImage(image, (int) (x - xOffset), (int) y, width, height, null);

//            if(isAttacking){
//              g.setColor(Color.RED);
//              g.fillRect((int) (attackHitbox.x-xOffset),attackHitbox.y,attackHitbox.width,attackHitbox.height);
//            }
        }


            ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
//        g.setColor(Color.blue);
//        g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
        }


    public Rectangle getCollisionBounds() {
        return new Rectangle(
                (int)x + bounds.x,      // Poziția X ajustată cu offset-ul bound-ului
                (int)y + bounds.y,      // Poziția Y ajustată cu offset-ul bound-ului
                bounds.width,           // Lățimea zonei de coliziune
                bounds.height           // Înălțimea zonei de coliziune
        );
    }
    private void Despawn(){
        isSpawned=false;
    }
    public void TakeDamage(int damage) {
        if (damage < 0) {
            throw new NegativeDamageException(damage);
        }
        health -= damage; // Scade viața eroului
        if (health <= 0) {
             Despawn();// Metodă care gestionează moartea eroului
            State.SetState(refLink.GetGame().GetDeathState());
        }
    }
    /// Unlocj
    public void setWeaponUnlocked(int i){
        if(i<weaponUnlocked.length){
            weaponUnlocked[i] = true;
        }
    }
    public boolean[] getWeaponUnlocked(){
        return weaponUnlocked;
    }

    /// Reseteaza toate starile ce ar putea schimba actiunile eroului
    public void resetState() {
        isAttacking = false;
        isFalling = false;
        isJumping = false;
        facingRight = true;
        isSpawned = true;
        hasDealtDamage = false;
    }

    public Rectangle getAttackHitbox() {
        return new Rectangle((int) (attackHitbox.x-refLink.GetCamera().getXOffset()),attackHitbox.y,attackHitbox.width,attackHitbox.height);
    }

    public WeaponManager getWeaponManager(){
        return weapon;
    }
    public boolean isAttacking() {
        return isAttacking;
    }
    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }
    public void setScore(int points) {
        score = points;
    }
    public boolean isfacingRight() {return facingRight;}
    public boolean isHasDealtDamage() {return hasDealtDamage;}
    public void setHasDealtDamage(boolean hasDealtDamage) {this.hasDealtDamage = hasDealtDamage;}

}

