package com.example.thales.tangramprovacomp.tangram;

import com.example.thales.tangramprovacomp.buffer.Buffer;

import java.lang.reflect.Array;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Thales on 25/09/2017.
 */

public abstract class Geometria {
    private FloatBuffer vrVetBuffer =null;

    private float PosX=0,PoxY=0,MovX=0,MovY=0;
    private float angulo=0;
    private float cor[]={0,0,0};
    private float escala[]={1,1,1};
    private boolean inverter;

    public boolean isInverter() {
        return inverter;
    }

    public void setInverter(boolean inverter) {
        this.inverter = inverter;
    }

    public void setAngulo (float angulo)
    {
        this.angulo=angulo;
    }

    public float getAngulo ()
    {
        return  angulo;
    }
    public void setCor (float cor[])
    {
        this.cor=cor;
    }
    public float []getCor()
    {
        return this.cor;
    }

    public void setPosXY (float x, float y)
    {
        this.PosX=x;
        this.PoxY=y;
    }

    public void setMover (float x,float y)
    {
        MovX=this.PosX+x;
        MovY=this.PoxY+y;
    }
    public float getMovX ()
    {
        return this.MovX;
    }

    public float getMovY()
    {
        return this.MovY;
    }

    public void CriaBuffer (float[] array)
    {
        this.vrVetBuffer=Buffer.CriaBuffer(array);
    }
    public FloatBuffer getBuffer ()
    {
        return  this.vrVetBuffer;
    }
    public  void setEscala (float escala[])
    {
        this.escala=escala;
    }
    public void desenha(GL10 vrOpenGL)
    {
        vrOpenGL.glLoadIdentity();
        vrOpenGL.glTranslatef(getMovX(), getMovY(), 0);
        vrOpenGL.glRotatef(getAngulo(), 0,0,1);
        if(inverter) vrOpenGL.glRotatef(180,1,0,0);
        vrOpenGL.glColor4f(this.cor[0],this.cor[1],this.cor[2],1);
        vrOpenGL.glScalef(this.escala[0],this.escala[1],1);
        vrOpenGL.glVertexPointer(2,GL10.GL_FLOAT,0,getBuffer());
        vrOpenGL.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);
    }


}
