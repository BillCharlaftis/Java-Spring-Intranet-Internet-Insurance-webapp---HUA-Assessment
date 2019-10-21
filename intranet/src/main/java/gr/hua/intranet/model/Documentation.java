package gr.hua.intranet.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"rule"
})
public class Documentation {


@JsonIgnore
private Map<String, Object> documentation = new HashMap<String, Object>();



@JsonAnyGetter
public Map<String, Object> getDocumentation() {
return this.documentation;
}

@JsonAnySetter
public void setDocumentation(String name, Object value) {
this.documentation.put(name, value);
}

}