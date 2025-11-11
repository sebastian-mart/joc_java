package PaooGame.Levels;

import PaooGame.Camera.Camera;
import PaooGame.Graphics.Assets;

import PaooGame.Items.Heart;
import PaooGame.NPC.Asterion;
import PaooGame.NPC.NPC;
import PaooGame.NPC.Sacal;

import PaooGame.Items.GoldenSwordPickup;
import PaooGame.Items.Item;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.List;

import java.util.ArrayList;
import java.util.Scanner;


public class Level2 extends Level{
    public Level2(RefLinks refLink) {
        super(refLink);
//        spawnX=64*2;
//        spawnY=64*10;
        spawnX=64*2;
        spawnY=64*10;
        items=new ArrayList<>();
        items.add(new GoldenSwordPickup(refLink,100*64,2*64));
        items.add(new Heart(refLink,74*64,6*64));
        items.add(new Heart(refLink,64*64,6*64));
        items.add(new Heart(refLink,148*64,6*64));
        itemsbool=new boolean[10];
        int index=0;
        for(Item i : items){
            itemsbool[index]=true;
            index++;
        }


        int w_tile = Tile.TILE_WIDTH;
        int h_tile = Tile.TILE_HEIGHT;
        npc_list = new ArrayList<>();
//        npc_list.add(new Asterion(refLink,152*w_tile,11*h_tile));
        npc_list.add(new Asterion(refLink,180*w_tile,11*h_tile));
        npc_list.add(new Sacal(refLink,w_tile*26,h_tile));
        npc_list.add(new Sacal(refLink,w_tile*61,h_tile*11));
        npc_list.add(new Sacal(refLink,w_tile*80,h_tile*11));
        npc_list.add(new Sacal(refLink,92*w_tile,8*h_tile));
        npc_list.add(new Sacal(refLink,182*w_tile,11*h_tile));


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
    }
  
    public void Draw(Graphics g) {
        Camera camera = refLink.GetCamera();
        float xOffset = camera.getXOffset();

        g.drawImage(Assets.backgroundLevel2, (int) -xOffset, 0, 64 * 200, refLink.GetHeight(), null);

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
        return Assets.level2[x][y];
    }
    public boolean isExitReached(int heroX, int heroY) {
//        System.out.println("heroX="+heroX/64+" heroY="+heroY/64);
        if(heroX>=64*195 && heroY>=64*7){
            refLink.GetHero().setWeaponUnlocked(3);
            return true;
        }
        return false;
    }
}
