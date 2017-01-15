package com.arek.objects;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by Arek on 13.01.2017.
 */
public class RawData {
    private int position;
    private int productId;
    private List<Element> htmlSnippets;

    public RawData(List<Element> snippets, int productId){
        this.productId = productId;
        this.htmlSnippets = snippets;
    }

    public List<Element> getHtmlSnippets() {
        return htmlSnippets;
    }

    public void setHtmlSnippets(List<Element> htmlSnippets) {
        this.htmlSnippets = htmlSnippets;
    }

    public int getSnippetsNumber(){
        return htmlSnippets.size();
    }

    public boolean hasNext(){
        if(position < htmlSnippets.size() )
            return true;
        else
            return false;
    }

    public void reset(){
        position = 0;
    }

    public Element getNext(){
        return htmlSnippets.get(position++);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
