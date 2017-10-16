package com.example.thales.tangramprovacomp.tangram.formatos;

import com.example.thales.tangramprovacomp.tangram.Geometria;

/**
 * Created by Thales on 30/09/2017.
 */

public class Quadrado extends Geometria {
    public Quadrado (float largura, float altura) {
        float[] coordenadas = {
                0,0,
                -largura/4,-altura/4,
                -largura/4,altura/4,
                -largura/2,0
        };

        CriaBuffer(coordenadas);

    }
}
