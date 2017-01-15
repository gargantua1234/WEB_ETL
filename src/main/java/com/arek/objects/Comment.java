package com.arek.objects;

import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */
public class Comment {


    private long id;
    private String author = "anonymous";
    private String date;
    private String content;
    private float rate;
    private boolean recommended;
    private int helpful;
    private int unhelpful;
    private List<String> pros;
    private List<String> cons;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public int getHelpful() {
        return helpful;
    }

    public void setHelpful(int helpful) {
        this.helpful = helpful;
    }

    public int getUnhelpful() {
        return unhelpful;
    }

    public void setUnhelpful(int unhelpful) {
        this.unhelpful = unhelpful;
    }

    public List<String> getPros() {
        return pros;
    }

    public void setPros(List<String> pros) {
        this.pros = pros;
    }

    public List<String> getCons() {
        return cons;
    }

    public void setCons(List<String> cons) {
        this.cons = cons;
    }
}
