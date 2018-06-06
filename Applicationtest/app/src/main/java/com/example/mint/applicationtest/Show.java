package com.example.mint.applicationtest;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Show extends AppCompatActivity {
    private String total;
    private TextView textPrice, txtRemarkable, txtGeneral, txtArea, txtRiceName, textAmount, totalview, showId;
    private Button orderBtn;
    private ImageView imgRice;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String ID_RICE = "id_rice";
    public static final String LIST_STORE_DATA = "list";
    public static final String NAME_SET = "set";
    public static final String PRICE_SET = "pset";
    public static final String AMOUNT_SET = "amount_set";
    public static final String PRICE_OF_RICE = "quantity_order";
    public static final String TOTAL_SET = "total_set";
    public static final String NAME_RICE = "name_price";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //Log.d("data",getIntent().getStringExtra("data"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences(LIST_STORE_DATA, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        txtRiceName = (TextView) findViewById(R.id.txtRiceName);
        textPrice = (TextView) findViewById(R.id.txtPrice);
        txtRemarkable = (TextView) findViewById(R.id.txtRemarkable);
        txtGeneral = (TextView) findViewById(R.id.txtGeneral);
        txtArea = (TextView) findViewById(R.id.txtArea);
        imgRice = (ImageView) findViewById(R.id.imgRice);
        orderBtn = (Button) findViewById(R.id.order_button);
        showId = (TextView) findViewById(R.id.dam_show_id);

        //รับค่าจากหน้าแฟรกเม้น แต่ละหน้า แล้วนำค่ามาเซ็ทลงแต่ละวิว
        Bundle bundle = getIntent().getExtras();
        Bitmap bitmap = bundle.getParcelable("image");
        imgRice.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imgRice.setImageBitmap(bitmap);
        txtRiceName.setText(bundle.getString("ricename"));
        textPrice.setText(bundle.getString("price"));
        txtRemarkable.setText(bundle.getString("remark"));
        txtGeneral.setText(bundle.getString("general"));
        txtArea.setText(bundle.getString("area"));
        showId.setText(bundle.getString("id"));
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent openBasketActivity = new Intent(Show.this, Basket.class);
                ArrayList<DataBasket> list = new ArrayList<DataBasket>();
                list.add(new DataBasket(txtRiceName.getText().toString(), textPrice.getText().toString()));
                openBasketActivity.putExtra(LIST_STORE_DATA, list);
                startActivity(openBasketActivity);*/
                Log.d("name", txtRiceName.getText().toString());
                editor.putString(NAME_SET, txtRiceName.getText().toString());
                editor.putString(PRICE_SET, textPrice.getText().toString());
                editor.putString(ID_RICE, showId.getText().toString());
                editor.putInt(PRICE_OF_RICE, Integer.parseInt(textPrice.getText().toString()));
                editor.putString(NAME_RICE,txtRiceName.getText().toString());
                Log.d("id", "RICE ID = " + showId.getText().toString());

                editor.commit();
                showDialog();

            }
        });
        //เรียกฐานข้อมูล
        /*try {
            JSONObject data=new JSONObject(getIntent().getStringExtra("data"));
            ((TextView)findViewById(R.id.txtRiceName)).setText(data.getString("ricename"));
            ((TextView)findViewById(R.id.txtPrice)).setText(data.getString("price"));
            ((TextView)findViewById(R.id.txtRemarkable)).setText(data.getString("remarkable"));
            ((TextView)findViewById(R.id.txtGeneral)).setText(data.getString("general"));
            ((TextView)findViewById(R.id.txtArea)).setText(data.getString("area"));
            Glide.with(this).load("http://www.zp10290.tld.122.155.18.18.no-domain.name/admin/rice/myfile/"+data.getString("img")).asBitmap().into((ImageView)findViewById(R.id.imgRice));

          //  Toast.makeText(this,data.getString("ricename"),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    public void showDialog() {

        final Dialog dialog = new Dialog(Show.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_comfirm_order);
        dialog.show();
        dialog.setCancelable(false);
        final EditText order = dialog.findViewById(R.id.amount_order);
        final Button btn_cf = dialog.findViewById(R.id.cf_btn);
        textAmount = dialog.findViewById(R.id.text_amount);
        totalview = dialog.findViewById(R.id.txtTotal);
        if (textAmount.getText().toString().isEmpty()) {
            btn_cf.setEnabled(false);
        }
        order.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textAmount.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (textAmount.getText().toString().isEmpty()) {
                    btn_cf.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (order.getText().length() == 0) {
                    textAmount.setText("");
                    btn_cf.setEnabled(false);
                }

            }
        });
        order.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH ||
                        i == EditorInfo.IME_ACTION_DONE ||
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                keyEvent.getKeyCode() == keyEvent.KEYCODE_ENTER
                        ) {
                    int amount = Integer.valueOf(order.getText().toString());
                    int price = Integer.valueOf(textPrice.getText().toString());
                    Log.d("a", order.getText() + "" + textPrice.getText());
                    total = String.valueOf(amount * price);
                    textAmount.setText(total);
                    order.clearFocus();
                    btn_cf.setEnabled(true);
                }

                return true;
            }
        });
        (dialog.findViewById(R.id.cf_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("a", total);
                Intent basketPage = new Intent(Show.this, Basket.class);
                editor.putString(AMOUNT_SET,"                 "+ order.getText().toString() + "              " + total);

                editor.commit();
                startActivity(basketPage);
            }
        });
        (dialog.findViewById(R.id.cc_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

//        int id = item.getItemId();
//        if(id == R.id.home){
//            System.out.println("Home id;" + id);
//            Intent myIntent = new Intent(getApplicationContext(), IndexActivity.class);
//            startActivity(myIntent);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
