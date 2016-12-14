package wekaservice.model;

import java.lang.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;




/**
 * ResultAPI
 */
public class ResultAPI  implements java.io.Serializable {
  private Integer returnCode = null;

  private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();

  private List<Data> datas = new ArrayList<Data>();

  public ResultAPI(Integer _returnCode, List<ErrorMsg> _errors, List<Data> _datas )
  {
   this.returnCode = _returnCode;
    this.errors = _errors;
    this.datas = _datas;
  }
  public ResultAPI returnCode(Integer code) {
    this.returnCode = code;
    return this;
  }

   /**
   * Get returnCode
   * @return returnCode
  **/
  //@ApiModelProperty(value = "")
  public Integer getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(Integer returnCode) {
    this.returnCode = returnCode;
  }

  public ResultAPI errors(List<ErrorMsg> errors) {
    this.errors = errors;
    return this;
  }

  public ResultAPI addErrorsItem(ErrorMsg errorsItem) {
    this.errors.add(errorsItem);
    return this;
  }

   /**
   * Get errors
   * @return errors
  **/
  //@ApiModelProperty(value = "")
  public List<ErrorMsg> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorMsg> errors) {
    this.errors = errors;
  }

  public ResultAPI datas(List<Data> datas) {
    this.datas = datas;
    return this;
  }

  public ResultAPI addDatasItem(Data datasItem) {
    this.datas.add(datasItem);
    return this;
  }

   /**
   * Get datas
   * @return datas
  **/
  //@ApiModelProperty(value = "")
  public List<Data> getDatas() {
    return datas;
  }

  public void setDatas(List<Data> datas) {
    this.datas = datas;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResultAPI resultAPI = (ResultAPI) o;
    return Objects.equals(this.returnCode, resultAPI.returnCode) &&
        Objects.equals(this.errors, resultAPI.errors) &&
        Objects.equals(this.datas, resultAPI.datas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(returnCode, errors, datas);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResultAPI {\n");
    
    sb.append("    returnCode: ").append(toIndentedString(returnCode)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    datas: ").append(toIndentedString(datas)).append("\n");
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

