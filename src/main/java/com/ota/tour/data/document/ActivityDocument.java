package com.ota.tour.data.document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ota.tour.converter.json.GeoJsonDeserializer;
import com.ota.tour.data.model.Translation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashSet;
import java.util.List;

@Data
@Document(collection = "activity")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ActivityDocument extends SequenceBaseDocument {
    private static final long serialVersionUID = 1L;

    private List<Translation> name;
    private List<Translation> overview;
    private List<Translation> importantInformation;
    private List<Translation> note;
    private List<Translation> policy;
    private List<Translation> howToUse;
    private List<Translation> meetUpInformation;
    private List<Translation> meetUpAddress;
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private GeoJsonPoint meetUpLocation;
    private LinkedHashSet<Long> destinationIds;
    private Boolean status;
    private Boolean isDelete = false;
}
