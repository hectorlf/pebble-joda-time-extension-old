package com.hectorlopezfernandez.pebblejodatime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.tokenParser.TokenParser;

public class JodaExtension extends AbstractExtension {
	
	public static final String LOCALE_REQUEST_ATTRIBUTE = "joda_locale";
	public static final String TIMEZONE_REQUEST_ATTRIBUTE = "joda_timezone";
	public static final String PATTERN_REQUEST_ATTRIBUTE = "joda_pattern";

    private final JodaFilter filter;

    public JodaExtension() {
        this.filter = new JodaFilter();
    }

    @Override
    public Map<String, Filter> getFilters() {
        Map<String, Filter> filters = new HashMap<>();
        filters.put("joda", filter);
        return filters;
    }

    @Override
    public List<TokenParser> getTokenParsers() {
        List<TokenParser> parsers = new ArrayList<>();
        parsers.add(new JodaLocaleTokenParser());
        parsers.add(new JodaTimezoneTokenParser());
        return parsers;
    }

}