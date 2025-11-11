package PaooGame.Levels;


import PaooGame.RefLinks;
import PaooGame.States.State;

import java.awt.*;

public class LevelManager {
    private RefLinks refLink;
    private Level currentLevel;
    private Level nextLevel;
    private int levelId;
    private boolean loading;

    public LevelManager(RefLinks refLink,int level) {
        this.refLink=refLink;
        this.levelId=level;
        loadLevel(levelId);
    }
    protected Level createLevel(int levelId){
        switch (levelId){
            case 1: return new Level1(refLink);
            case 2: return new Level2(refLink);
            case 3: return new Level3(refLink);
            default: {
                System.out.println("Invalid level id: "+levelId);
                return null;
            }
        }
    }
    public void loadLevel(int levelId) {
        currentLevel=createLevel(levelId);
        if(currentLevel!=null){
            this.levelId=levelId;
            // actualizează și în RefLinks
            refLink.SetLevelManager(this);
        }

    }
    public synchronized void nextLevelAsync() {
        if (loading || levelId > 3) return;
        if(levelId==3) {
            State winstate=refLink.GetGame().GetWinState();
            refLink.GetGame().SetWinState(winstate);
            State.SetState(winstate);
            return;
        }
        loading = true;
        Thread loaderThread = new Thread(new LevelLoader(this, refLink, ++levelId), "LevelLoader");
        loaderThread.start();
    }
    public synchronized void finishLoading() {
        if (nextLevel != null) {
            currentLevel = nextLevel;
            nextLevel = null;
        }
    }
    public boolean isLoading() { return loading; }

    protected void setNextLevel(Level lvl) { this.nextLevel = lvl; }

    protected void setLoading(boolean l)    { this.loading   = l; }

    public void Update() {
        currentLevel.Update();
    }

    public void Draw(Graphics g) {
        currentLevel.Draw(g);
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
    public int GetLevelId() {
        return levelId;
    }
    public void SetLevelId(int levelId) {this.levelId = levelId;}
}
