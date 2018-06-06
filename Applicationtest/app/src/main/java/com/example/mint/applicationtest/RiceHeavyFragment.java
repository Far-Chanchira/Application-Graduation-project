package com.example.mint.applicationtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mint.applicationtest.DataClass.Dataheavy;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mint on 8/6/2017.
 */

public class RiceHeavyFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String Url = "http://www.zp10290.tld.122.155.18.18.no-domain.name/select_riceheavy.php";
    private List<Dataheavy> datas = new ArrayList<>();
    View v;
    ListView riceList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.rice_heavy,null);
        riceList = v.findViewById(R.id.rice_listview);
        List<JSONObject> datas= WebApiRequest.start("http://www.zp10290.tld.122.155.18.18.no-domain.name/select_riceheavy.php","");
        //RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        //requestQueue.add(jsonArrayRequest);

        //MyAdaptermiddle adapter = new MyAdaptermiddle(getActivity().getApplicationContext(), datas);
        MyAdapter2 adapter = new MyAdapter2(this.getContext(),R.layout.rice_middle,datas);
        //ListView lv = (ListView)
        riceList.setAdapter(adapter);
        riceList.setOnItemClickListener(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i <= response.length(); i++) {
//                    try {
//                        JSONObject object = response.getJSONObject(i);
//                        String name = object.getString("ricename");
//                        String price = object.getString("price");
//                        String url = object.getString("img");
//                        datas.add(new Dataheavy(name, price, "http://www.zp10290.tld.122.155.18.18.no-domain.name/admin/rice/myfile/" + url));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        requestQueue.add(jsonArrayRequest);
//
//       MyAdapterheavy adapter = new MyAdapterheavy(getActivity().getApplicationContext(), datas);
//        //ListView lv = (ListView)
//        riceList.setAdapter(adapter);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

       /* JSONObject data = (JSONObject)adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this.getContext(),Show.class);
        intent.putExtra("data",data.toString());
        this.getContext().startActivity(intent);
*/
    }
}
