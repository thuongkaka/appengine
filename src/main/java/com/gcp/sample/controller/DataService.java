package com.gcp.sample.controller;
import com.gcp.sample.helper.ConnectionHelper;
import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonCreator;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by lunar on 11/28/2016.
 */
    @Path("/data")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataService {
    @POST
    @JsonCreator
    public Response index(String body) {
        try {
            JSONObject payload = new JSONObject(body);

            JSONObject message = new JSONObject(payload.get("message").toString());


            //PubsubMessage message = PubsubMessage.parseFrom(ByteString.copyFrom(body, StandardCharsets.UTF_8));
            // Base64-decode the data and work with it.
            //final String data = new String(message.decodeData(), StandardCharsets.UTF_8);
            process(message);
            System.out.println(payload.get("message").toString());
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public void process(JSONObject message) throws Exception {
        Connection con = ConnectionHelper.getConnection();

        JSONObject attributes = new JSONObject(message.get("attributes").toString());

        String sql = "INSERT INTO `t_actual_sleepdata` VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE `sleep_time` = ?, `awakening_score` = ?;";
        PreparedStatement stmt = con.prepareStatement(sql);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date measure_date = dateFormat.parse(attributes.get("measure_date").toString());
        int sleep_time = Integer.parseInt(attributes.get("sleep_time").toString());
        int awakening_score = Integer.parseInt(attributes.get("awakening_score").toString());

        stmt.setInt(1, Integer.parseInt(attributes.get("user_id").toString()));
        stmt.setDate(2, new Date(measure_date.getTime()));
        stmt.setInt(3, sleep_time);
        stmt.setInt(4, awakening_score);
//        stmt.setInt(5, sleep_time);
//        stmt.setInt(6, awakening_score);

        stmt.execute();
        stmt.closeOnCompletion();
    }
}
