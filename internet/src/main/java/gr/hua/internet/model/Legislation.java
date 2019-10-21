package gr.hua.internet.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"law"
})
public class Legislation {


@JsonIgnore
private Map<String, Object> legislation = new HashMap<String, Object>();



@JsonAnyGetter
public Map<String, Object> getLegislation() {
return this.legislation;
}

@JsonAnySetter
public void setLegislation(String name, Object value) {
this.legislation.put(name, value);
}

}