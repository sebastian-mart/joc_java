package PaooGame.States;

import PaooGame.Database;
import PaooGame.GameSave;
import PaooGame.Items.Hero;
import PaooGame.Levels.Level;
import PaooGame.Levels.LevelManager;
import PaooGame.RefLinks;
import PaooGame.Camera.Camera;
import PaooGame.HUD.HUD;

import java.awt.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State {
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Camera camera;
    private LevelManager levelManager;
    private HUD hud;

    private enum Stage {PLAY, LOADING,NOTSAVED}

    private Stage stage = Stage.PLAY;
//    private HealthBar healthBar;

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza
        super(refLink);
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului
        levelManager = new LevelManager(refLink,1);
        refLink.SetLevelManager(levelManager);
        ///Construieste eroul
        hero = new Hero(refLink, levelManager.getCurrentLevel().GetSpawnX(), levelManager.getCurrentLevel().GetSpawnY());
        refLink.SetHero(hero);
        /// Initializeaza Camera jocului
        camera = new Camera(refLink.GetWidth(), levelManager.getCurrentLevel().getWidthPixels());
        /// Referinta catre camera jocului, aceasta este pusa in reflinks pentru a putea fi accesata de alte elemente ale jocului
        refLink.SetCamera(camera);

        hud = new HUD(hero);

        InitHandlers();
    }

    @Override
    public void InitHandlers(){
        Canvas canvas = refLink.GetGame().GetGameWindow().GetCanvas();
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int width=40,height=40;
                int x=30,y=30;
                int coordX = e.getX(),coordY = e.getY();
                if(coordX>=x && coordX<=x+width && coordY>=y && coordY<=y+height){
                    State pauseState = refLink.GetGame().GetPauseState();
                    canvas.removeMouseListener(this);
                    State.SetState(pauseState);

                }
            }
        });
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override

    public void Update() {
        switch (stage) {
            case NOTSAVED:
                boolean []buff={true,true,true,true,true,true,true,true,true,true};
                refLink.GetLevelManager().getCurrentLevel().setItemsbool(buff);
                refLink.GetLevelManager().getCurrentLevel().setNpcbool(buff);
                createGameSave();
                new Database().insertTable(refLink.GetGameSave());
                stage = Stage.PLAY;
                break;
            case PLAY:
                if(State.GetState() instanceof PauseState)
                    return;
                hero.Update();
                levelManager.Update();
                camera.update(hero.GetX());

                if (refLink.GetLevelManager().getCurrentLevel().isExitReached((int) hero.GetX(), (int) hero.GetY())
                        && refLink.GetLevelManager().getCurrentLevel().isBossBeat()) {
                    stage = Stage.LOADING;
                    levelManager.nextLevelAsync();
                }
                break;
            case LOADING:
                if (!levelManager.isLoading()) {
                    // swap efectiv
                    levelManager.finishLoading();
                    hero.SetX(levelManager.getCurrentLevel().GetSpawnX());
                    hero.SetY(levelManager.getCurrentLevel().GetSpawnY());
                    camera = new Camera(refLink.GetWidth(), levelManager.getCurrentLevel().getWidthPixels());
                    refLink.SetCamera(camera);
                    stage = Stage.NOTSAVED;
                }
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        switch (stage) {
            case PLAY : levelManager.Draw(g);
            hero.Draw(g);
            hud.Draw(g);
                /// generam un buton pt a putea folosi meniul de pauza in timpul jocului
                Image pause_image=null;
                try{
                    pause_image = ImageIO.read(new File("res/menu/arrow_decorative_e_small.png"));
                    pause_image = pause_image.getScaledInstance(40,40,0);
                }catch (IOException e){
                    System.out.print("Fisier negasit\n");
                }

                g.drawImage(pause_image,30,30,null);
            break;
            case LOADING:
                g.setColor(Color.BLACK);
                g.fillRect(0,0,refLink.GetWidth(),refLink.GetHeight());
                g.setColor(Color.WHITE);
                g.setFont(new Font("Consolas",Font.PLAIN,30));
                g.drawString("  Loading...",64*13,64*13);
                break;
        }

    }

    public Hero GetHero(){return hero;}

    public void createGameSave() {
        Hero hero = refLink.GetHero();
        GameSave save = new GameSave(
                "PLY",  // Atat va fi deocamdata
                (int)hero.GetX(),
                (int)hero.GetY(),
                hero.GetHealth(),
                hero.getWeaponManager().getCurrentWeaponIndex(),
                hero.getWeaponUnlocked(),
                refLink.GetLevelManager().GetLevelId(),
                refLink.GetLevelManager().getCurrentLevel().getItemsbool(),
                refLink.GetLevelManager().getCurrentLevel().getNpcbool(),
                hero.getScore()
        );
        refLink.SetGameSave(save);
    }

    public void applyGameSave() {
        GameSave save = refLink.GetGameSave();
        if(save != null) {
            Hero hero = refLink.GetHero();
            hero.SetX(save.getDbHeroX());
            hero.SetY(save.getDbHeroY());
            hero.SetHealth(save.getDbHealth());
            hero.getWeaponManager().switchWeapon(save.getCurrentWeaponIndex());
            for(int i=0;i<4;i++){
//                System.out.println(save.getDbunlockedWeapons()[i]);
                if (save.getDbunlockedWeapons()[i]==true){
                    hero.setWeaponUnlocked(i);
                }
            }
            hero.setScore(save.getDbhighScore());
            refLink.GetLevelManager().loadLevel(save.getDblevel());
//            refLink.GetLevelManager().getCurrentLevel().setItemsbool(save.getDbcollectedItems());
//            refLink.GetLevelManager().getCurrentLevel().setNpcbool(save.getDbNPCs());
            // Restaurare stare nivel
            refLink.GetLevelManager().loadLevel(save.getDblevel());
            Level currentLevel = refLink.GetLevelManager().getCurrentLevel();
            refLink.SetHero(hero);




            // Aplicare vectori salvați
            boolean[] savedItems = save.getDbcollectedItems();
            boolean[] savedNPCs = save.getDbNPCs();

            // Asigură dimensiunea corectă
            int itemsSize = Math.min(savedItems.length, currentLevel.getItems().size());
//            System.arraycopy(savedItems, 0, currentLevel.getItemsbool(), 0, itemsSize);

            int npcSize = Math.min(savedNPCs.length, currentLevel.getNPC().size());
//            System.arraycopy(savedNPCs, 0, currentLevel.getNpcbool(), 0, npcSize);

            // Actualizează starea itemelor și NPC-urilor
            for(int i=0; i<itemsSize; i++) {
                    currentLevel.setItemsbool(savedItems[i],i);
            }

            for(int i=0; i<npcSize; i++) {
                currentLevel.setNpcbool(savedNPCs[i],i);
                if(!savedNPCs[i])
                {
                    currentLevel.getNPC().get(i).Despawn();
                }
            }
        }
    }
}
