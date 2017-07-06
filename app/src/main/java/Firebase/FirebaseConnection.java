package Firebase;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Firebase.DataObjects.FirebaseObject;
import Firebase.DataObjects.User;

import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

public class FirebaseConnection {
    private FirebaseDatabase db;

    public FirebaseConnection(){
        db = FirebaseDatabase.getInstance();
    }

    public Task<FirebaseObject> getObject(final String objectType, String key){
        DatabaseReference objectReference = db.getReference("ibsmithmediacalc/" + objectType + "/" + key);
        final TaskCompletionSource<FirebaseObject> taskOutput = new TaskCompletionSource<>();
        objectReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    String className = objectType.substring(0, 1).toUpperCase() + objectType.substring(1);
                    FirebaseObject object = (FirebaseObject) dataSnapshot.getValue(Class.forName("Firebase.DataObjects." + className));
                    if (object != null){
                        object.setKey(dataSnapshot.getKey());
                    }else{
                        object = null;
                    }
                    taskOutput.setResult(object);
                }catch (ClassNotFoundException e){
                    Log.v("ERROR EXPLANATION", "Could not get objects");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        return taskOutput.getTask();
    }

    //get all children of the requested object
    public Task<List<FirebaseObject>> getAllObjects(final String objectType){
        DatabaseReference reference = db.getReference("objects/" + objectType);
        final TaskCompletionSource<List<FirebaseObject>> output = new TaskCompletionSource<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot table) {
                output.setResult(buildObjects(table));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        return output.getTask();
    }

    //writes/updates an object's value in the database
    private void writeToDatabase(FirebaseObject object){
        String objectType = getObjectType(object);
        String objectID =  object.key();
        DatabaseReference reference = db.getReference("objects/" + objectType + "/" + objectID);
        reference.setValue(object);
        return;
    }

    //helper methods
    private String getObjectType(FirebaseObject object){
        String objectType = object.getClass().toString();
        String removableString = "class Firebase.DataObjects.";
        String newString = objectType.replaceAll(removableString, "");
        return newString.toLowerCase();
    }
    private List<FirebaseObject> buildObjects(DataSnapshot table){
        List<FirebaseObject> newObjects = new ArrayList<>();
        String objectType = table.getKey();
        String className = objectType.substring(0, 1).toUpperCase() + objectType.substring(1);
        Class tableClass = FirebaseObject.class;
        try {
            tableClass = Class.forName("Firebase.DataObjects." + className);
        } catch (ClassNotFoundException e){}
        for (DataSnapshot objectData: table.getChildren()){
            FirebaseObject newObject = (FirebaseObject) objectData.getValue(tableClass);
            newObject.setKey(objectData.getKey());
            newObjects.add(newObject);
        }
        return newObjects;
    }






//TODO: Add counter when Public
    private void incrementCounter(FirebaseObject object){
        DatabaseReference reference = db.getReference("counts/user");
        reference.setValue(Integer.parseInt(object.key()));
    }
    public void addUser(final User user){
        final User newUser = user;
        DatabaseReference countRef = db.getReference("counts/user");
        countRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //fetch the count from the data snapshot
                int userCount = (int) dataSnapshot.getValue(int.class);
                userCount++;
                addUsrWithKey(user, userCount + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void addUsrWithKey(User user, String key){
        user.setKey(key);
        final User finalUser = user;
        incrementCounter(user);
        updateObject(user);
    }



    public void updateObject(FirebaseObject object){
        writeToDatabase(object);
    }
    public Task<List<List<String>>> getResources(){
     //TODO: add id when login added here user/Resources
        String reference = "ibsmithmediacalc";
        DatabaseReference ref = db.getReference(reference);
        final TaskCompletionSource<List<List<String>>> taskOutput = new TaskCompletionSource<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int resourceIndex = 0;
                List<List<String>> resourceLists = new ArrayList<List<String>>();
                for (DataSnapshot resourceCollection: dataSnapshot.getChildren()){
                    List<String> resources = new ArrayList<String>();
                    for (DataSnapshot eachResource: resourceCollection.getChildren()){
                        String resource = (String) eachResource.getValue(String.class);
                        resources.add(resource);
                    }
                    resourceLists.add(resources);
                    resourceIndex++;
                }
                taskOutput.setResult(resourceLists);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return taskOutput.getTask();
    }
}
