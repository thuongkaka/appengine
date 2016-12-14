package com.gcp.bot.controller;

import com.gcp.bot.model.Event;
import com.gcp.bot.model.EventList;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by lunar on 12/12/2016.
 */
@Path("/line")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LineService {
    @POST
    public Response index(EventList events) {
        JSONObject message = new JSONObject();
        try {
                for (Event listEvent : events.getEvents()){
                    TextMessage textMessage = new TextMessage("hello");
                    ReplyMessage replyMessage = new ReplyMessage(
                            listEvent.getReplyToken(),
                            textMessage
                    );
                    JSONObject payload = new JSONObject(listEvent);
                    message = new JSONObject(payload.get("message").toString());

                    retrofit2.Response<BotApiResponse> response =
                            LineMessagingServiceBuilder
                                    .create("10a9dadb768f9be1645feda187fe7913")
                                    .build()
                                    .replyMessage(replyMessage)
                                    .execute();
                    System.out.println(response.code() + " " + response.message());

                }

            System.out.println(events.getEvents().get(0).getReplyToken());
            System.out.println(message);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
