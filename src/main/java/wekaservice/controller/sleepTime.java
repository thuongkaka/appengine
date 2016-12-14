package wekaservice.controller;

import wekaservice.model.*;

import java.net.URL;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.Calendar;

import wekaservice.factory.PropertiesFactory;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Date;
import java.io.File;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import java.lang.String;
//import com.mysql.jdbc.*;
/**
 * Created by lunar on 11/23/2016.
 */
@Path("/sleepTime")
public class sleepTime {

    @javax.ws.rs.core.Context
    ServletContext context;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response userGetById(@PathParam("id") Integer id) throws Exception {
        UserName returnUserName = new UserName("" ,"");
        try {
            String userName =getUserNameById(id);
            if(userName != "" &&  userName != null )
            {
                returnUserName.setName(userName);
                return Response.ok().entity(returnUserName).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
            }else{
                returnUserName.setError("not found");
                return Response.ok().entity(returnUserName).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            returnUserName.setError("error " + e );
            return Response.ok().entity(returnUserName).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
        }
    }

    @GET
    @Path("/{id}/{starDay}/{endDay}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response sleepTimeGetByTime(@PathParam("id") Integer id,@PathParam("starDay")  String fromDate,@PathParam("endDay")  String toDate) throws  Exception,SQLException {
        ArrayList<ErrorMsg> list = new ArrayList<ErrorMsg>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ResultAPI resultAPI = null;
        Date fromDateForecast =null;
        Date toDayForecast = null;

        System.out.println(fromDate);
        System.out.println(toDate);
        try{
            fromDateForecast = dateFormat.parse(fromDate);
            toDayForecast = dateFormat.parse(toDate);
        }catch(Exception e){
            list.add(new ErrorMsg("invalid Date format","fromDate and toDate"));
            resultAPI = new ResultAPI(400,list,null);
            return Response.ok().entity(resultAPI).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
        }

        if(!validateTimeRange(fromDateForecast, toDayForecast)){
            list.add(new ErrorMsg("invalid time range","fromDate and toDate"));
            resultAPI = new ResultAPI(400,list,null);
            return Response.ok().entity(resultAPI).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
        }

        //get data forecast
        try{
            ArrayList<Data> data = forecastData(loadSleepInfo(id,fromDateForecast,toDayForecast),fromDateForecast,toDayForecast);

            ArrayList<Data> dataReturn =  fullWeek(data,fromDateForecast,toDayForecast);
            resultAPI = new ResultAPI(200,list,dataReturn);
            return Response.ok().entity(resultAPI).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
        }catch(Exception e){
            list.add(new ErrorMsg("can not get data", e.getMessage()));
            resultAPI = new ResultAPI(400,list,null);
            return Response.ok().entity(resultAPI).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
        }

    }

    // function
    //get data from DBsql
    private String getUserNameById(Integer id)throws SQLException, ClassNotFoundException
    {
        //get conection String from config
        String connectionString = "";
        try {
            Class.forName(PropertiesFactory.getInstance().getProperties().getProperty("connectionDriver"));
            connectionString = PropertiesFactory.getInstance().getProperties().getProperty("connectionString");
            System.out.println(connectionString);
        } catch (Exception ex){
            System.out.println("Can not get connection string from properties");
            ex.printStackTrace();
            return null;
        }
        // Declare the JDBC objects.
        // Class.forName("com.mysql.cj.jdbc.Driver"); // Force server to load SQL JDBC driver
        Connection connection = DriverManager.getConnection(connectionString);
        System.out.println(connectionString);
        // Create and execute an INSERT SQL prepared statement.
        String selectSql = "select nickname from t_user_info where user_id = ?";

        // Prepare SQL with parameters

        System.out.println(connectionString);
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1, id);
        System.out.println(selectSql);
        // Execute SQL statement
        ResultSet resultSet = preparedStatement.executeQuery();
        String userName = "";
        while (resultSet.next()) {
            userName = resultSet.getString(1);
        }
        if(!resultSet.isClosed()) resultSet.close();
        preparedStatement.close();
        if(!connection.isClosed()) connection.close();
        return userName;
    }

    // convert get list data from model
    private  ArrayList<Data> forecastData(ArrayList<SleepFulldata> listsleep, Date fromdate, Date toDate){
        ArrayList<Data> list = new ArrayList<Data>();
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
        String modelDir ="/tmp/M5P_20161012.model";
        File model= new File("/tmp/M5P_20161012.model");
        if(!model.exists()){
            modelDir =currentDir + "/model/M5P_20161012.model";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Double result=null;
        try{
            Classifier classifier = (Classifier) weka.core.SerializationHelper.read(modelDir);
            //new Instances
            Instances isTrainingSet;
            FastVector atts = new FastVector();
            FastVector attributeSex = new FastVector(2);
            attributeSex.addElement("male");
            attributeSex.addElement("female");
            Attribute sex = new Attribute("Sex", attributeSex);
            Attribute age = new Attribute("Age");
            FastVector attributeValues = new FastVector(7);
            attributeValues.addElement("Sun");
            attributeValues.addElement("Mon");
            attributeValues.addElement("Tue");
            attributeValues.addElement("Wed");
            attributeValues.addElement("Thu");
            attributeValues.addElement("Fri");
            attributeValues.addElement("Sat");
            Attribute day_of_the_week = new Attribute("day_of_the_week", attributeValues);
            Attribute highest_temp = new Attribute("highest_temp");
            Attribute lowest_temp = new Attribute("lowest_temp");
            Attribute awakening_score = new Attribute("awakening_score");
            atts.addElement(sex);
            atts.addElement(age);
            atts.addElement(day_of_the_week);
            atts.addElement(highest_temp);
            atts.addElement(lowest_temp);
            atts.addElement(awakening_score);
            isTrainingSet = new Instances("isTrainingSet", atts, 7);


            Instance instance = new Instance(6);


            for(SleepFulldata oneRecord: listsleep)
            {

                if(oneRecord.getSex()!= ""){
                    instance.setValue(sex, oneRecord.getSex());
                    instance.setValue(age, oneRecord.getAge());
                    instance.setValue(day_of_the_week,oneRecord.getDateOfWeek());
                    instance.setValue(awakening_score,oneRecord.getAwakening_score());
                    instance.setValue(highest_temp,oneRecord.getHighest());
                    instance.setValue(lowest_temp,oneRecord.getLowest());
                    result = classifier.classifyInstance(instance);
                    // add one record to result list
                    if(oneRecord.getSleepTime()<0){
                        list.add(new Data(oneRecord.getUser_id(),0,0,dateFormat.format(oneRecord.getDateSleep()),result.intValue() ));
                    }else{
                        list.add(new Data(oneRecord.getUser_id(),oneRecord.getSleepTime(),oneRecord.getAwakening_score(),dateFormat.format(oneRecord.getDateSleep()),result.intValue() ));
                    }
                }else{

                    list.add(new Data(oneRecord.getUser_id(),oneRecord.getSleepTime(),oneRecord.getAwakening_score(),dateFormat.format(oneRecord.getDateSleep()),0 ));
                }

            }
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

    // load data from sql return SleepFulldata table
    private  ArrayList<SleepFulldata> loadSleepInfo(Integer id,Date fromDate, Date toDate)
            throws SQLException, ClassNotFoundException{

        ArrayList<SleepFulldata> list = new ArrayList<SleepFulldata>();
        //get conection String from config
        String connectionString = "";
        try {
            connectionString = PropertiesFactory.getInstance().getProperties().getProperty("connectionString");

        } catch (Exception ex){
            System.out.println("Can not get connection string from properties");
            return null;
        }


        // Declare the JDBC objects.
        Class.forName("com.mysql.jdbc.Driver"); // Force server to load SQL JDBC driver
        Connection connection = DriverManager.getConnection(connectionString);
        // Create and execute an INSERT SQL prepared statement.

        String selectSql =
                "	SELECT * FROM ( " +
                        "		 SELECT IFNULL(A.id,0) id, A.sex,A.birth,  IFNULL(A.forecasted_date,B.measure_date) sleepDate, A.highest,A.lowest,A.dayofwek, IFNULL(B.sleep,-1) sleep,IFNULL(B.score, 3 ) score FROM " +
                        "         (" +
                        "            (select users.user_id id,users.sex sex, users.birthday birth,weather.forecasted_date forecasted_date, weather.highest_temp highest,weather.lowest_temp " +
                        "			 lowest ,weather.day_of_the_week dayofwek " +
                        "			 from  t_user_info users, t_forecasted_weather weather " +
                        "			 where users.user_id = ? " +
                        "				and (users.state = weather.state) " +
                        "				and (weather.forecasted_date BETWEEN ? and ?)" +
                        "				) as A " +
                        "         LEFT JOIN " +
                        "			 (select  actual.sleep_time sleep, actual.awakening_score score,actual.measure_date measure_date " +
                        "			 from t_actual_sleepdata actual " +
                        "			 where actual.user_id= ?" +
                        "				and  (actual.measure_date BETWEEN ? and ?)" +
                        "				) as B " +
                        "		 ON A.forecasted_date=B.measure_date " +
                        "         ) " +
                        "         UNION " +

                        "SELECT IFNULL(C.id,0) id, C.sex,C.birth,  IFNULL(C.forecasted_date,D.measure_date) sleepDate, C.highest,C.lowest,C.dayofwek, IFNULL(D.sleep,-1) sleep,IFNULL(D.score, 3 ) score FROM " +
                        "         (" +
                        "			(select users.user_id id,users.sex sex, users.birthday birth,weather.forecasted_date forecasted_date, weather.highest_temp highest,weather.lowest_temp " +
                        "			 lowest ,weather.day_of_the_week dayofwek " +
                        "			 from  t_user_info users, t_forecasted_weather weather " +
                        "			 where users.user_id = ? " +
                        "				and (users.state = weather.state) " +
                        "				and (weather.forecasted_date BETWEEN ? and ?)" +
                        "				) as C " +
                        "         RIGHT JOIN " +
                        "			 (select  actual.sleep_time sleep, actual.awakening_score score,actual.measure_date measure_date " +
                        "			 from t_actual_sleepdata actual " +
                        "			 where actual.user_id= ?" +
                        "				and  (actual.measure_date BETWEEN ? and ?)" +
                        "				) as D " +
                        "		 ON C.forecasted_date=D.measure_date " +
                        "      ) ) as T ORDER  BY sleepDate  ASC" ;

        // Prepare SQL with parameters
        PreparedStatement preparedStatement =connection.prepareStatement(selectSql);
        preparedStatement.setInt(1,id);
        preparedStatement.setDate(2, new java.sql.Date(fromDate.getTime()));
        preparedStatement.setDate(3, new java.sql.Date(toDate.getTime()));
        preparedStatement.setInt(4,id);
        preparedStatement.setDate(5, new java.sql.Date(fromDate.getTime()));
        preparedStatement.setDate(6, new java.sql.Date(toDate.getTime()));
        preparedStatement.setInt(7,id);
        preparedStatement.setDate(8, new java.sql.Date(fromDate.getTime()));
        preparedStatement.setDate(9, new java.sql.Date(toDate.getTime()));
        preparedStatement.setInt(10,id);
        preparedStatement.setDate(11, new java.sql.Date(fromDate.getTime()));
        preparedStatement.setDate(12, new java.sql.Date(toDate.getTime()));
        // Execute SQL statement
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            // if have forecast weather
            if(resultSet.getInt(1) > 0){

                float _age = getFloatAge(resultSet.getDate(3),resultSet.getDate(4));
                String sexString = "female";
                if (resultSet.getInt(1)==1)
                {
                    sexString="male";
                }
                list.add(new SleepFulldata(resultSet.getInt(1),sexString,_age,resultSet.getDate(4),resultSet.getString(7),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(8),resultSet.getInt(9),0));
            }else{
                // if do not have forecast weather, just have
                list.add(new SleepFulldata(id,"",null,resultSet.getDate(4),resultSet.getString(7),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(8),resultSet.getInt(9),0));
            }

        }

        if(!resultSet.isClosed()) resultSet.close();
        preparedStatement.close();
        if(!connection.isClosed()) connection.close();
        return list;
    }


    //common
    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
    public Boolean validateTimeRange(Date fromDate, Date toDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);

        int dateCount = daysBetween(fromDate, toDate);

        int fromDateOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTime(toDate);
        int toDateOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dateCount==6 && fromDateOfWeek == Calendar.SUNDAY && toDateOfWeek == Calendar.SATURDAY;
    }
    // convert birthday to age
    public  float getFloatAge(Date _birthday,Date _forecasted_date){
        float age= 0;
        Calendar calendar_birthday = new GregorianCalendar();
        calendar_birthday.setTime(_birthday);
        Calendar calendar_forecasted_date = new GregorianCalendar();
        calendar_forecasted_date.setTime(_forecasted_date);
        int years= calendar_forecasted_date.get(Calendar.YEAR) - calendar_birthday.get(Calendar.YEAR);
        int month = calendar_forecasted_date.get(Calendar.MONTH) - calendar_birthday.get(Calendar.MONTH);

        int date = calendar_forecasted_date.get(Calendar.DATE) - calendar_birthday.get(Calendar.DATE);
        Calendar last_birthday = new GregorianCalendar(calendar_forecasted_date.get(Calendar.YEAR) -1 , calendar_birthday.get(Calendar.MONTH), calendar_birthday.get(Calendar.DATE));
        if(month<=0)
        {
            if(month==0 && date==0){
                age = years;
            }
            if(month==0 && date!=0){
                age = (float)years + date/365;
            }
            if(month<0){
                age= (float)(years -1)+ (float)(calendar_forecasted_date.get(Calendar.DAY_OF_YEAR) + (365- last_birthday.get(Calendar.DAY_OF_YEAR)))/365;
            }
        }else{
            age= (float)years+ (float)(calendar_forecasted_date.get(Calendar.DAY_OF_YEAR) + (365-last_birthday.get(Calendar.DAY_OF_YEAR)))/365;
        }
        return age;

    }
    private ArrayList<Data> fullWeek(ArrayList<Data> data, Date fromDate, Date toDate){
        Date nowDate=fromDate;
        ArrayList<String> arrayFullDate =new ArrayList<String>();
        ArrayList<String> arrayDate =new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Boolean Flag = false;
        if(data.size()>=7){
            return data;
        }else{
            // array 7 days in week
            for(int i=0;i<7;i++){
                arrayFullDate.add(dateFormat.format(nowDate));
                nowDate.setTime(nowDate.getTime() + (1000 * 60 * 60 * 24));
            }
            //array days in database

            for(Data onerecord:data){
                arrayDate.add(onerecord.getDate());
            }

            for(int i=0;i<arrayFullDate.size();i++){
                if(!arrayDate.contains(arrayFullDate.get(i))){

                    data.add(i,new Data(0,0,0,arrayFullDate.get(i),0));
                }
            }

        }
        return data;
    }
}
