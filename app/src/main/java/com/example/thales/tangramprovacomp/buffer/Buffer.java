package com.example.thales.tangramprovacomp.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Thales on 25/09/2017.
 */

public class Buffer {

    public static FloatBuffer CriaBuffer (float[] array)
    {
        //alloc Bytes in memory
        ByteBuffer vrByteBuffer= ByteBuffer.allocateDirect(array.length*4);
        vrByteBuffer.order(ByteOrder.nativeOrder());

        //Create a FloatBuffer
        FloatBuffer vrFloatBuffer= vrByteBuffer.asFloatBuffer();
        vrFloatBuffer.clear();

        //insert a java array into float buffer
        vrFloatBuffer.put(array);
        //reset floatBuffer attribs
        vrFloatBuffer.flip();

        return vrFloatBuffer;
    }
}
