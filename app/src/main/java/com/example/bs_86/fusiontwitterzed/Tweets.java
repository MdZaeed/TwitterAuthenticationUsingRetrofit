package com.example.bs_86.fusiontwitterzed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BS-86 on 2/12/2016.
 */
public class Tweets {

    private List<Tweet> statuses =new ArrayList<Tweet>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Tweet> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Tweet> statuses) {
        this.statuses = statuses;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
