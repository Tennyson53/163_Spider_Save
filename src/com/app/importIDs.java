package com.app;

import com.structure.urlTuple;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by tenny on 2016/11/29.
 */
public class importIDs {

    public static ArrayList<urlTuple> getTuples(int _begin, int _end) {

        String url = "jdbc:mysql://localhost:3306/tenny_announcement";
        String name = "java.sql.Driver";
        String user = "root";
        String password = "passwd";

        String get_tuples = "SELECT * FROM URL_Info";

        urlTuple urlTup = new urlTuple();
        ArrayList<urlTuple> urlTups = new ArrayList();

        try {
            Class.forName(name);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement sqlQuery = conn.createStatement();
            ResultSet rs = sqlQuery.executeQuery(get_tuples);

            //Set start position
            for (int i = 0; i < _begin; i++) {
                rs.next();
            }
            //Get tuples
            while (rs.next()) {
                String tup_SID = rs.getString("Stock_ID");
                String tup_AID = rs.getString("Announcement_ID");
                String tup_TIME = rs.getString("Publish_DATE");

                urlTup.Stock_ID = tup_SID;
                urlTup.Announcement_ID = tup_AID;
                urlTup.Publish_Date = tup_TIME;

                urlTups.add(urlTup);

                urlTup = new urlTuple();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlTups;
    }

}
