package wekaservice.model;
import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Data
 */
public class SleepFulldata  implements java.io.Serializable {
  private Integer user_id = null;
  private String sex = null;
  private Float age = null;
  private Date dateSleep = null;
  private String dateOfWeek = null;
  private Integer highest = null;
  private Integer lowest = null;
  private Integer sleepTime = null;
  private Integer awakening_score = null;
  private Integer forecastSleepTime = null;
  public SleepFulldata(Integer _id, String _sex, Float _age, Date _dateSleep,String _dateOfWeek, Integer _highest,Integer _lowest,Integer _sleepTime,Integer _awakening_score,Integer _forecastSleepTime) 
  {
    this.user_id = _id;
    this.sex = _sex;
    this.age = _age;
    this.dateSleep = _dateSleep;
    this.dateOfWeek = _dateOfWeek;
    this.highest = _highest;
    this.lowest = _lowest;
    this.sleepTime = _sleepTime;
    this.awakening_score = _awakening_score;
    this.forecastSleepTime = _forecastSleepTime;
  }
  //-------------------------------------------------
  //user_id
  //-------------------------------------------------
  public SleepFulldata user_id(Integer _user_id) {
    this.user_id = _user_id;
    return this;
  }

   /**
   * Get user_id
   * @return user_id
  **/
  //@ApiModelProperty(value = "")
  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer _user_id) {
    this.user_id = _user_id;
  }
//-------------------------------------------------
//sex
//-------------------------------------------------
  public SleepFulldata sex(String _sex) {
    this.sex = _sex;
    return this;
  }

   /**
   * Get sex
   * @return sex
  **/
  //@ApiModelProperty(value = "")
  public String getSex() {
    return sex;
  }

  public void setId(String _sex) {
    this.sex = _sex;
  }
//-------------------------------------------------
//age
//-------------------------------------------------
  public SleepFulldata age(Float _age) {
    this.age = _age;
    return this;
  }

   /**
   * Get age
   * @return age
  **/
  //@ApiModelProperty(value = "")
  public Float getAge() {
    return age;
  }

  public void setAge(Float _age) {
    this.age = _age;
  }
//-------------------------------------------------
//Date dateSleep
//-------------------------------------------------
  public SleepFulldata dateSleep(Date _dateSleep) {
    this.dateSleep = _dateSleep;
    return this;
  }

   /**
   * Get dateSleep
   * @return dateSleep
  **/
  //@ApiModelProperty(value = "")
  public Date getDateSleep() {
    return dateSleep;
  }

  public void setDateSleep(Date _dateSleep) {
    this.dateSleep = _dateSleep;
  }

//-------------------------------------------------
//String dateOfWeek
//-------------------------------------------------
  public SleepFulldata dateOfWeek(String _dateOfWeek) {
    this.dateOfWeek = _dateOfWeek;
    return this;
  }

   /**
   * Get dateOfWeek
   * @return dateOfWeek
  **/
  //@ApiModelProperty(value = "")
  public String getDateOfWeek() {
    return dateOfWeek;
  }

  public void setDateOfWeek(String _dateOfWeek) {
    this.dateOfWeek = _dateOfWeek;
  }

//-------------------------------------------------
//Float highest
//-------------------------------------------------
  public SleepFulldata highest(Integer _highest) {
    this.highest = _highest;
    return this;
  }

   /**
   * Get highest
   * @return highest
  **/
  //@ApiModelProperty(value = "")
  public Integer getHighest() {
    return highest;
  }

  public void setHighest(Integer _highest) {
    this.highest = _highest;
  }
//-------------------------------------------------
//Float lowest
//-------------------------------------------------
  public SleepFulldata lowest(Integer _lowest) {
    this.lowest = _lowest;
    return this;
  }

   /**
   * Get lowest
   * @return lowest
  **/
  //@ApiModelProperty(value = "")
  public Integer getLowest() {
    return lowest;
  }

  public void setLowest(Integer _lowest) {
    this.lowest = _lowest;
  }
//-------------------------------------------------
//Integer sleepTime
//-------------------------------------------------
  public SleepFulldata sleepTime(Integer _sleepTime) {
    this.sleepTime = _sleepTime;
    return this;
  }

   /**
   * Get sleepTime
   * @return sleepTime
  **/
  //@ApiModelProperty(value = "")
  public Integer getSleepTime() {
    return sleepTime;
  }

  public void setSleepTime(Integer _sleepTime) {
    this.sleepTime = _sleepTime;
  }
//-------------------------------------------------
//Integer awakening_score
//-------------------------------------------------
  public SleepFulldata awakening_score(Integer _awakening_score) {
    this.awakening_score = _awakening_score;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  //@ApiModelProperty(value = "")
  public Integer getAwakening_score() {
    return awakening_score;
  }

  public void setAwakening_score(Integer _awakening_score) {
    this.awakening_score = _awakening_score;
  }
//-------------------------------------------------
//Integer forecastSleepTime
//-------------------------------------------------
  public SleepFulldata forecastSleepTime(Integer _forecastSleepTime) {
    this.forecastSleepTime = _forecastSleepTime;
    return this;
  }

   /**
   * Get forecastSleepTime
   * @return forecastSleepTime
  **/
  //@ApiModelProperty(value = "")
  public Integer getForecastSleepTime() {
    return forecastSleepTime;
  }

  public void setForecastSleepTime(Integer _forecastSleepTime) {
    this.forecastSleepTime = _forecastSleepTime;

  }
//-------------------------------------------------
  /**
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Data data = (Data) o;
    return Objects.equals(this.user_id, data.user_id) &&
        Objects.equals(this.sex, data.sex) &&
        Objects.equals(this.age, data.age) &&
        Objects.equals(this.dateSleep, data.dateSleep) &&
        Objects.equals(this.highest, data.highest) &&
        Objects.equals(this.lowest, data.lowest) &&
        Objects.equals(this.sleepTime, data.sleepTime) &&
        Objects.equals(this.awakening_score, data.awakening_score) &&
        Objects.equals(this.forecastSleepTime, data.forecastSleepTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user_id, sex, age, dateSleep, highest,lowest,sleepTime,awakening_score,forecastSleepTime);
  }

  @Override
     public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Data {\n");
    
    sb.append("    user_id: ").append(toIndentedString(user_id)).append("\n");
    sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    dateSleep: ").append(toIndentedString(dateSleep)).append("\n");
    sb.append("    highest: ").append(toIndentedString(highest)).append("\n");
    sb.append("    lowest: ").append(toIndentedString(lowest)).append("\n");
    sb.append("    sleepTime: ").append(toIndentedString(sleepTime)).append("\n");
    sb.append("    awakening_score: ").append(toIndentedString(awakening_score)).append("\n");
    sb.append("    forecastSleepTime: ").append(toIndentedString(forecastSleepTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }


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

