package wuqing.theone.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import wuqing.theone.R;
import wuqing.theone.table.Goods;
import wuqing.theone.table.ListViewForScrollView;
import wuqing.theone.table.TableAdapter;

/**
 * Created by 无情 on 2018/1/24.
 */

public class Fragment2 extends Fragment implements View.OnClickListener {
    private View mView;
    private List<Goods> list;
    private TableAdapter adapter;
    private ListView tableListView;
    private ListViewForScrollView mListViewForScrollView;
    private Button btnAscending;
    private Button btnDescending;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment2, null);


        ViewGroup tableTitle = (ViewGroup) mView.findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));

        btnAscending = (Button) mView.findViewById(R.id.btn_ascending);
        btnDescending = (Button) mView.findViewById(R.id.btn_descending);
        btnAscending.setOnClickListener(this);
        btnDescending.setOnClickListener(this);
        list = new ArrayList<Goods>();
        list.add(new Goods("01", "伊利婴儿加盖奶粉110ml", "982323423232", 34, 42, 23));
        list.add(new Goods("02", "鱼翅", "31312323223", 34, 41, 56));
        list.add(new Goods("03", "农夫山泉", "12", 34, 12, 34));
        list.add(new Goods("04", "飞天茅台0", "12333435445", 34, 19, 34));
        list.add(new Goods("05", "农家小菜", "34523", 34, 17, 23));
        list.add(new Goods("06", "飞天消费菜", "345456", 34, 23, 34));
        list.add(new Goods("07", "旺仔小牛奶", "2344", 34, 73, 62));
        list.add(new Goods("08", "旺旺", "23445", 34, 12, 35));
        list.add(new Goods("09", "达利园超时牛奶", "3234345", 34, 27, 23));
        Log.d("MainActivity", "List为------>" + list);

//        tableListView = (ListView) findViewById(R.id.list);
        mListViewForScrollView = (ListViewForScrollView) mView.findViewById(R.id.ly_list);
        adapter = new TableAdapter(getActivity(), list);

        mListViewForScrollView.setAdapter(adapter);
        return mView;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_descending:
                Collections.reverse(list);
                mListViewForScrollView.setAdapter(adapter);
                break;
            case R.id.btn_ascending:
                Collections.sort(list);
                mListViewForScrollView.setAdapter(adapter);
                break;

        }
        return;
    }
}
