package com.example.mint.applicationtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mint.applicationtest.DataClass.Datanews;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mint on 8/6/2017.
 */

public class RiceNewsFragment extends Fragment implements AdapterView.OnItemClickListener  {
    private static final String Url = "http://www.zp10290.tld.122.155.18.18.no-domain.name/select_news.php";
    private List<Datanews> datas = new ArrayList<>();
    View v;
    ListView riceList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("make","ok");
        v =  inflater.inflate(R.layout.rice_news,null);
        riceList = v.findViewById(R.id.rice_listview);
        List<JSONObject> datas= WebApiRequest.start("http://www.zp10290.tld.122.155.18.18.no-domain.name/select_news.php","");
        //RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        //requestQueue.add(jsonArrayRequest);

        //MyAdaptermiddle adapter = new MyAdaptermiddle(getActivity().getApplicationContext(), datas);
        MyAdapter3 adapter = new MyAdapter3(this.getContext(),R.layout.rice_middle,datas);
        //ListView lv = (ListView)
        riceList.setAdapter(adapter);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i <= response.length(); i++) {
//                    try {
//                        JSONObject object = response.getJSONObject(i);
//                        String name = object.getString("title");
//                        String price = object.getString("content");
//                        datas.add(new Datanews(name, price));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("error",error.getMessage());
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        Singleton.getmInstance(getContext()).addtoRequestque(jsonArrayRequest);
//        MyAdapternews adapter = new MyAdapternews(getActivity().getApplicationContext(), datas);
//        //ListView lv = (ListView)
//        riceList.setAdapter(adapter);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
