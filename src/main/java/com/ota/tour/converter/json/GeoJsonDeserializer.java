package com.ota.tour.converter.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.io.IOException;

public class GeoJsonDeserializer extends JsonDeserializer<GeoJsonPoint> {

    private final static String GEOJSON_TYPE_POINT = "Point";

    private final static String JSON_KEY_GEOJSON_TYPE = "type";
    private final static String JSON_KEY_GEOJSON_COORDS = "coordinates";


    @Override
    public GeoJsonPoint deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        double x = 0;
        double y = 0;
        final JsonNode tree = jp.getCodec().readTree(jp);
        if (tree.get(JSON_KEY_GEOJSON_TYPE) == null) {
            x = tree.get("lat").asDouble();
            y = tree.get("lon").asDouble();
        } else {
            final String type = tree.get(JSON_KEY_GEOJSON_TYPE).asText();
            final JsonNode coordsNode = tree.get(JSON_KEY_GEOJSON_COORDS);
            if (GEOJSON_TYPE_POINT.equalsIgnoreCase(type)) {
                x = coordsNode.get(0).asDouble();
                y = coordsNode.get(1).asDouble();
            } else {
                System.out.println(String.format("No logic present to deserialize %s ", tree.asText()));
            }
        }
        return new GeoJsonPoint(x, y);
    }
}
