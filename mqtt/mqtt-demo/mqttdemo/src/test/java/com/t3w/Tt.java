package com.t3w;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Tt {


    @Test
    public void suibian() throws ParseException {
        long[] longs =new long[]{1L,2L,3L};
        long[] longs1 = Arrays.stream(longs).filter(predicate -> predicate > 1L).toArray();

        System.out.println(Arrays.toString(longs1));
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        long time = format.parse("20190616180000").getTime();
        System.out.println(time);

    }
}
