// 1. Clasa loader
package PaooGame.Levels;

import PaooGame.RefLinks;

public class LevelLoader implements Runnable {
    private final RefLinks refLink;
    private final int newLevelId;
    private final LevelManager manager;

    public LevelLoader(LevelManager manager, RefLinks refLink, int newLevelId) {
        this.manager    = manager;
        this.refLink    = refLink;
        this.newLevelId = newLevelId;
    }

    @Override
    public void run() {
        /// Incarca nivelul in background
        Level next= manager.createLevel(newLevelId);
        //// Blocheaza modificarea starilor partajate
        synchronized (manager) {
            manager.setNextLevel(next);
            manager.setLoading(false);
        }
    }
}
