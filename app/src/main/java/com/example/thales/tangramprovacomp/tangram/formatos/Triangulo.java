package com.example.thales.tangramprovacomp.tangram.formatos;

import com.example.thales.tangramprovacomp.tangram.Geometria;

/**
 * Created by Thales on 25/09/2017.
 */

public class Triangulo extends Geometria
{
    public Triangulo(float largura, float altura) {
        float[] coordenadas = {
                -largura/4,-altura/4,
                -largura/4,altura/4,
                0,0
        };

        CriaBuffer(coordenadas);

    }
}
