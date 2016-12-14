package wekaservice.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;





/**
 * Error
 */
public class Error implements java.io.Serializable  {
  private String error = null;

  private String parameter = null;

  public Error(String error, String parameter){
    this.error =  error;
    this.parameter = parameter;
  }
  public Error error(String error) {
    this.error = error;
    return this;
  }

   /**
   * Get error
   * @return error
  **/
  //@ApiModelProperty(value = "")
  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public Error parameter(String parameter) {
    this.parameter = parameter;
    return this;
  }

   /**
   * Get parameter
   * @return parameter
  **/
  //@ApiModelProperty(value = "")
  public String getParameter() {
    return parameter;
  }

  public void setParameter(String parameter) {
    this.parameter = parameter;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.error, error.error) &&
        Objects.equals(this.parameter, error.parameter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(error, parameter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    parameter: ").append(toIndentedString(parameter)).append("\n");
    sb.append("}");
    return sb.toString();
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

