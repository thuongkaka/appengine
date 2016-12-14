package wekaservice.model;
import java.util.Objects;

/**
 * Created by ltctien on 2016/11/25.
 */
public class UserName implements java.io.Serializable {
    // var
    private String error = null;
    private String name = null;
    // constructor
    public UserName(String error, String name){
        this.error =  error;
        this.name = name;
    }
    // get, set error
    public UserName error(String error) {
        this.error = error;
        return this;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    // get, set name
    public UserName name(String userName) {
        this.name = userName;
        return this;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
