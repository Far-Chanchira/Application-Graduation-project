package com.example.mint.applicationtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mint.applicationtest.DataClass.DataBasket;
import com.example.mint.applicationtest.DataClass.DataClass;
import com.example.mint.applicationtest.Hashmap.Hashmap;
import com.example.mint.applicationtest.SQLConnection.SqlConnection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Basket extends AppCompatActivity implements View.OnClickListener {
    String idUser, idRice, date;
    int priceOfRice, amountOfOrder;
    private Button btnclear, btnSave;
    private List<DataBasket> list = new ArrayList<>();
    private List<DataClass> hashmapList = new ArrayList<>();
    AdapterBasket adapterBasket;
    SharedPreferences sharedPreferences, sp_id_user;
    private Hashmap hashmap;
    private TextView textTotal;
    private static final String TAG = "basket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket2);
        SharedPreferences preferences = getSharedPreferences("data", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
        sharedPreferences = getSharedPreferences(Show.LIST_STORE_DATA, Context.MODE_PRIVATE);
        idRice = sharedPreferences.getString(Show.ID_RICE, "");
        priceOfRice = (sharedPreferences.getInt(Show.PRICE_OF_RICE, 0));
        if (idRice.isEmpty()) {
            Toast.makeText(Basket.this, "plese select your order", Toast.LENGTH_LONG).show();
        }
        sp_id_user = getSharedPreferences(Login.MEMBRE_ID, Context.MODE_PRIVATE);
        idUser = sp_id_user.getString(Login.KEY_ID, "");
        textTotal = (TextView) findViewById(R.id.tvTotalPrice);
        if (!sharedPreferences.getString(Show.NAME_SET, "").isEmpty()) {
            hashmap = new Hashmap(Basket.this, sharedPreferences.getString(Show.NAME_SET, ""), sharedPreferences.getString(Show.PRICE_SET, "") + " " + sharedPreferences.getString(Show.AMOUNT_SET, ""));
            hashmapList = hashmap.hashmapToArray();
            for (DataClass dataClass : hashmapList) {
                list.add(new DataBasket(dataClass.getNameRice(), dataClass.getPriceRice()));
            }
            String[] convertStr = sharedPreferences.getString(Show.AMOUNT_SET, "").replaceAll("\\s+", " ").split(" ");
            amountOfOrder = Integer.parseInt(convertStr[1]);
            Log.d("main", convertStr[2]);
            textTotal.setText(convertStr[2]);
        } else {
            textTotal.setText("ราคารวม");
        }
        //}
        /*if (bundle == null) {
            for (int i = 0; i <= 20; i++) {
                list.add(new DataBasket("test", "test"));
            }

        } else {
            ArrayList<DataBasket> dataList = (ArrayList<DataBasket>) bundle.getParcelable(Show.LIST_STORE_DATA);
            list.addAll(dataList);
            adapterBasket = new AdapterBasket(Basket.this, list);
        }*/
        adapterBasket = new AdapterBasket(Basket.this, list);

        ListView view = (ListView) findViewById(R.id.basket);
        view.setAdapter(adapterBasket);
        (btnclear = (Button) findViewById(R.id.bClear)).setOnClickListener(this);
        (btnSave = (Button) findViewById(R.id.bShop)).setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Basket.this, MainActivity.class));
        SharedPreferences preferences = getSharedPreferences("data", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        if (editor.clear().commit()) {
            Toast.makeText(Basket.this, "delete complete", Toast.LENGTH_LONG).show();
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bClear:
                list.clear();
                adapterBasket.notifyDataSetChanged();
                textTotal.setText("ราคารวม");
                sharedPreferences.edit().clear().commit();
                sp_id_user.edit().clear().commit();
                Toast.makeText(Basket.this, "Clear data complete", Toast.LENGTH_LONG).show();
                break;
            case R.id.bShop:
                date = getDate();
                SharedPreferences sharedPreferences = getSharedPreferences(Show.LIST_STORE_DATA, MODE_PRIVATE);
                String riceName = sharedPreferences.getString(Show.NAME_RICE, "");
                Log.d(TAG, "id user = " + idUser);
                Log.d(TAG, "id rice = " + idRice);
                Log.d(TAG, "price = " + String.valueOf(priceOfRice));
                Log.d(TAG, "quantity = " + String.valueOf(amountOfOrder));
                Log.d(TAG, "Date = " + date);
                Log.d(TAG, "name = " + riceName);
                //TODO เอาค่าข้างบนมาใส่ใน()
                SqlConnection.insertUser(date,riceName,String.valueOf(amountOfOrder),String.valueOf(priceOfRice),idUser,idRice);
                //TODO เพิมโค๊ดในsQLCONNECTION


                /*SharedPreferences preferences = getSharedPreferences("data", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("iduser", idUser);
                editor.putString("idrice", idRice);
                editor.putInt("price", priceOfRice);
                editor.putInt("quantity", amountOfOrder);
                editor.putString("date", date);
                editor.putString("namerice", riceName);
                editor.commit();
                Toast.makeText(Basket.this, "สั่งจองสำเร็จ", Toast.LENGTH_LONG).show();*/

                break;
        }

    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(calendar.getTime());
        return date;
    }

    public void openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        Intent i = manager.getLaunchIntentForPackage(packageName);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(i);

    }


}
