package com.gcp.sample.controller;

import com.gcp.sample.helper.BigtableHelper;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.util.Bytes;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


/**
 * Created by lunar on 11/28/2016.
 */
@Path("/storage")
@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StorageService {
    // Refer to table metadata names by byte array in the HBase API
    private static final byte[] TABLE_NAME = Bytes.toBytes("t_actual_sleepdata");
    private static final byte[] COLUMN_FAMILY_NAME = Bytes.toBytes("gcp");
    private static final byte[] COLUMN_DEVICE_ID = Bytes.toBytes("device_id");
    private static final byte[] COLUMN_USER_ID = Bytes.toBytes("user_id");
    private static final byte[] COLUMN_MEASURE_DATE = Bytes.toBytes("measure_date");
    private static final byte[] COLUMN_SLEEP_TIME = Bytes.toBytes("sleep_time");
    private static final byte[] COLUMN_AWAKENING_SCORE= Bytes.toBytes("awakening_score");
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".store/compute_engine_sample");
    @POST
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
        JSONObject attributes = new JSONObject(message.get("attributes").toString());
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        java.util.Date measure_date = dateFormat.parse(attributes.get("measure_date").toString());
//        int sleep_time = Integer.parseInt(attributes.get("sleep_time").toString());
//        int awakening_score = Integer.parseInt(attributes.get("awakening_score").toString());
//         String PROJECT_ID = "ltctien-149903";
//         String INSTANCE_ID = "sample-big-data";
        Connection connection = null;
        try {
            connection = BigtableHelper.getConnection();
            // The admin API lets us create, manage and delete tables
            Admin admin = connection.getAdmin();

//            try
//            {
//                HttpTransport httpTransport = new NetHttpTransport();
//                JacksonFactory jsonFactory = new JacksonFactory();
//
//                // unset GOOGLE_APPLICATION_CREDENTIALS
//                //String SERVICE_ACCOUNT_JSON_FILE = "YOUR_SERVICE_ACCOUNT_JSON_FILE.json";
//                //FileInputStream inputStream = new FileInputStream(new File(SERVICE_ACCOUNT_JSON_FILE));
//                //GoogleCredential credential = GoogleCredential.fromStream(inputStream, httpTransport, jsonFactory);
//
//                // to use application default credentials and a JSON file, set the environment variable first:
//                // export GOOGLE_APPLICATION_CREDENTIALS=YOUR_SERVICE_ACCOUNT_JSON_FILE.json
//                GoogleCredential credential = GoogleCredential.getApplicationDefault(httpTransport,jsonFactory);
//
//                if (credential.createScopedRequired())
//                    credential = credential.createScoped(Arrays.asList(Oauth2Scopes.USERINFO_EMAIL));
//
//                Oauth2 service = new Oauth2.Builder(httpTransport,jsonFactory,credential)
//                        .setApplicationName("oauth client")
//                        .build();
//                Userinfoplus ui = service.userinfo().get().execute();
//                System.out.println(ui.getEmail());
//            }
//            catch (Exception ex) {
//                System.out.println("Error:  " + ex);
//            }


//            // Create a table with a single column family
//            if(!admin.tableExists(TableName.valueOf(TABLE_NAME))){
//                HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
//                descriptor.addFamily(new HColumnDescriptor(COLUMN_USER_ID));
//                descriptor.addFamily(new HColumnDescriptor(COLUMN_MEASURE_DATE));
//                descriptor.addFamily(new HColumnDescriptor(COLUMN_SLEEP_TIME));
//                descriptor.addFamily(new HColumnDescriptor(COLUMN_AWAKENING_SCORE));
//                admin.createTable(descriptor);
//            } else {
//                // [START writing_rows]
//                // Retrieve the table we just created so we can do some reads and writes
//                Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
//                // Write some rows to the table
//                for ( int i = 0 ; i < attributes.length(); i++) {
//                    Put put = new Put(Bytes.toBytes(i));
//                    put.addColumn(COLUMN_FAMILY_NAME, COLUMN_USER_ID, Bytes.toBytes(attributes.getString("user_id")));
//                    put.addColumn(COLUMN_FAMILY_NAME, COLUMN_MEASURE_DATE, Bytes.toBytes(attributes.getString("measure_date")));
//                    put.addColumn(COLUMN_FAMILY_NAME, COLUMN_SLEEP_TIME, Bytes.toBytes(attributes.getString("sleep_time")));
//                    put.addColumn(COLUMN_FAMILY_NAME, COLUMN_AWAKENING_SCORE, Bytes.toBytes(attributes.getString("awakening_score")));
//                    table.put(put);
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
