package com.arek.services.transformers;

import com.arek.objects.Comment;
import com.arek.objects.RawData;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CommentTransformerImpl implements CommentTransformer {

    @Override
    public List<Comment> transformToComments(RawData rawData) {
        List<Comment> comments = new ArrayList<>();
        List<Comment> tmpComments;
        Element page;

        while(rawData.hasNext()){
            page = rawData.getNext();
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

        result.addAll(allElements
                .stream()
                .map(Element::text)
                .collect(Collectors.toList())
        );

        return result;
    }
}
