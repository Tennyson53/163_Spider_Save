package com.app;

import com.structure.urlTuple;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by tenny on 2016/11/30.
 */
public class getText {
    public static String getHTML(urlTuple T) {
        return "http://quotes.money.163.com/f10/ggmx_" + T.Stock_ID + "_" + T.Announcement_ID + ".html";
    }

    public static String getContent(String _url) {
        String html_res = new String();
        String html_line = new String();
        String html_text = new String();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new URL(_url).openConnection().getInputStream(), "UTF-8"));//GB2312可以根据需要替换成要读取网页的编码
            while ((html_line = in.readLine()) != null) {
                html_res = html_res + html_line + "\n";
            }

            //Replace all &ensp to blankspace
            html_res = html_res.trim().replaceAll("&ensp;", "");

            //Parse
            Parser html_parser = Parser.createParser(html_res, "utf-8");
            AndFilter andFil = new AndFilter(new TagNameFilter("div"),
                    new HasAttributeFilter("class", "inner_box"));
            Node node = null;
            NodeList nodes = html_parser.extractAllNodesThatMatch(andFil);

            node = nodes.elementAt(1);
            html_text = node.toPlainTextString();


        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(html_res);
        return html_text;

    }
}
