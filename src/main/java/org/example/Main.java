package org.example;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import processing.core.PApplet;

import static com.mongodb.client.model.Filters.eq;

public class Main {


  public static void main(String[] args) throws InterruptedException {

    String[] appletArgs = new String[]{"Spacesaver"};
    Window window = new Window();
    PApplet.runSketch(appletArgs, window);

    // you can use these credentials until there are any overwrite conflicts, then talk to Paul
    ConnectionString connectionString = new ConnectionString("mongodb+srv://COMP2522:Tuesday29@cluster0.rnpqryh.mongodb.net/?retryWrites=true&w=majority");
    // settings for connecting to MongoDB
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    // connect to MongoDB
    MongoClient mongoClient = MongoClients.create(settings);
    // Use this database unless there are overwrite conflicts
    MongoDatabase database = mongoClient.getDatabase("comp2522fall2022");

    // Replace "test" with your group name
    MongoCollection<Document> collection = database.getCollection("test");

    while (window.gameState == 1 || window.gameState == 0){
      Thread.sleep(1000);
    }

    Document doc = new Document("name", window.name)
            .append("score", window.result);

    // Async calls may execute in any order
    // insert document
    collection.insertOne(doc)
            // subscribe takes a class that defines the callback
            .subscribe(new SubscriberHelpers.OperationSubscriber<InsertOneResult>());
    // find document
    collection.find(eq("name", window.name))
            // subscribe takes a class that defines the callback
            .first().subscribe(new SubscriberHelpers.PrintDocumentSubscriber());



  }
}