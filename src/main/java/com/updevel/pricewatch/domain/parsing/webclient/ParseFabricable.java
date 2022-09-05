package com.updevel.pricewatch.domain.parsing.webclient;

public interface ParseFabricable {
    Parserable getFabricByDomain(String host);
}
