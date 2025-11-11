package PaooGame;

public class GameSave {
    /// Clasa GameSave: aceasta clasa retine detaliile importante din joc
    /// ce vor fi notate in baza de date la fiecare salvare
    private String dbName;//Numele jucatorului
    private int dbHeroX;//pozitia jucatorului pe axa x
    private int dbHeroY;//pozitia jucatorului pe axa y
    private int dbHealth;
    private int currentWeaponIndex;//arma pe care o manuieste eroul
    private boolean []dbunlockedWeapons;//vectorul ce retine armele deblocate
    private int dblevel;
    private boolean[] dbcollectedItems;//iteme colectate
    private boolean[] dbNPCs;//npc-uri
    private int dbhighScore;
    public GameSave(String dbName, int dbHeroX, int dbHeroY, int dbHealth,int currentWeaponIndex,boolean []dbunlockedWeapons,
                    int dblevel,boolean[] dbcollectedItems,boolean[] dbDefeatedNPCs,int dbhighScore) {
        this.dbName = dbName;
        this.dbHeroX = dbHeroX;
        this.dbHeroY = dbHeroY;
        this.dbHealth = dbHealth;
        this.currentWeaponIndex=currentWeaponIndex;
        this.dbunlockedWeapons = dbunlockedWeapons;
        this.dblevel= dblevel;

        this.dbcollectedItems = dbcollectedItems;
        this.dbNPCs = dbDefeatedNPCs;
        this.dbhighScore = dbhighScore;

    }
    public String getDbName() {return dbName;}
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public int getDbHeroX() {return dbHeroX;}
    public void setDbHeroX(int dbHeroX) {this.dbHeroX = dbHeroX;}

    public int getDbHeroY() {return dbHeroY;}
    public void setDbHeroY(int dbHeroY) {
        this.dbHeroY = dbHeroY;
    }

    public int getDbHealth() {return dbHealth;}
    public void setDbHealth(int dbHealth) {this.dbHealth = dbHealth;}

    public boolean[] getDbunlockedWeapons() {
        return dbunlockedWeapons;
    }
    public void setDbunlockedWeapons(boolean[] dbunlockedWeapons) {this.dbunlockedWeapons = dbunlockedWeapons;}

    public int getDblevel() {return dblevel;}
    public void setDblevel(int dblevel) {this.dblevel = dblevel;}

    public int getCurrentWeaponIndex() {
        return currentWeaponIndex;
    }
    public void setCurrentWeaponIndex(int currentWeaponIndex) {
        this.currentWeaponIndex = currentWeaponIndex;
    }

    public boolean[] getDbcollectedItems() {
        return dbcollectedItems;
    }
    public void setDbcollectedItems(boolean[] dbcollectedItems) {this.dbcollectedItems = dbcollectedItems;}

    public boolean[] getDbNPCs() { return dbNPCs; }
    public void setDbNPCs(boolean[] NPCs){this.dbNPCs = NPCs;}

    public int getDbhighScore() {return dbhighScore;}
    public void setDbhighScore(int dbhighScore) {this.dbhighScore = dbhighScore;}

}
