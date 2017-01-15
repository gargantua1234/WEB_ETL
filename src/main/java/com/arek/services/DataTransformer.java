package com.arek.services;

import com.arek.objects.Comment;
import com.arek.objects.Product;
import com.arek.objects.RawData;
import com.arek.objects.Remark;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataTransformer implements Transformer {
    @Override
    public Product transform(RawData data) {
        Product product = null;
        List<Comment> comments = null;

        product = extractProductData(data.getNext(), data.getProductId());
        data.reset();
        comments = extractCommentsData(data);
        product.setComments(comments);

        return product;
    }

    private Product extractProductData(Element htmlSnippet, int productId){
        Product product = new Product();
        String brand = getBrand(htmlSnippet);

        product.setId(productId);
        product.setType(getType(htmlSnippet));
        product.setBrand(brand);
        product.setModel(getModel(htmlSnippet, brand));
        product.setRemarks(getRemarks(htmlSnippet));

        return product;
    }

    private String getType(Element root){
        String type = root
                .select(".breadcrumb")
                .last()
                .select("a")
                .select("span")
                .text();

        return type;
    }

    private String getBrand(Element root) {
        String brand = root
                .select(".product-shop")
                .attr("data-brand");

        return brand;
    }

    private String getModel(Element root, String brand){
        String model = root
                .select(".js_searchInGoogleTooltip")
                .first()
                .text();
        model = model
                .replaceAll("(?i)"+brand, "")
                .trim();

        return model;
    }


    private List<Remark> getRemarks(Element root) {
        List<Remark> remarks = new ArrayList<>();
        Elements rawRemarks = root
                .select("#productTechSpecs")
                .select(".specs-group")
                .select("tr");
        Remark remark;

        for(Element element: rawRemarks){
            remark = new Remark();
            String name = element
                    .select("th")
                    .text();

            String value = element
                    .select("td")
                    .text();

            remark.setName(name);
            remark.setValue(value);
            remarks.add(remark);
        }

        return remarks;
    }


    private List<Comment> extractCommentsData(RawData data) {
        List<Comment> comments = new ArrayList<>();
        List<Comment> tmpComments;
        Element page;

        while(data.hasNext()){
            page = data.getNext();
            tmpComments = getComments(page);
            comments.addAll(tmpComments);
        }

        return comments;
    }

    private List<Comment> getComments(Element page) {
        List<Comment> comments = new ArrayList<>();
        Elements allComment = page
                .select(".product-reviews")
                .select(".product-review");

        Comment comment;

        for(Element singleComment: allComment){
            comment = new Comment();
            comment.setId(getId(singleComment));
            comment.setAuthor(getAuthor(singleComment));
            comment.setDate(getDate(singleComment));
            comment.setContent(getContent(singleComment));
            comment.setRate(getRate(singleComment));
            comment.setRecommended(isRecommended(singleComment));
            comment.setHelpful(getHelpful(singleComment));
            comment.setUnhelpful(getUnhelpful(singleComment));
            comment.setPros(getProsOrCons(singleComment, "pros"));
            comment.setCons(getProsOrCons(singleComment, "cons"));
            comments.add(comment);
        }


        return comments;
    }

    private String getAuthor(Element element){
        String author = element
                .select(".product-reviewer")
                .text();

        return author;
    }

    private long getId(Element element) {
        String id = element
                .select(".product-review-comment-dummy.js_product-review-comment-toggle")
                .attr("data-review-id");

        return Long.parseLong(id);
    }

    private String getDate(Element element) {
        String date = element
                .select(".review-time")
                .select("time")
                .attr("datetime");

        return date;
    }

    private String getContent(Element element){
        String content = element
                .select(".product-review-body")
                .text();

        return content;
    }

    private float getRate(Element element) {
        String rate = element
                .select(".review-score-count")
                .text();
        String[] split = rate.split("/");
        rate = split[0].replaceAll(",", ".");

        return Float.parseFloat(rate);
    }

    private boolean isRecommended(Element element) {
        String recommendation = element
                .select(".product-recommended")
                .text();

        return recommendation.equals("Polecam");
    }

    private int getHelpful(Element element) {
        String quantity = element
                .select(".js_product-review-usefulness.vote")
                .select(".vote-yes")
                .select("span")
                .text();

        return Integer.parseInt(quantity);
    }

    private int getUnhelpful(Element element) {
        String quantity = element
                .select(".js_product-review-usefulness.vote")
                .select(".vote-no")
                .select("span")
                .text();

        return Integer.parseInt(quantity);
    }

    private List<String> getProsOrCons(Element element, String choice) {
        List<String> result = new ArrayList<>();
        String type = "."+choice+"-cell";
        Elements allElements = element
                .select(type)
                .select("li");

        result.addAll(allElements.stream().map(Element::text).collect(Collectors.toList()));

        return result;
    }



    //method extracting data about product
    //method extracting data about remarks
        //returning list of remarks
    //method extracting data about comments
        // method will return list of comments
}
