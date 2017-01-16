package com.arek.services.transformers;

import com.arek.objects.Product;
import com.arek.objects.Remark;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ProductTransformerImpl implements ProductTransformer {

    @Override
    public Product transformToProduct(Element htmlSnippet, int productId) {
        Product product = new Product();
        String brand = getBrand(htmlSnippet);

        product.setBrand(brand);
        product.setId(productId);
        product.setType(getType(htmlSnippet));
        product.setModel(getModel(htmlSnippet, brand));
        product.setRemarks(getRemarks(htmlSnippet));

        return product;
    }

    private String getBrand(Element root) {
        String brand = root
                .select(".product-shop")
                .attr("data-brand");

        return brand;
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
}
