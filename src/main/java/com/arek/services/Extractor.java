package com.arek.services;


import com.arek.objects.RawData;

public interface Extractor {
    String CENEO_URL="http://www.ceneo.pl";

    RawData extract(String productCode) throws Exception;
}
