package PaooGame.States;

import PaooGame.Camera.Camera;
import PaooGame.Database;
import PaooGame.GameSave;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import PaooGame.Levels.LevelManager;

import java.awt.*;

public class DeathState extends State{
    private LevelManager levelManager;
    private Hero hero;

    public DeathState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        this.levelManager = refLink.GetLevelManager();
        this.hero = refLink.GetHero();
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
     */
    @Override
    public void Update()
    {
        if(refLink.GetKeyManager().r) {
            restartGame();
            State.SetState(refLink.GetGame().GetPlayState());
        }
        else if(refLink.GetKeyManager().m) {
            refLink.GetGame().SetPlayState(new PlayState(refLink));
            State.SetState(new MenuState(refLink));
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        // Fundal semi-transparent
        g.setColor(new Color(Color.black.getRGB()));
        g.fillRect(0, 0, refLink.GetWidth(), refLink.GetHeight());

        // Text Game Over
        g.setColor(Color.RED);
        g.setFont(new Font("Consolas", Font.BOLD, 64));
        String text = "GAME OVER";
        int x = (refLink.GetWidth() - g.getFontMetrics().stringWidth(text)) / 2;
        int y = refLink.GetHeight()/2 - 50;
        g.drawString(text, x, y);

        // Instructiuni
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 24));
        String restartText = "Press R to load the last save";
        x = (refLink.GetWidth() - g.getFontMetrics().stringWidth(restartText)) / 2;
        y += 80;
        g.drawString(restartText, x, y);

        String menuText = "Press M for Main Menu";
        x = (refLink.GetWidth() - g.getFontMetrics().stringWidth(menuText)) / 2;
        y += 40;
        g.drawString(menuText, x, y);
    }
    private void restartGame() {
        hero.resetState();
        // Incarcare ultima salvare
        GameSave loadedSave = new Database().readTable();
        if(loadedSave != null) {
            refLink.SetGameSave(loadedSave);
            State playState = refLink.GetGame().GetPlayState();
            ((PlayState)playState).applyGameSave();
            State.SetState(playState);

        }
    }

    @Override
    public void InitHandlers() {

    }
}
