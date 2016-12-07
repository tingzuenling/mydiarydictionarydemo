package com.myproject.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.myproject.Bean.TranslateResult;
import com.myproject.Bean.Vocabulary;
import com.myproject.DAO.DatabaseHelper;
import com.myproject.Httputils.Myapplication;
import com.myproject.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.myproject.Httputils.Httputils.APPID;
import static com.myproject.Httputils.Httputils.APPSECRET;
import static com.myproject.Httputils.Httputils.URL_TRANSLATE;

/**
 * Created by D1ngZenL1ng on 2016/11/20.
 */

public class DictionaryFragment extends Fragment {
    private AutoCompleteTextView autoinput;
    private TextView content;
    private Button btn_send;
    View view;
    private List<String> mList;
    private ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dictionaryfragment, container, false);
        initView();
        return view;
    }

    private void initView() {
        autoinput = (AutoCompleteTextView) view.findViewById(R.id.autotext);
        btn_send = (Button) view.findViewById(R.id.btn_send);
        content = (TextView) view.findViewById(R.id.content);

//        从数据库查询vocabulary,自动补全，但查找未完善
       /* final DatabaseHelper mHelper=new DatabaseHelper(getContext());
        List<String> stringList=new ArrayList<String>();
        try {
          List<Vocabulary> vocabularyList= mHelper.getVocabularyDao().queryForAll();
            for (int i = 0; i <vocabularyList.size() ; i++) {
                stringList.add(vocabularyList.get(i).getVocabulary());
            }
            ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,stringList);
            autoinput.setAdapter(mAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String vocabulary = autoinput.getText().toString();
                autoinput.setText("");
                content.setText("");

                getexplains(vocabulary);
            }

        });
    }

    private void getexplains(final String vocabulary) {
        RequestQueue queue = Myapplication.getHttpRequestQueue();
        StringRequest request = new StringRequest(Request.Method.POST, URL_TRANSLATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                TranslateResult translateResult = gson.fromJson(s, TranslateResult.class);
                Object[] explains = translateResult.getShowapi_res_body().getBasic().getExplains();
                String explainstr = "";
                if (explains != null) {
                    for (int i = 0; i < explains.length; i++) {
                        char[] explain = explains[i].toString().toCharArray();
                        if ('[' == explain[0]) {
                            explainstr = new String(explain, 1, explain.length - 2);
                        } else {
                            explainstr = explains[i].toString();
                        }
                        content.append(explainstr + "\n");
                    }
                }

//                将数据存到数据库中，未完善
              /*  Vocabulary voca = new Vocabulary(vocabulary, explainstr);
                DatabaseHelper mHelper = new DatabaseHelper(getContext());
                try {
                Dao<Vocabulary,Integer>  dao=mHelper.getVocabularyDao();
                    dao.createOrUpdate(voca);
                } catch (SQLException e) {
                    e.printStackTrace();
                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("Dictionary", "onErrorResponse: 查询请求失败");
                content.setText("查询失败");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("showapi_appid", APPID);
                params.put("showapi_sign", APPSECRET);
                params.put("q", vocabulary);
                return params;
            }
        };
        queue.add(request);
    }

}
