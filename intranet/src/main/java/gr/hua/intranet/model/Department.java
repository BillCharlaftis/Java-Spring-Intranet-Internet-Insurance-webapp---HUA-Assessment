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
"name",
"address",
"telephone"
})
public class Department {


@JsonIgnore
private Map<String, Object> departments = new HashMap<String, Object>();



@JsonAnyGetter
public Map<String, Object> getDepartments() {
return this.departments;
}

@JsonAnySetter
public void setDepartments(String name, Object value) {
this.departments.put(name, value);
}

}