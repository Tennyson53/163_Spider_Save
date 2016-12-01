package com.app;

import com.structure.urlTuple;

import java.util.ArrayList;

/**
 * Created by tenny on 2016/11/30.
 */
public class mainFunc {

    public static void main(String[] args) {
        int _begin = 0;
        int _end = 10;

        ArrayList<urlTuple> urlTups = importIDs.getTuples(_begin, _end);
        urlTuple tup;
        String target_url;
        String target_text;
        for (int i = 0; i < _end - _begin; i++) {
            tup = urlTups.get(i);

            //Generate the target url
            target_url = getText.getHTML(tup);

            target_text = getText.getContent(target_url);
            System.out.println(target_text);

        }


    }
}
