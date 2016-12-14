package wekaservice.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;




/**
 * Data
 */
public class Data  implements java.io.Serializable {
  private Integer id = null;

  private Integer SA = null;

  private Integer AS = null;

  private String date = null;

  private Integer forecast = null;
  public Data(Integer id, Integer SASec, Integer ASint, String date,Integer forecast) 
  {
    this.id = id;
    this.SA = SASec;
    this.AS = ASint;
    this.date = date;
    this.forecast = forecast;
  }
  public Data id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  //@ApiModelProperty(value = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Data SA(Integer SA) {
    this.SA = SA;
    return this;
  }

   /**
   * Get SA
   * @return SA
  **/
  //@ApiModelProperty(value = "")
  public Integer getSA() {
    return SA;
  }

  public void setSA(Integer SA) {
    this.SA = SA;
  }

  public Data AS(Integer AS) {
    this.AS = AS;
    return this;
  }

   /**
   * Get AS
   * @return AS
  **/
  //@ApiModelProperty(value = "")
  public Integer getAS() {
    return AS;
  }

  public void setAS(Integer AS) {
    this.AS = AS;
  }

  public Data date(String date) {
    this.date = date;
    return this;
  }

   /**
   * Get date
   * @return date
  **/
  //@ApiModelProperty(value = "")
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Data forecast(Integer forecast) {
    this.forecast = forecast;
    return this;
  }

   /**
   * Get forecast
   * @return forecast
  **/
  //@ApiModelProperty(value = "")
  public Integer getForecast() {
    return forecast;
  }

  public void setForecast(Integer forecast) {
    this.forecast = forecast;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Data data = (Data) o;
    return Objects.equals(this.id, data.id) &&
        Objects.equals(this.SA, data.SA) &&
        Objects.equals(this.AS, data.AS) &&
        Objects.equals(this.date, data.date) &&
        Objects.equals(this.forecast, data.forecast);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, SA, AS, date, forecast);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Data {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    SA: ").append(toIndentedString(SA)).append("\n");
    sb.append("    AS: ").append(toIndentedString(AS)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    forecast: ").append(toIndentedString(forecast)).append("\n");
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

