package com.example.thales.tangramprovacomp;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.thales.tangramprovacomp.tangram.formatos.Quadrado;
import com.example.thales.tangramprovacomp.tangram.formatos.Trapezio;
import com.example.thales.tangramprovacomp.tangram.formatos.Triangulo;


import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;



class Renderizador implements GLSurfaceView.Renderer
{

    Triangulo triangulo0,triangulo1,triangulo2,triangulo3,triangulo4;
    Quadrado quadrado=null;
    Trapezio trapezio= null;

    //Atributo Activity
    Activity vrActivity = null;

    public Renderizador(){

    }

    public Renderizador(Activity vrActivity){
        this.vrActivity = vrActivity;
    }

    @Override
    //será chamado quando o aplicativo for criado, 1 vez só
    public void onSurfaceCreated(GL10 vrOpengl, EGLConfig eglConfig) {

    }

    @Override
    //Vai ser chamada quando a superficie mudar
    public void onSurfaceChanged(GL10 vrOpenGL, int largura, int altura) {

        //configura a cor que sera utilizada para limpar o fundo da tela
        vrOpenGL.glClearColor(1.0f,1.0f,1.0f,1.0f);
        //Configura a area de visualização utilizada na tela do aparelho
        vrOpenGL.glViewport(0,0,largura,altura);

        triangulo0= new Triangulo(largura, largura);
        triangulo1= new Triangulo(largura, largura);
        triangulo2=new Triangulo(largura, largura);
        triangulo3= new Triangulo(largura, largura);
        triangulo4= new Triangulo(largura, largura);
        quadrado= new Quadrado(largura,largura);
        trapezio= new Trapezio(largura,largura);

        //Triangulo Rosa
        // triangulo.setPosXY(largura,altura);
        triangulo0.setMover(900,870);
        float cor[]={1.0f,0.0f,1.0f};
        triangulo0.setCor(cor);
        float escala[]= {0.5f,0.5f,0.5f};
        triangulo0.setAngulo(90);
        //triangulo.setEscala(escala);

        //Triangulo vermelho
        triangulo1.setMover(510,600);
        float cor2[]={1.0f,0.0f,0.0f};
        triangulo1.setCor(cor2);
        triangulo1.setAngulo(135);

        //Triangulo verde pequeno
        triangulo2.setMover(600,300);
        float cor3[]={0.0f,1.0f,0.0f};
        triangulo2.setCor(cor3);
        float escala2[]= {0.5f,0.5f,0.5f};
        triangulo2.setAngulo(90);
        triangulo2.setEscala(escala2);

        //Triangulo azul
        triangulo3.setMover(495,300);
        float escala3[]= {0.75f,0.75f,0.75f};
        float cor4[]={0.0f,0.0f,1.0f};
        triangulo3.setCor(cor4);
        triangulo3.setEscala(escala3);
        triangulo3.setAngulo(-45);

        //Triangulo amarelo pequeno
        triangulo4.setMover(500,750);
        float cor5[]={1.0f,1.0f,0.0f};
        triangulo4.setCor(cor5);
        triangulo4.setEscala(escala);
        triangulo4.setAngulo(90);

        //Quadrado preto
        quadrado.setMover(650,650);
        float corQuadrado[]= {1,0,0};
        quadrado.setCor(corQuadrado);
        quadrado.setEscala(escala);
        quadrado.setAngulo(45);

        //Trapezio cor aleatória
        trapezio.setMover(300,300);
        float corTrapezio[]= {(float)Math.random(),(float) Math.random(),(float) Math.random()};
        trapezio.setCor(corTrapezio);
        trapezio.setEscala(escala);
        trapezio.setInverter(true);
        trapezio.setAngulo(45);

        vrOpenGL.glMatrixMode(GL10.GL_PROJECTION);
        vrOpenGL.glLoadIdentity();  // carrega a matriz identidade para tirar o lixo da memoria
        vrOpenGL.glOrthof(0,largura, 0,altura,1,-1);
        vrOpenGL.glMatrixMode(GL10.GL_MODELVIEW);
        vrOpenGL.glLoadIdentity();
        vrOpenGL.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        int desenho = desenhaTextura(vrOpenGL);

    }

    @Override

    public void onDrawFrame(GL10 vrOpengl) {
        vrOpengl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //triangulo0.desenha(vrOpengl);

        //triangulo1.desenha(vrOpengl);
        //triangulo2.desenha(vrOpengl);
        //triangulo3.desenha(vrOpengl);
        //triangulo4.desenha(vrOpengl);
        quadrado.desenha(vrOpengl);
        //trapezio.desenha(vrOpengl);
        desenhaTextura(vrOpengl);

    }

    public int desenhaTextura(GL10 vrOpengl){
        //Criar o vetor resposável pelo armazenamento do código da textura
        int [] contextura = new int[1];

        //Aponta a máquina OpenGL para a memória a ser trabalhada
        vrOpengl.glBindTexture(GL10.GL_TEXTURE_2D, contextura[0]);

        //Carregar a imagem na memória RAM
        Bitmap vrImage = BitmapFactory.decodeResource(vrActivity.getResources(), R.mipmap.smile);
        //Solicitar a geração do código para a memória RAM
        vrOpengl.glGenTextures(1, contextura, 0);

        vrOpengl.glBindTexture(GL10.GL_TEXTURE_2D, contextura[0]);

        //Copiar  a imagem da RAM para a VRAM
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, vrImage,0);

        //Configura os filtros de imagem
        vrOpengl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);

        vrOpengl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);

        //Retira o apontador OpenGL da área de memória
        vrOpengl.glBindTexture(GL10.GL_TEXTURE_2D, 0);

        //Libera a memória da imagem na RAM
        vrImage.recycle();

        return contextura[0];
    }

}

public class MainActivity extends AppCompatActivity {
    //Cria uma variavel de referencia para a OpenGL
    GLSurfaceView superficieDesenho=null;
    Renderizador render=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Valida a variavel de referencia com uma instancia da superficie
        superficieDesenho=new GLSurfaceView(this);
        render= new Renderizador(this);
        //Ligando classe
        superficieDesenho.setRenderer(render);
        //Configura a tela do aparelho para mostrar a sup. de desenho
        setContentView(superficieDesenho);
        //IMPRIME UMA MENSAGEM NO LOG COM A TAG FPS E O TEXTO DO 2 PARAMETRO
        Log.i("FPS","Alguma coisa");

    }

}