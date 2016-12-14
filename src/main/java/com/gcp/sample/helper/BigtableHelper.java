package com.gcp.sample.helper;
import org.apache.hadoop.hbase.client.Connection;
import com.google.cloud.bigtable.hbase.BigtableConfiguration;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.ServletContext;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;


/**
 * Created by nnthuong on 2016/11/30.
 */
public class BigtableHelper {
    private static String PROJECT_ID = "ltctien-149903";
    private static String INSTANCE_ID = "sample-big-data";
    // The initial connection to Cloud Bigtable is an expensive operation -- We cache this Connection
    // to speed things up.  For this sample, keeping them here is a good idea, for
    // your application, you may wish to keep this somewhere else.
    private static Connection connection = null;
    private static ServletContext sc;
    /**
     * Connect will establish the connection to Cloud Bigtable.
     **/
    public static void connect() throws IOException {
        if (PROJECT_ID == null || INSTANCE_ID == null ) {
            sc.log("environment variables BIGTABLE_PROJECT, and BIGTABLE_INSTANCE need to be defined.");
            return;
        }

        connection = BigtableConfiguration.connect(PROJECT_ID, INSTANCE_ID);
        HttpTransport transport = null;
        try {
            transport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        JsonFactory jsonFactory = new JacksonFactory();
        GoogleCredential credential = GoogleCredential.getApplicationDefault(transport, jsonFactory);

    }
    public static Connection getConnection() {
        if(connection == null) {
            try {
                connect();
            } catch (IOException e) {
                sc.log("connect ", e);
            }
        }
        if(connection == null) sc.log("BigtableHelper-No Connection");
        return connection;
    }
}
