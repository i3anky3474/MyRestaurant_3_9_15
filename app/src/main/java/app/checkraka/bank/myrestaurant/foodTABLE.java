package app.checkraka.bank.myrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nipat on 9/3/15 AD.
 */
public class foodTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase,readSqLiteDatabase;

    public static final String FOOD_TABLE = "foodTABLE";
    public static final String COLUMN_ID_FOOD = "_id";
    public static final String COLUMN_FOOD = "Food";
    public static final String COLUMN_SOURCE = "Source";
    public static final String COLUMN_PRICE = "Price";

    public foodTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }//Constructor

    public long addNewFood(String strFood,String strSource,String strPrice) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_FOOD,strFood);
        objContentValues.put(COLUMN_SOURCE,strSource);
        objContentValues.put(COLUMN_PRICE,strPrice);

        return writeSqLiteDatabase.insert(FOOD_TABLE,null,objContentValues);
    }

}
