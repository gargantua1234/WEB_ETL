package com.arek.services.extractors;


import com.arek.models.RawData;

public interface Extractor {
    String CENEO_URL="http://www.ceneo.pl";

    RawData extract(String productCode) throws Exception;
}
