package app.checkraka.bank.myrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v4.util.Pools;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //Exolicit
    private UserTABLE objUserTABLE;
    private foodTABLE objFoodTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Connect DB
        createAndConnectDatabase();

        //Tester add value
//        testerAddValue();

        //Delete All Data
       //Synchronize JSON to SQLite
        synJSONtoSQLite();

    }//On Create

    private void synJSONtoSQLite() {
        //Change Policy
        StrictMode.ThreadPolicy objThreadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(objThreadPolicy);

        int time = 0;
        while (time <= 1)
        {
            InputStream objInputStream = null;
            String strJSON = null;
            String user_url = "http://swiftcodingthai.com/3sep/php_get_data_bank.php";
            String food_url = "http://swiftcodingthai.com/3sep/php_get_data_food.php";

            HttpPost objHttpPost = null;

            //1. Create Input Stream
            try {

                HttpClient objHttpClient = new DefaultHttpClient();
                if (time != 1) {
                    objHttpPost = new HttpPost(user_url);
                } else {
                    objHttpPost = new HttpPost(food_url);
                }

                //Response
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();
                
            }
            catch (Exception e)
            {
                Log.d("Rest","Input ==> "+e.toString());
            }

            //2. Create strJSON
            //3. Update to SQLite

            time +=1;
        }
    }//synJSONtoSQLite

    private void deleteAllData() {
        //All Delete
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("Restaurant.db",MODE_PRIVATE,null);
        objSqLiteDatabase.delete("userTABLE",null,null);
        objSqLiteDatabase.delete("foodTABLE",null,null);
    }

    private void testerAddValue() {
        objUserTABLE.addNewUser("admin","12345","Nipat");
        objFoodTABLE.addNewFood("MAMA","http://i.ytimg.com/vi/ZmN6Srrd8p4/hqdefault.jpg","150");
    }

    private void createAndConnectDatabase() {
        objUserTABLE = new UserTABLE(this);
        objFoodTABLE = new foodTABLE(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}//Main Class
