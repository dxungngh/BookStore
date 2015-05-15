package com.dungnh8.truyen_ngon_tinh.parser;

import android.util.Log;

import com.dungnh8.truyen_ngon_tinh.database.model.Book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class BookParser {
    public static final String TAG = BookParser.class.getSimpleName();

    public List<Book> parseBooks(String content) throws Exception {
        List<Book> books = new ArrayList<>();
        Document document = Jsoup.parse(content);
        Element element = document.select("div[class*=tcaricature_block").get(0);
        Elements liTags = element.select("li");
        for (Element liTag : liTags) {
            Book book = parseBook(liTag);
            if (book != null) {
                books.add(book);
            }
        }
        return books;
    }

    private Book parseBook(Element liTag) {
        try {
            Element aTag = liTag.select("a").get(0);
            String url = aTag.attr("href");

            Element imgTag = aTag.select("img").get(0);
            String avatar = imgTag.attr("src");
            String name = imgTag.attr("alt");

            Elements tags = liTag.select("div[class=adiv2hidden]");
            Element authorTag = tags.get(1);
            String author = authorTag.select("a").text();

            Book book = new Book();
            if (tags.size() > 2) {
                Element newChapterTag = tags.get(2);
                String newChapter = newChapterTag.select("a").text();
                book.setNewChapterTitle(newChapter);
            }
            book.setUrl(url);
            book.setAvatar(avatar);
            book.setName(name);
            book.setAuthor(author);
            return book;
        } catch (Exception e) {
            Log.e(TAG, "parseBook", e);
            return null;
        }
    }
}