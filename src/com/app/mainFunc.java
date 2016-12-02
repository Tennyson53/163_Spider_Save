package com.app;

import com.structure.urlTuple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

        String url = "jdbc:mysql://localhost:3306/tenny_announcement";
        String name = "java.sql.Driver";
        String user = "root";
        String password = "passwd";

        String store_text = "insert into Announcement_html(Stock_ID,Announcement_ID,Publish_Date,html) values(?,?,?,?)";

        String ann_url, ann_text;
        try {
            Class.forName(name);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement sqlQuery = conn.prepareStatement(store_text);

            for (int i = 0; i < _end - _begin; i++) {
                tup = urlTups.get(i);
                ann_url = getText.getHTML(tup);
                ann_text = getText.getContent(ann_url);
                sqlQuery.setString(1, tup.Stock_ID);
                sqlQuery.setString(2, tup.Announcement_ID);
                sqlQuery.setString(3, tup.Publish_Date);
                sqlQuery.setString(4, ann_text);

                int resCode = sqlQuery.executeUpdate();
                if (resCode > 0) {
                    System.out.println("Complete Record " + i);
                } else {
                    System.out.println("ERROR");
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
