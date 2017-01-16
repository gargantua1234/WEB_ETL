package com.arek.test;

import com.arek.objects.Product;
import com.arek.objects.RawData;
import com.arek.objects.Remark;
import com.arek.services.extractors.DataExtractor;
import com.arek.services.transformers.CommentTransformerImpl;
import com.arek.services.transformers.DataTransformer;
import com.arek.services.transformers.ProductTransformerImpl;
import com.arek.validator.SimpleCodeValidator;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 08.01.2017.
 */
public class Test {
    public static void main(String[] args) {
        try {
//            Document document = Jsoup.connect("http://ceneo.pl/42952133")
//                    .data("query", "Java")
//                    .userAgent("Mozilla")
//                    .cookie("auth", "token")
//                    .timeout(3000)
//                    .get();
//
//            Element body = document
//                    .select("#body")
//                    .first();
//
//            getBrand(body);
//            getType(body);
//            getModel(body);
//            System.out.println("---------------------");
//            getRemarks(body);
//            Element reviews = body
//                    .getElementsByClass("product-reviews")
//                    .first();
//                    .select(".main-content")
//                    .select(".screening-wrapper")
//                    .select(".product-reviews");


//            System.out.println(document
//                    .select(".main-content")
//                    .select(".screening-wrapper")
//                    .select(".product-reviews")
//                    .select(".product-review")
//                    .select(".product-reviewer")
//                    .text()
//            );

//            Elements elementsByClass = reviews.select(".product-review");
//
//            for(Element element : elementsByClass){
//                System.out.print("autor: ");
//                System.out.println(element.select(".product-reviewer").text());
//            }

//            dataExtractorTest();
            testDataTransformer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
        }
    }

    public static void getBrand(Element root){
        String brand = root
                        .select(".product-shop")
                        .attr("data-brand");
        System.out.println("Brand: " + brand);
    }

    public static void getType(Element root){
        String type = root
                .select(".breadcrumb")
                .last()
                .select("a")
                .select("span")
                .text();
        System.out.println("Type: "+ type);
    }

    public static void getModel(Element root){
        String model = root
                .select(".js_searchInGoogleTooltip")
                .first()
                .text();

        System.out.println("Model: " +model);
    }
    public static void getRemarks(Element root){
        List<Remark> remarks = new ArrayList<>();

        Elements rawRemarks = root
                .select("#productTechSpecs")
                .select(".specs-group")
                .select("tr");

        for(Element element: rawRemarks){
            Remark remark = new Remark();
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

        remarks.forEach(
                r-> System.out.println(r.getName()+": "+r.getValue())
        );
    }

    public static RawData dataExtractorTest() throws Exception {
        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setValidator(new SimpleCodeValidator());
        RawData extract = dataExtractor.extract("43223879");
        System.out.println(extract.getSnippetsNumber());

        return extract;
    }

    public static void testDataTransformer() throws Exception {
        RawData rawData;
        DataTransformer transformer = new DataTransformer();
        transformer.setProductTransformer(new ProductTransformerImpl());
        transformer.setCommentTransformer(new CommentTransformerImpl());
        Product product;


        rawData = dataExtractorTest();
        product = transformer.transform(rawData);

        showProductData(product);
        showRemarksData(product);
        showCommentsData(product);
        showPros(product);
        showCons(product);


    }



    private static void showProductData(Product product){
        System.out.println("ID: "+product.getId());
        System.out.println("Type: "+product.getType());
        System.out.println("Brand: "+product.getBrand());
        System.out.println("Model: "+product.getModel());
        System.out.println();
    }

    private static void showRemarksData(Product product) {
        System.out.println("Remark key: value");
        product.getRemarks()
                .forEach(r->System.out.println(r.getName()+": "+r.getValue()));
        System.out.println();
    }

    private static void showCommentsData(Product product) {
        String sep = ":";

        System.out.println("Comment id: Author: Date: Content: Rate: Recommended: Helpful: Unhelpful ");
        product.getComments()
                .forEach(p->System.out.println(p.getId()+sep+p.getAuthor()+
                        sep+p.getDate()/*+sep+p.getContent()*/+
                        sep+p.getRate()+sep+p.isRecommended()+sep+
                        p.getHelpful()+sep+p.getUnhelpful()
                ));

        System.out.println("number of comments:" +product.getComments().size());
        System.out.println();
    }

    private static void showPros(Product product) {
        System.out.println("Pros:");
        product.getComments().forEach(
                p-> p.getPros().forEach(
                        System.out::println
                )
        );
        System.out.println();
    }

    private static void showCons(Product product) {
        System.out.println("Cons:");
        product.getComments().forEach(
                p-> p.getCons().forEach(
                        System.out::println
                )
        );
        System.out.println();
    }

}

