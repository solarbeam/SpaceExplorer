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

import static com.badlogic.gdx.math.MathUtils.cosDeg;
import static com.badlogic.gdx.math.MathUtils.sinDeg;

public class mainclass extends ApplicationAdapter {

    private Music bgm;
    private int savegameid=1;
    private int goal[] = {500,3000,1700,500,30000,500,3000,1700,500,500,500,3000,1700,500,500,500,3000,1700,500,500};
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
                    if(currLevel==16){level17();remove();return true;}
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
        boolean nxt= false;
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
        Texture t;
        h2pb(){
            t = new Texture(Gdx.files.internal("h2p.png"));
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
            b.draw(t,640/2-100,480/2-50-25-50);
        }
    }
    public class exit extends Actor{
        Texture t;
        exit(){
            t = new Texture(Gdx.files.internal("exit.png"));
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
            b.draw(t,640-50,480-40);
        }
    }
    public class countdown extends Actor{
        long nextTime;
        Texture t1;
        Texture t2;
        Texture t3;
        countdown(long time){
            this.nextTime = time+3000;
            t1 = new Texture(Gdx.files.internal("1.png"));
            t2 = new Texture(Gdx.files.internal("2.png"));
            t3 = new Texture(Gdx.files.internal("3.png"));
        }
        public void draw(Batch b, float alpha){
            if(nextTime-System.currentTimeMillis()>0&&nextTime-System.currentTimeMillis()<1000)
            {b.draw(t1,640/2-(125/2),480/2-(125/2));return;}
            if(nextTime-System.currentTimeMillis()>1000&&nextTime-System.currentTimeMillis()<2000)
            {b.draw(t2,640/2-(125/2),480/2-(125/2));return;}
            if(nextTime-System.currentTimeMillis()>2000&&nextTime-System.currentTimeMillis()<3000)
            {b.draw(t3,640/2-(125/2),480/2-(125/2));return;}
            remove();
            p.start();
        }
    }
    public class lvl1b extends Actor{
        Texture r;
        lvl1b(){
            r = new Texture(Gdx.files.internal("lvl1b.png"));
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
            b.draw(r,10,480-50);
            if(savegameid<1)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl2b extends Actor{
        Texture r;
        lvl2b(){
            r = new Texture(Gdx.files.internal("lvl2b.png"));
            setBounds(85,480-50, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level2();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<2)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(r,85,480-50);
            if(savegameid<2)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl3b extends Actor{
        Texture r;
        lvl3b(){
            r = new Texture(Gdx.files.internal("lvl3b.png"));
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
            b.draw(r,160,480-50);
            if(savegameid<3)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl4b extends Actor{
        Texture r;
        lvl4b(){
            r = new Texture(Gdx.files.internal("lvl4b.png"));
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
            b.draw(r,235,480-50);
            if(savegameid<4)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl5b extends Actor{
        Texture r;
        lvl5b(){
            r = new Texture(Gdx.files.internal("lvl5b.png"));
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
            b.draw(r,310,480-50);
            if(savegameid<5)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl6b extends Actor{
        Texture r;
        lvl6b(){
            r = new Texture(Gdx.files.internal("lvl6b.png"));
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
            b.draw(r,385,480-50);
            if(savegameid<6)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl7b extends Actor{
        Texture r;
        lvl7b(){
            r = new Texture(Gdx.files.internal("lvl7b.png"));
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
            b.draw(r,10,480-100);
            if(savegameid<7)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl8b extends Actor{
        Texture r;
        lvl8b(){
            r = new Texture(Gdx.files.internal("lvl8b.png"));
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
            b.draw(r,85,480-100);
            if(savegameid<8)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl9b extends Actor{
        Texture r;
        lvl9b(){
            r = new Texture(Gdx.files.internal("lvl9b.png"));
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
            b.draw(r,160,480-100);
            if(savegameid<9)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl10b extends Actor{
        Texture r;
        lvl10b(){
            r = new Texture(Gdx.files.internal("lvl10b.png"));
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
            b.draw(r,235,480-100);
            if(savegameid<10)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl11b extends Actor{
        Texture r;
        lvl11b(){
            r = new Texture(Gdx.files.internal("lvl11b.png"));
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
            b.draw(r,310,480-100);
            if(savegameid<11)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl12b extends Actor{
        Texture r;
        lvl12b(){
            r = new Texture(Gdx.files.internal("lvl12b.png"));
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
            b.draw(r,385,480-100);
            if(savegameid<12)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl13b extends Actor{
        Texture r;
        lvl13b(){
            r = new Texture(Gdx.files.internal("lvl13b.png"));
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
            b.draw(r,10,480-150);
            if(savegameid<13)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl14b extends Actor{
        Texture r;
        lvl14b(){
            r = new Texture(Gdx.files.internal("lvl14b.png"));
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
            b.draw(r,85,480-150);
            if(savegameid<14)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl15b extends Actor{
        Texture r;
        lvl15b(){
            r = new Texture(Gdx.files.internal("lvl15b.png"));
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
            b.draw(r,160,480-150);
            if(savegameid<15)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl16b extends Actor{
        Texture r;
        lvl16b(){
            r = new Texture(Gdx.files.internal("lvl16b.png"));
            setBounds(235,480-150, 75, 25);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                    level16();
                    return true;
                }
            });
        }
        public void draw(Batch b, float alpha){
            if(savegameid<16)b.setColor(0.4f, 0.4f,0.4f,1f);
            b.draw(r,235,480-150);
            if(savegameid<16)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl17b extends Actor{
        Texture r;
        lvl17b(){
            r = new Texture(Gdx.files.internal("lvl17b.png"));
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
            b.draw(r,310,480-150);
            if(savegameid<17)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl18b extends Actor{
        Texture r;
        lvl18b(){
            r = new Texture(Gdx.files.internal("lvl18b.png"));
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
            b.draw(r,385,480-150);
            if(savegameid<18)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl19b extends Actor{
        Texture r;
        lvl19b(){
            r = new Texture(Gdx.files.internal("lvl19b.png"));
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
            b.draw(r,10,480-200);
            if(savegameid<19)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class lvl20b extends Actor{
        Texture r;
        lvl20b(){
            r = new Texture(Gdx.files.internal("lvl20b.png"));
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
            b.draw(r,85,480-200);
            if(savegameid<20)b.setColor(1f, 1f,1f,1f);
        }
    }
    public class play extends Actor{
        Texture r;
        play(){
            r = new Texture(Gdx.files.internal("play.png"));
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
            b.draw(r,640/2-25-15,480/2);
        }
    }
    public class beacon extends Actor{
        Rectangle Bounds = new Rectangle();
        Texture Texture;
        beacon(float a, float b){
            this.setX(a);
            this.setY(b);
            Texture = new Texture(Gdx.files.internal("beacon.png"));
            this.addAction(Actions.forever(Actions.rotateBy(180, 2f)));
        }
        public void draw(Batch b, float alpha){
            if(this.getX()>650)return;
            Bounds.set(this.getX(), 480-25-this.getY(), 25, 25);
            if(Intersector.overlaps(Bounds, p.playerBounds)){levelpass();}
            b.draw(Texture,this.getX(),480-25-this.getY(),13,13,25,
                    25,1, 1,this.getRotation(),0,0,
                    Texture.getWidth(),Texture.getHeight(),false,false);
        }
    }
    public class retry extends Actor{
        Texture r;
        retry(){
            r = new Texture(Gdx.files.internal("retry.png"));
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
            b.draw(r,640/2-50,480/2);
        }
    }
    public class inputActor extends Actor{
        inputActor(){
            setBounds(0, 0, 640, 480);
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    p.jump();
                    return true;
                }
            });
        }
    }
    public class wall extends Actor{

        Rectangle wallBounds = new Rectangle();
        Texture wallTexture;
        wall(float a, float b){
            this.setX(a);
            this.setY(b);
            wallTexture = new Texture(Gdx.files.internal("wall.png"));
        }
        public void draw(Batch b, float alpha){
            if(this.getParent()==null){
                wallTexture.dispose();
            }
            if(this.getX()>650)return;
            wallBounds.set(this.getX(), 480-25-this.getY(), 25, 25);
            if(Intersector.overlaps(wallBounds, p.playerBounds)){p.gotHit();dieSound.play(0.3f);remove();}
            if(this.getX()<-50){this.remove();}
            b.draw(wallTexture,this.getX(),480-25-this.getY());
        }
    }
    public class fallingwall extends Actor{
        Texture texture;
        Rectangle wallBounds;
        boolean act=false;
        fallingwall(float a, float b){
            wallBounds = new Rectangle();
            this.setX(a);
            this.setY(b);
            texture = new Texture(Gdx.files.internal("wall.png"));
            this.addAction(Actions.forever(Actions.rotateBy(180, 0.5f)));
        }
        public void draw(Batch b, float alpha){
            if(this.getX()>650)return;
            if(this.getX()<25*7&&!act){act = true;
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
            b.draw(texture,this.getX(),480-25-this.getY(),13,13,25,
                    25,1, 1,this.getRotation(),0,0,
                    texture.getWidth(),texture.getHeight(),false,false);
            if(act)b.setColor(1f, 1f, 1f, 1f);
        }
    }
    public class flyingwall extends Actor{
        Texture texture;
        Rectangle wallBounds;
        boolean act=false;
        flyingwall(float a, float b){
            wallBounds = new Rectangle();
            this.setX(a);
            this.setY(b);
            texture = new Texture(Gdx.files.internal("wall.png"));
            this.addAction(Actions.forever(Actions.rotateBy(180, 0.5f)));
        }
        public void draw(Batch b, float alpha){
            if(this.getY()<70)remove();
            if(this.getX()>650)return;
            if(this.getX()<25*7&&!act){act = true;
                this.addAction(Actions.moveBy(-50, -25*10, 4.8f));
                Timer.schedule(new Task(){
                    public void run(){
                        remove();
                    }
                }, 3f);
            }
            wallBounds.set(this.getX(), 480-25-this.getY(), 25, 25);
            if(Intersector.overlaps(wallBounds, p.playerBounds)){p.gotHit();dieSound.play(0.3f);remove();}
            if(this.getX()<-50){this.remove();}
            if(act)b.setColor(1f, 0f, 0f, 1f);
            b.draw(texture,this.getX(),480-25-this.getY(),13,13,25,
                    25,1, 1,this.getRotation(),0,0,
                    texture.getWidth(),texture.getHeight(),false,false);
            if(act)b.setColor(1f, 1f, 1f, 1f);
        }
    }
    public class spinwall extends Actor{
        Texture texture;
        Rectangle wallBounds;
        spinwall(float a, float b){
            wallBounds = new Rectangle();
            this.setX(a);
            this.setY(b);
            texture = new Texture(Gdx.files.internal("wall.png"));
            this.addAction(Actions.forever(Actions.rotateBy(180, 0.5f)));
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
            b.draw(texture,this.getX()+25*cosDeg(rot),480-25-this.getY()-25*sinDeg(rot),13,13,25,
                    25,1, 1,-this.getRotation(),0,0,
                    texture.getWidth(),texture.getHeight(),false,false);
            b.setColor(1f, 1f, 1f, 1f);
        }
    }
    public class money extends Actor{

        Rectangle Bounds = new Rectangle();
        Texture Texture;
        money(float a, float b){
            this.setX(a);
            this.setY(b);
            Texture = new Texture(Gdx.files.internal("money.png"));
        }
        public void draw(Batch b, float alpha){
            if(this.getX()>650)return;
            Bounds.set(this.getX(), 480-25-this.getY(), 25, 25);
            if(Intersector.overlaps(Bounds, p.playerBounds)){p.gotMoney();this.remove();moneySound.play(0.8f);}
            b.setColor(1f, 1f, 0f, 1f);
            b.draw(Texture,this.getX(),480-25-this.getY());
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
                wx+=25;
                if(map[i]=='\n'){wx=0;wy+=25;}

            }
        }

    }
    public class player extends Actor{

        private int score=0;
        private int mult;
        private long nextTime=0;
        boolean jumping = false;
        Rectangle playerBounds = new Rectangle();
        Texture playerTexture;
        player(){
            jumping=true;
            setX(25*3);
            setY(480-25*15);
            Timer.schedule(new Task(){
                public void run(){
                    if(getY()<480-25*19)addAction(Actions.moveTo(25*3, 480-25*19));
                }
            }, 0f, 0.01f);
            playerTexture = new Texture(Gdx.files.internal("player.png"));

        }
        public void start(){
            addAction(Actions.forever(Actions.moveBy(0, -30,0.5f)));
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
            gameover();
        }


        public void draw(Batch b, float alpha){
            if(System.currentTimeMillis()>nextTime)mult=1;
            font.draw(b,"Score: " + Integer.toString(score), 25, 480-25);
            font.draw(b,"Multiplier: " + Integer.toString(mult)+"X", 25+100, 480-25);
            font.draw(b,"Goal: " + Integer.toString(goal[currLevel-1]), 25+200, 480-25);
            playerBounds.set(this.getX(), this.getY(), 31, 17);
            b.draw(playerTexture,this.getX(),this.getY());

        }
    }
    public class background extends Actor{
        Texture t;
        background(){
            this.setX(0);
            this.setY(0);
            //t = new Texture(Gdx.files.internal("background.png"));
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

        s.addActor(new countdown(System.currentTimeMillis()));
        p = new player();
        s.addActor(p);
        new reader(s,1);
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
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
        s.addActor(new countdown(System.currentTimeMillis()));
        s.addActor(new inputActor());
        s.addActor(new exit());
    }
    private void stages(){
        //s.clear();
        refresh();
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
        s.addActor(new lvl17b());
        s.addActor(new lvl18b());
        s.addActor(new lvl19b());
        s.addActor(new lvl20b());
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
        //savegameid = 1000;
    }
    private void save(){
        prefs.putString("sav", Base64Coder.encodeString(Integer.toString(savegameid)));
        prefs.flush();
    }
    private void refresh(){
        s.dispose();
        s = new Stage();
        s.getViewport().setCamera(o);
        Gdx.input.setInputProcessor(s);
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
        h2pb = new Texture(Gdx.files.internal("h2pb.png"));
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