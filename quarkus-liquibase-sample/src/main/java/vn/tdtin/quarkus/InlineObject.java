package vn.tdtin.quarkus;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InlineObject {

    private String username;

    @JsonProperty(value = "addresses")
    private List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
