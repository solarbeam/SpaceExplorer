package lt.game.spaceexplorer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Base64Coder;
import java.lang.Math;

import static com.badlogic.gdx.math.MathUtils.cosDeg;
import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.math.MathUtils.sinDeg;

public class mainclass extends ApplicationAdapter {

    private Music bgm;
    private int savegameid=1;
    private int goal[] = {7500,4000,80,10000,10000,20000,30000,40000,5000,20000,10000,5000,15000,30000,5000,1,0,0,0,0};
    private Preferences prefs;
    private Stage s;
    private OrthographicCamera o;
    private BitmapFont font;
    private player p;
    private Sound moneySound;
    private Sound dieSound;
    private int currLevel = 0;
    private Texture background;
    private Texture title;
    private Texture stages;
    private Texture nextlevel;
    private Texture lvlcomp;
    private Texture nextlvlavail;
    private Texture mainmenu;
    private Texture tooltip;
    private Texture h2pb;
    private Texture exit;
    private Texture _1 ;
    private Texture _2;
    private Texture _3;
    private Texture lvl1b;
    private Texture lvl2b;
    private Texture lvl3b;
    private Texture lvl4b;
    private Texture lvl5b;
    private Texture lvl6b;
    private Texture lvl7b;
    private Texture lvl8b;
    private Texture lvl9b;
    private Texture lvl10b;
    private Texture lvl11b;
    private Texture lvl12b;
    private Texture lvl13b;
    private Texture lvl14b;
    private Texture lvl15b;
    private Texture lvl16b;
    private Texture lvl17b;
    private Texture lvl18b;
    private Texture lvl19b;
    private Texture lvl20b;
    private Texture play;
    private Texture beacon;
    private Texture retry;
    private Texture wall;
    private Texture money;
    private Texture player;
    private Texture die;



    public class title extends Actor{
        public void draw(Batch b, float alpha){
            b.draw(title,640/2-620/2,480-141);
        }
    }
    public class stagesb extends Actor{
        stagesb(){
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    if(p.score<goal[currLevel-1]){stages();remove();return true;}
                    if(currLevel==1){level2();remove();return true;}
                    if(currLevel==2){level3();remove();return true;}
                    if(currLevel==3){level4();remove();return true;}
                    if(currLevel==4){level5();remove();return true;}
                    if(currLevel==5){level6();remove();return true;}
                    if(currLevel==6){level7();remove();return true;}
                    if(currLevel==7){level8();remove();return true;}
                    if(currLevel==8){level9();remove();return true;}
                    if(currLevel==9){level10();remove();return true;}
                    if(currLevel==10){level11();remove();return true;}
                    if(currLevel==11){level12();remove();return true;}
                    if(currLevel==12){level13();remove();return true;}
                    if(currLevel==13){level14();remove();return true;}
                    if(currLevel==14){level15();remove();return true;}
                    if(currLevel==15){level16();remove();return true;}
                    if(currLevel==16){stages();remove();return true;}
                    if(currLevel==17){level18();remove();return true;}
                    if(currLevel==18){level19();remove();return true;}
                    if(currLevel==19){level20();remove();return true;}
                    if(currLevel==20){stages();remove();return true;}
                    remove();
                    return true;
                }
            });
            setBounds(640-200,15, 200, 50);
        }
        public void draw(Batch b, float alpha){
            if(currLevel==20 || p.score<goal[currLevel-1])b.draw(stages,640-200,15); else b.draw(nextlevel,640-200,15);
        }
    }
    public class lvlcomp extends Actor{
        boolean first=true;
        boolean sc = false;
        boolean gl = false;
        boolean nxt = false;
        Stage mainstage;
        lvlcomp(Stage m){
            this.mainstage = m;
        }
        public void draw(Batch b, float alpha){
            b.draw(lvlcomp,640/2-125,(480*3)/4-25);
            if(first){
                Timer.schedule(new Task(){
                    public void run() {
                        sc = true;
                    }}, 0.5f);
                Timer.schedule(new Task(){
                    public void run() {
                        gl = true;
                    }}, 1f);
                Timer.schedule(new Task(){
                    public void run() {
                        if(p.score>=goal[currLevel-1])nxt = true;
                    }}, 1.5f);
                Timer.schedule(new Task(){
                    public void run() {
                        mainstage.addActor(new stagesb());
                    }}, 2f);
            }
            if(sc)font.draw(b,"Score: " + Integer.toString(p.score), 640/2-125, (480*3)/4-100);
            if(gl)font.draw(b,"Goal: " + Integer.toString(goal[currLevel-1]), 640/2-125, (480*3)/4-125);
            if(nxt){b.draw(nextlvlavail,640/2-125,(480*3)/4-25-175);}
            first = false;
        }
    }
    public class mm extends Actor{
        mm(){
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    mainmenu();
                    remove();
                    return true;
                }
            });
            setBounds(640-230,15, 230, 50);
        }
        public void draw(Batch b, float alpha){
            b.draw(mainmenu,640-230,15);
        }
    }
    public class h2p extends Actor{
        h2p(){
        }
        public void draw(Batch b, float alpha){
            b.draw(tooltip,80,60);
        }
    }
    public class h2pb extends Actor{
        h2pb(){
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    howtoplay();
                    remove();
                    return true;
                }
            });
            setBounds(640/2-100,480/2-50-25-50, 200, 50);
        }
        public void draw(Batch b, float alpha){
            b.draw(h2pb,640/2-100,480/2-50-25-50);
        }
    }
    public class exit extends Actor{
        exit(){
            setBounds(640-50,480-40, 40, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    stages();
                    remove();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            b.draw(exit,640-50,480-40);
        }
    }
    public class countdown extends Actor{
        boolean __1 = false;
        boolean __2 = false;
        boolean __3 = true;
        countdown(){
            Timer.schedule(new Task() {
                public void run() {
                    __3 = false;
                    __2 = true;
                }
            },1f);
            Timer.schedule(new Task() {
                public void run() {
                    __2 = false;
                    __1 = true;
                }
            }, 2f);
            Timer.schedule(new Task() {
                public void run() {
                    __1 = false;
                }
            }, 3f);
        }
        public void draw(Batch b, float alpha){
            if(__1)
            {b.draw(_1,640/2-(125/2),480/2-(125/2));return;}
            if(__2)
            {b.draw(_2,640/2-(125/2),480/2-(125/2));return;}
            if(__3)
            {b.draw(_3,640/2-(125/2),480/2-(125/2));return;}
            remove();
            p.start();
        }
    }
    public class lvl1b extends Actor{
        lvl1b(){
            setBounds(10,480-50, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level1();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<1)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl1b,10,480-50);
            if(savegameid<1)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl2b extends Actor{
        lvl2b(){
            setBounds(85, 480 - 50, 75, 25);
            addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    level2();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<2)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl2b,85,480-50);
            if(savegameid<2)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl3b extends Actor{
        lvl3b(){
            setBounds(160,480-50, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level3();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<3)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl3b,160,480-50);
            if(savegameid<3)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl4b extends Actor{
        lvl4b(){
            setBounds(235,480-50, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level4();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<4)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl4b,235,480-50);
            if(savegameid<4)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl5b extends Actor{
        lvl5b(){
            setBounds(310,480-50, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level5();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<5)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl5b,310,480-50);
            if(savegameid<5)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl6b extends Actor{
        lvl6b(){
            setBounds(385,480-50, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level6();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<6)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl6b,385,480-50);
            if(savegameid<6)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl7b extends Actor{
        lvl7b(){
            setBounds(10,480-100, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level7();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<7)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl7b,10,480-100);
            if(savegameid<7)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl8b extends Actor{
        lvl8b(){
            setBounds(85,480-100, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level8();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<8)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl8b,85,480-100);
            if(savegameid<8)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl9b extends Actor{
        lvl9b(){
            setBounds(160,480-100, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level9();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<9)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl9b,160,480-100);
            if(savegameid<9)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl10b extends Actor{
        lvl10b(){
            setBounds(235,480-100, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level10();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<10)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl10b,235,480-100);
            if(savegameid<10)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl11b extends Actor{
        lvl11b(){
            setBounds(310,480-100, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level11();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<11)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl11b,310,480-100);
            if(savegameid<11)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl12b extends Actor{
        lvl12b(){
            setBounds(385,480-100, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level12();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<12)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl12b,385,480-100);
            if(savegameid<12)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl13b extends Actor{
        lvl13b(){
            setBounds(10,480-150, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level13();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<13)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl13b,10,480-150);
            if(savegameid<13)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl14b extends Actor{
        lvl14b(){
            setBounds(85,480-150, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level14();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<14)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl14b,85,480-150);
            if(savegameid<14)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl15b extends Actor{
        lvl15b(){
            setBounds(160,480-150, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level15();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<15)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl15b,160,480-150);
            if(savegameid<15)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl16b extends Actor{
        lvl16b(){
            setBounds(235,480-150, 210, 50);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level16();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<16)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl16b,235,480-150);
            if(savegameid<16)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl17b extends Actor{
        lvl17b(){
            setBounds(310,480-150, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level17();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<17)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl17b,310,480-150);
            if(savegameid<17)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl18b extends Actor{
        lvl18b(){
            setBounds(385,480-150, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level18();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<18)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl18b,385,480-150);
            if(savegameid<18)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl19b extends Actor{
        lvl19b(){
            setBounds(10,480-200, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level19();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<19)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl19b,10,480-200);
            if(savegameid<19)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl20b extends Actor{
        lvl20b(){
            setBounds(85,480-200, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level20();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<20)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(lvl20b,85,480-200);
            if(savegameid<20)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class play extends Actor{
        play(){
            setBounds(640/2-25-15, 480/2, 100, 50);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    stages();
                    remove();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            b.draw(play,640/2-25-15,480/2);
        }
    }
    public class beacon extends Actor{
        Rectangle Bounds = new Rectangle();
        beacon(float a, float b){
            this.setX(a);
            this.setY(b);
            this.addAction(Actions.forever(Actions.rotateBy(180, 2f)));
        }
        public void draw(Batch b, float alpha){
            if(this.getX()>650)return;
            Bounds.set(this.getX(), 480 - 25 - this.getY(), 25, 25);
            if(Intersector.overlaps(Bounds, p.playerBounds)){levelpass();}
            b.draw(beacon,this.getX(),480-25-this.getY(),13,13,25,
                    25,1, 1,this.getRotation(),0,0,
                    beacon.getWidth(),beacon.getHeight(),false,false);
        }
    }
    public class retry extends Actor{
        retry(){
            setBounds(640/2-50, 480/2, 100, 50);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    switch(currLevel){
                        case 1: level1();break;
                        case 2: level2();break;
                        case 3: level3();break;
                        case 4: level4();break;
                        case 5: level5();break;
                        case 6: level6();break;
                        case 7: level7();break;
                        case 8: level8();break;
                        case 9: level9();break;
                        case 10: level10();break;
                        case 11: level11();break;
                        case 12: level12();break;
                        case 13: level13();break;
                        case 14: level14();break;
                        case 15: level15();break;
                        case 16: level16();break;
                        case 17: level17();break;
                        case 18: level18();break;
                        case 19: level19();break;
                        case 20: level20();break;
                    }
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            b.draw(retry,640/2-50,480/2);
        }
    }
    public class inputActor extends Actor{
        inputActor(){
            setBounds(0, 0, 640, 480);
            addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    p.jump();
                    return true;
                }
            });
        }
    }
    public class wall extends Actor{
        Rectangle wallBounds = new Rectangle();
        wall(float a, float b){
            this.setX(a);
            this.setY(b);
        }
        public void draw(Batch b, float alpha){
            if(this.getX()>650)return;
            wallBounds.set(this.getX(), 480-25-this.getY(), 25, 25);
            if(Intersector.overlaps(wallBounds, p.playerBounds)){p.gotHit();dieSound.play(0.3f);remove();}
            if(this.getX()<-50){this.remove();}
            b.draw(wall,this.getX(),480-25-this.getY());
        }
    }
    public class fallingwall extends Actor{
        Rectangle wallBounds;
        boolean act=false;
        fallingwall(float a, float b){
            wallBounds = new Rectangle();
            this.setX(a);
            this.setY(b);
            this.addAction(Actions.forever(Actions.rotateBy(180, 0.5f)));
        }
        public void draw(Batch b, float alpha){
            if(this.getX()>650)return;
            if(this.getX()<25*8&&!act){act = true;
                this.addAction(Actions.moveBy(-50, 25*10, 4.8f));
                Timer.schedule(new Task(){
                    public void run(){
                        remove();
                    }
                }, 3f);
            }
            wallBounds.set(this.getX(), 480-25-this.getY(), 25, 25);
            if(Intersector.overlaps(wallBounds, p.playerBounds)){p.gotHit();dieSound.play(0.3f);remove();}
            if(this.getX()<-50){this.remove();}
            if(act)b.setColor(1f, 0f, 00f, 1f);
            b.draw(wall,this.getX(),480-25-this.getY(),13,13,25,
                    25,1, 1,this.getRotation(),0,0,
                    wall.getWidth(),wall.getHeight(),false,false);
            if(act)b.setColor(1f, 1f, 1f, 1f);
        }
    }
    public class flyingwall extends Actor{
        Rectangle wallBounds;
        boolean act=false;
        flyingwall(float a, float b){
            wallBounds = new Rectangle();
            this.setX(a);
            this.setY(b);
            this.addAction(Actions.forever(Actions.rotateBy(180, 0.5f)));
        }
        public void draw(Batch b, float alpha){
            if(this.getY()<70)remove();
            if(this.getX()>650)return;
            if(this.getX()<25*8&&!act){act = true;
                this.addAction(Actions.moveBy(-50, -25 * 10, 4.8f));
                Timer.schedule(new Task() {
                    public void run() {
                        remove();
                    }
                }, 3f);
            }
            wallBounds.set(this.getX(), 480-25-this.getY(), 25, 25);
            if(Intersector.overlaps(wallBounds, p.playerBounds)){p.gotHit();dieSound.play(0.3f);remove();}
            if(this.getX()<-50){this.remove();}
            if(act)b.setColor(1f, 0f, 0f, 1f);
            b.draw(wall,this.getX(),480-25-this.getY(),13,13,25,
                    25,1, 1,this.getRotation(),0,0,
                    wall.getWidth(),wall.getHeight(),false,false);
            if(act)b.setColor(1f, 1f, 1f, 1f);
        }
    }
    public class spinwall extends Actor{
        Rectangle wallBounds;
        spinwall(float a, float b){
            wallBounds = new Rectangle();
            this.setX(a);
            this.setY(b);
            this.addAction(Actions.forever(Actions.rotateBy(180, 1f)));
        }
        public void draw(Batch b, float alpha){
            if(this.getY()<70)remove();
            if(this.getX()>650)return;
            float rot = this.getRotation();
            while(rot>360){
                rot-=360;
            }
            wallBounds.set(this.getX()+25*cosDeg(rot), 480-25-this.getY()-25*sinDeg(rot), 25, 25);
            if(Intersector.overlaps(wallBounds, p.playerBounds)){p.gotHit();dieSound.play(0.3f);remove();}
            if(this.getX()<-50){this.remove();}
            b.setColor(1f, 0f, 0f, 1f);
            b.draw(wall,this.getX()+25*cosDeg(rot),480-25-this.getY()-25*sinDeg(rot),13,13,25,
                    25,1, 1,-this.getRotation(),0,0,
                    wall.getWidth(),wall.getHeight(),false,false);
            b.setColor(1f, 1f, 1f, 1f);
        }
    }
    public class ghostwall extends Actor{
        Rectangle wallBounds;
        ghostwall(float a, float b){
            wallBounds = new Rectangle();
            this.setX(a);
            this.setY(b);
            this.addAction(Actions.forever(Actions.rotateBy(180, 0.5f)));
            Timer.schedule(new Task() {
                public void run() {
                    addAction(Actions.forever(Actions.sequence(Actions.fadeOut(1f), Actions.delay(2.5f), Actions.fadeIn(0.5f))));
                }
            },new Float(Math.random()));

        }
        public void draw(Batch b, float alpha){
            if(this.getY()<70)remove();
            if(this.getX()>650)return;
            wallBounds.set(this.getX(), 480-25-this.getY(), 25, 25);
            if(Intersector.overlaps(wallBounds, p.playerBounds)){p.gotHit();dieSound.play(0.3f);remove();}
            if(this.getX()<-50){this.remove();}
            b.setColor(1,1,1,this.getColor().a);
            b.draw(wall,this.getX(),480-25-this.getY(),13,13,25,
                    25,1, 1,this.getRotation(),0,0,
                    wall.getWidth(),wall.getHeight(),false,false);
            b.setColor(1, 1, 1, 1);
        }
    }
    public class money extends Actor{
        Rectangle Bounds = new Rectangle();
        money(float a, float b){
            this.setX(a);
            this.setY(b);
        }
        public void draw(Batch b, float alpha){
            if(this.getX()>650)return;
            Bounds.set(this.getX(), 480-25-this.getY(), 25, 25);
            if(Intersector.overlaps(Bounds, p.playerBounds)){p.gotMoney();this.remove();moneySound.play(0.8f);}
            b.setColor(1f, 1f, 0f, 1f);
            b.draw(money,this.getX(),480-25-this.getY());
            b.setColor(1f, 1f, 1f, 1f);
        }
    }
    public class supermoney extends Actor{
        Rectangle Bounds = new Rectangle();
        supermoney(float a, float b){
            this.setX(a);
            this.setY(b);
        }
        public void draw(Batch b, float alpha){
            if(this.getX()>650)return;
            Bounds.set(this.getX(), 480-25-this.getY(), 25, 25);
            if(Intersector.overlaps(Bounds, p.playerBounds)){p.mult+=29;p.gotMoney();this.remove();moneySound.play(0.8f);}
            b.setColor(0, 0, 1f, 1f);
            b.draw(money,this.getX(),480-25-this.getY());
            b.setColor(1f, 1f, 1f, 1f);
        }
    }
    public class reader{
        char[] map;
        String s;
        FileHandle file;
        Stage mainstage;

        reader(Stage mstage,int lvl){
            this.mainstage = mstage;
            if(lvl==1)file = Gdx.files.internal("level1");
            if(lvl==2)file = Gdx.files.internal("level2");
            if(lvl==3)file = Gdx.files.internal("level3");
            if(lvl==4)file = Gdx.files.internal("level4");
            if(lvl==5)file = Gdx.files.internal("level5");
            if(lvl==6)file = Gdx.files.internal("level6");
            if(lvl==7)file = Gdx.files.internal("level7");
            if(lvl==8)file = Gdx.files.internal("level8");
            if(lvl==9)file = Gdx.files.internal("level9");
            if(lvl==10)file = Gdx.files.internal("level10");
            if(lvl==11)file = Gdx.files.internal("level11");
            if(lvl==12)file = Gdx.files.internal("level12");
            if(lvl==13)file = Gdx.files.internal("level13");
            if(lvl==14)file = Gdx.files.internal("level14");
            if(lvl==15)file = Gdx.files.internal("level15");
            if(lvl==16)file = Gdx.files.internal("level16");
            if(lvl==17)file = Gdx.files.internal("level17");
            if(lvl==18)file = Gdx.files.internal("level18");
            if(lvl==19)file = Gdx.files.internal("level19");
            if(lvl==20)file = Gdx.files.internal("level20");
            s = file.readString();
            map = s.toCharArray();
            int wx=0,wy=0;
            for(int i=0;i!=s.length();i++){
                if(map[i]=='1'){
                    wall w = new wall(wx,wy);
                    w.addAction(Actions.forever(Actions.moveBy(-25, 0, 0.25f)));
                    this.mainstage.addActor(w);
                    w = null;
                }
                if(map[i]=='2'){
                    beacon b = new beacon(wx,wy);
                    b.addAction(Actions.forever(Actions.moveBy(-25, 0, 0.25f)));
                    this.mainstage.addActor(b);
                    b = null;
                }
                if(map[i]=='3'){
                    money m = new money(wx,wy);
                    m.addAction(Actions.forever(Actions.moveBy(-25, 0, 0.25f)));
                    this.mainstage.addActor(m);
                    m = null;
                }
                if(map[i]=='4'){
                    fallingwall w = new fallingwall(wx,wy);
                    w.addAction(Actions.forever(Actions.moveBy(-25, 0, 0.25f)));
                    this.mainstage.addActor(w);
                    w = null;
                }
                if(map[i]=='5'){
                    flyingwall w = new flyingwall(wx,wy);
                    w.addAction(Actions.forever(Actions.moveBy(-25, 0, 0.25f)));
                    this.mainstage.addActor(w);
                    w = null;
                }
                if(map[i]=='6'){
                    spinwall w = new spinwall(wx,wy);
                    w.addAction(Actions.forever(Actions.moveBy(-25, 0, 0.25f)));
                    this.mainstage.addActor(w);
                    w = null;
                }
                if(map[i]=='7'){
                    supermoney m = new supermoney(wx,wy);
                    m.addAction(Actions.forever(Actions.moveBy(-25, 0, 0.25f)));
                    this.mainstage.addActor(m);
                    m = null;
                }
                if(map[i]=='8'){
                    ghostwall w = new ghostwall(wx,wy);
                    w.addAction(Actions.forever(Actions.moveBy(-25, 0, 0.25f)));
                    this.mainstage.addActor(w);
                    w = null;
                }
                wx+=25;
                if(map[i]=='\n'){wx=0;wy+=25;}

            }
        }

    }
    public class player extends Actor{
        boolean dead=false;
        private int score=0;
        private int mult;
        private long nextTime=0;
        boolean jumping = false;
        Rectangle playerBounds = new Rectangle();
        player(){
            jumping=true;
            setX(25*3);
            setY(480-25*15);
            Timer.schedule(new Task(){
                public void run(){
                    if(getY()<480-25*19)addAction(Actions.moveTo(25*3, 480-25*19));
                }
            }, 0f, 0.01f);

        }
        public void start(){
            addAction(Actions.forever(Actions.moveBy(0, -30,0.35f)));
            jumping=false;
        }

        public void gotMoney() {
            score+=10*mult;
            mult++;
            nextTime = System.currentTimeMillis() + 1500;
        }


        public void jump() {
            if(jumping)return;
            p.addAction(Actions.moveBy(0, 25, 0.5f));
        }

        public void gotHit() {
            dead = true;
            gameover();
        }


        public void draw(Batch b, float alpha){
            if(System.currentTimeMillis()>nextTime)mult=1;
            font.draw(b,"Score: " + Integer.toString(score), 25, 480-25);
            font.draw(b,"Multiplier: " + Integer.toString(mult)+"X", 25+100, 480-25);
            font.draw(b,"Goal: " + Integer.toString(goal[currLevel-1]), 25+200, 480-25);
            font.draw(b,"Level: " + Integer.toString(currLevel), 25+400, 480-25);
            playerBounds.set(this.getX(), this.getY(), 31, 17);
            if(dead)b.draw(die,this.getX()-32+31,this.getY()-32+17);  else b.draw(player,this.getX(),this.getY());

        }
    }
    public class background extends Actor{
        background(){
            this.setX(0);
            this.setY(0);
            addAction(Actions.forever(Actions.moveBy(-25, 0, 0.45f)));
        }
        public void draw(Batch b, float alpha){
            if(this.getX()<-2048)this.setX(0);
            b.draw(background,this.getX(),0);
        }

    }
    private void gameover(){
        Actor[] a = s.getActors().items;
        for(int i=0;i!=a.length;i++)
            if(a[i]!=null&& !(a[i] instanceof exit))a[i].clear();
        s.addActor(new retry());
    }
    private void levelpass(){
        s.clear();
        s.addActor(new background());
        s.addActor(new lvlcomp(s));
        if(p.score>=goal[currLevel-1] && currLevel==savegameid)savegameid+=1;
        save();
    }
    private void level1(){
        if(savegameid<1)return;
        currLevel = 1;
        s.clear();
        s.addActor(new background());
        p = new player();
        s.addActor(p);
        new reader(s,1);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level2(){
        if(savegameid<2)return;
        currLevel = 2;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,2);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level3(){
        if(savegameid<3)return;
        currLevel = 3;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,3);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level4(){
        if(savegameid<4)return;
        currLevel = 4;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,4);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level5(){
        if(savegameid<5)return;
        currLevel = 5;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,5);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level6(){
        if(savegameid<6)return;
        currLevel = 6;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,6);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level7(){
        if(savegameid<7)return;
        currLevel = 7;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,7);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level8(){
        if(savegameid<8)return;
        currLevel = 8;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,8);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level9(){
        if(savegameid<9)return;
        currLevel = 9;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,9);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level10(){
        if(savegameid<10)return;
        currLevel = 10;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,10);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level11(){
        if(savegameid<11)return;
        currLevel = 11;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,11);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level12(){
        if(savegameid<12)return;
        currLevel = 12;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,12);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level13(){
        if(savegameid<13)return;
        currLevel = 13;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,13);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level14(){
        if(savegameid<14)return;
        currLevel = 14;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,14);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level15(){
        if(savegameid<15)return;
        currLevel = 15;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,15);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level16(){
        if(savegameid<16)return;
        currLevel = 16;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,16);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level17(){
        if(savegameid<17)return;
        currLevel = 17;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,17);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level18(){
        if(savegameid<18)return;
        currLevel = 18;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,18);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level19(){
        if(savegameid<19)return;
        currLevel = 19;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,19);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void level20(){
        if(savegameid<20)return;
        currLevel = 20;
        s.clear();
        p = new player();
        s.addActor(new background());
        s.addActor(p);
        new reader(s,20);
        s.addActor(new countdown());
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void stages(){
        s.clear();
        s.addActor(new background());
        s.addActor(new lvl1b());
        s.addActor(new lvl2b());
        s.addActor(new lvl3b());
        s.addActor(new lvl4b());
        s.addActor(new lvl5b());
        s.addActor(new lvl6b());
        s.addActor(new lvl7b());
        s.addActor(new lvl8b());
        s.addActor(new lvl9b());
        s.addActor(new lvl10b());
        s.addActor(new lvl11b());
        s.addActor(new lvl12b());
        s.addActor(new lvl13b());
        s.addActor(new lvl14b());
        s.addActor(new lvl15b());
        s.addActor(new lvl16b());
        s.addActor(new mm());
    }
    private void howtoplay(){
        s.clear();
        s.addActor(new background());
        s.addActor(new h2p());
        s.addActor(new mm());
    }
    private void mainmenu(){
        s.clear();
        s.addActor(new background());
        s.addActor(new title());
        s.addActor(new play());
        s.addActor(new h2pb());
    }
    private void load(){
        savegameid = Integer.parseInt(Base64Coder.decodeString(prefs.getString("sav","MQ==")));
        savegameid = 1000;
    }
    private void save(){
        prefs.putString("sav", Base64Coder.encodeString(Integer.toString(savegameid)));
        prefs.flush();
    }
    public void create () {
        prefs = Gdx.app.getPreferences("vars");
        load();
        bgm = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        bgm.setLooping(true);
        bgm.play();
        bgm.setVolume(0.7f);
        moneySound = Gdx.audio.newSound(Gdx.files.internal("money.wav"));
        dieSound = Gdx.audio.newSound(Gdx.files.internal("die.wav"));
        font = new BitmapFont();
        font.setColor(255, 255, 255, 0);
        o = new OrthographicCamera(640,480);
        o.translate(640/2,480/2);
        s = new Stage();
        s.getViewport().setCamera(o);
        Gdx.input.setInputProcessor(s);
        background = new Texture(Gdx.files.internal("background.png"));
        title = new Texture(Gdx.files.internal("title.png"));
        stages = new Texture(Gdx.files.internal("stages.png"));
        nextlevel = new Texture(Gdx.files.internal("nextlevel.png"));
        lvlcomp = new Texture(Gdx.files.internal("lvlcomp.png"));
        nextlvlavail = new Texture(Gdx.files.internal("nextlvlavail.png"));
        mainmenu = new Texture(Gdx.files.internal("mainmenu.png"));
        tooltip = new Texture(Gdx.files.internal("tooltip.png"));
        h2pb = new Texture(Gdx.files.internal("h2p.png"));
        exit = new Texture(Gdx.files.internal("exit.png"));
        _1 = new Texture(Gdx.files.internal("1.png"));
        _2 = new Texture(Gdx.files.internal("2.png"));
        _3 = new Texture(Gdx.files.internal("3.png"));
        lvl1b = new Texture(Gdx.files.internal("lvl1b.png"));
        lvl2b = new Texture(Gdx.files.internal("lvl2b.png"));
        lvl3b = new Texture(Gdx.files.internal("lvl3b.png"));
        lvl4b = new Texture(Gdx.files.internal("lvl4b.png"));
        lvl5b = new Texture(Gdx.files.internal("lvl5b.png"));
        lvl6b = new Texture(Gdx.files.internal("lvl6b.png"));
        lvl7b = new Texture(Gdx.files.internal("lvl7b.png"));
        lvl8b = new Texture(Gdx.files.internal("lvl8b.png"));
        lvl9b = new Texture(Gdx.files.internal("lvl9b.png"));
        lvl10b = new Texture(Gdx.files.internal("lvl10b.png"));
        lvl11b = new Texture(Gdx.files.internal("lvl11b.png"));
        lvl12b = new Texture(Gdx.files.internal("lvl12b.png"));
        lvl13b = new Texture(Gdx.files.internal("lvl13b.png"));
        lvl14b = new Texture(Gdx.files.internal("lvl14b.png"));
        lvl15b = new Texture(Gdx.files.internal("lvl15b.png"));
        lvl16b = new Texture(Gdx.files.internal("fl.png"));
        lvl17b = new Texture(Gdx.files.internal("lvl17b.png"));
        lvl18b = new Texture(Gdx.files.internal("lvl18b.png"));
        lvl19b = new Texture(Gdx.files.internal("lvl19b.png"));
        lvl20b = new Texture(Gdx.files.internal("lvl20b.png"));
        play = new Texture(Gdx.files.internal("play.png"));
        beacon = new Texture(Gdx.files.internal("beacon.png"));
        retry = new Texture(Gdx.files.internal("retry.png"));
        wall = new Texture(Gdx.files.internal("wall.png"));
        money = new Texture(Gdx.files.internal("money.png"));
        player = new Texture(Gdx.files.internal("player.png"));
        die = new Texture(Gdx.files.internal("die.png"));
        Timer.schedule(new Task(){
            public void run(){
                System.gc();
            }
        },5f,1.5f);
        mainmenu();
    }
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        s.draw();
        s.act(Gdx.graphics.getDeltaTime());
    }
}
/*
Credits:
Background -  http://opengameart.org/sites/default/files/seamless%20space_0.PNG
Asteroids  -  http://opengameart.org/content/asteroid-generator-and-a-set-of-generated-asteroids
Rocket     -  http://opengameart.org/sites/default/files/onlyrocket.svg
Star       -  http://opengameart.org/sites/default/files/star.jpg
Coin Sound -  http://www.superflashbros.net/as3sfxr/
BGM        -  https://www.freesound.org/people/KeffyStay/sounds/238837/
Explosion  -  https://www.freesound.org/people/tommccann/sounds/235968/
*/