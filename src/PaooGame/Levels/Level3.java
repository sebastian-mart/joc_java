package PaooGame.Levels;

import PaooGame.Camera.Camera;
import PaooGame.Graphics.Assets;

import PaooGame.Items.Heart;
import PaooGame.Items.Matei;
import PaooGame.NPC.NPC;
import PaooGame.NPC.Oblivion;
import PaooGame.NPC.Sacal;

import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.States.State;
import PaooGame.States.WinState;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

public class Level3 extends Level{
    public Level3(RefLinks refLink) {
        super(refLink);
        spawnX=64*3;
        spawnY=64*10;

        int w_tile = Tile.TILE_WIDTH;
        int h_tile = Tile.TILE_HEIGHT;

        items = new ArrayList<>();
        items.add(new Heart(refLink,78*64,9*64));
        items.add(new Heart(refLink,93*64,13*64));
        items.add(new Matei(refLink,195*64,12*64));

        npc_list = new ArrayList<>();
//        npc_list.add(new Oblivion(refLink,w_tile*96,h_tile*12));
        npc_list.add(new Oblivion(refLink,w_tile*182,11*h_tile));
//        npc_list.add(new Sacal(refLink,w_tile*23,h_tile*8));
//        npc_list.add(new Sacal(refLink,w_tile*49,h_tile*4));
        npc_list.add(new Sacal(refLink,w_tile*61,h_tile*12));
        npc_list.add(new Sacal(refLink,w_tile*63,h_tile*4));
        npc_list.add(new Sacal(refLink,155*w_tile,11*h_tile));


        for(NPC npc:npc_list)
            npc.Spawn();
    }

    public void Update(){
        super.Update();
        for(NPC npc:npc_list) {
            if(npc!=null) {
                npc.Update();
                if(refLink.GetHero().isAttacking() && !refLink.GetHero().isHasDealtDamage()&& refLink.GetHero().getAttackHitbox().intersects(npc.GetHitBox())){
                    npc.TakeDamage((int)refLink.GetHero().getWeaponManager().getCurrentWeaponDamage());
                    refLink.GetHero().setHasDealtDamage(true);
                }
            }
        }
        if(!npcbool[0]){
            Tile.TurnToNotSolid();
        }
    }
    public void Draw(Graphics g) {
        Camera camera = refLink.GetCamera();
        float xOffset = camera.getXOffset();

        g.drawImage(Assets.backgroundLevel3, (int) -xOffset, 0, 64 * 200, refLink.GetHeight(), null);

        //g.drawImage(Assets.backgroundLevel2,0,0,refLink.GetWidth(), refLink.GetHeight(), null);

        //g.drawImage(Assets.backgroundLevel3,(int)-xOffset, 0, 64*200, refLink.GetHeight(), null);
        int startX = (int) (xOffset / Tile.TILE_WIDTH);
        int endX = (int) ((xOffset + refLink.GetWidth()) / Tile.TILE_WIDTH) + 2;

        startX = Math.max(0, startX);
        endX = Math.min(width, endX);

        for (int y = 0; y < height; y++) {
            for (int x = startX; x < endX; x++) {
                Tile tile = GetTile(x, y);
                if (tile != null) {
                    int drawX = (int) (x * Tile.TILE_WIDTH - xOffset);
                    int drawY = y * Tile.TILE_HEIGHT;
                    tile.Draw(g, drawX, drawY);
                }
            }
        }

        for (NPC npc : npc_list) {
            if (npc != null) {
                npc.Draw(g);
            }

        }
        for (Item item : items) {
            item.Draw(g);
        }
    }
    @Override
    protected int MiddleEastMap(int x ,int y){
        return Assets.level3[x][y];
    }

    @Override
    public boolean isExitReached(int heroX, int heroY) {
        System.out.println("heroX="+heroX/64+" heroY="+heroY/64);
        if(heroX>=64*195 && heroY>=64*7){
            State winState = new WinState(refLink);
            refLink.GetGame().SetWinState(winState);
            return true;
        }
        return false;
    }
}
