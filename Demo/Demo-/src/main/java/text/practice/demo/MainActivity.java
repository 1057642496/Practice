package text.practice.demo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, ScrollViewListener {
    private TextView tvContent;
    private Button btnDialog;
    private Spinner spDown;
    private ObservableScrollView mScrollView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mList;
    private List<Goods> list;
    private TableAdapter adapter;
    private ListView tableListView;
    private LinearLayoutForListView mLinearLayoutForListView;
    private ListViewForScrollView mListViewForScrollView;

    private SeekBar sb_normal;

    private RatingBar rb_normal;
    private TextView txt_cur;
    private TextView txt_rb;
    private EditText ip1;
    private EditText ip2;
    private EditText ip3;
    private EditText ip4;
    private int max = 255;
    private int min = 0;
    private Button sortAscendingOrder;
    private Button sortDescendingOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        mList = new ArrayList<>();
        mList.add("第一名");
        mList.add("第二名");
        mList.add("第三名");
        mList.add("第四名");
        mList.add("第五名");


        mAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, mList);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDown.setAdapter(mAdapter);

        spDown.setOnItemSelectedListener(this);


        /**
         * 功能：添加的对话框
         */
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this, R.style.dialog);
                dialog.setTitle("测试");
                dialog.setMessage("恭喜你成功了");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

            }
        });


        ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));

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
        mListViewForScrollView = (ListViewForScrollView) findViewById(R.id.list);
        adapter = new TableAdapter(this,list);

        mListViewForScrollView.setAdapter(adapter);



    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sort_ascending_order:
                Collections.sort(list);
                mListViewForScrollView.setAdapter(adapter);
                break;
            case R.id.sort_descending_order:
                Collections.reverse(list);
                mListViewForScrollView.setAdapter(adapter);
                break;
            default:
                break;
        }
    }

    private void bindViews() {

        tvContent = (TextView) findViewById(R.id.tv_content);
        btnDialog = (Button) findViewById(R.id.btn_dialog);
        spDown = (Spinner) findViewById(R.id.sp_down);
        ip1 = (EditText) findViewById(R.id.ip_1);
        ip2 = (EditText) findViewById(R.id.ip_2);
        ip3 = (EditText) findViewById(R.id.ip_3);
        ip4 = (EditText) findViewById(R.id.ip_4);
        setRegion(ip1);
        setRegion(ip2);
        setRegion(ip3);
        setRegion(ip4);
        sb_normal = (SeekBar) findViewById(R.id.sb_normal);
        txt_cur = (TextView) findViewById(R.id.txt_cur);
        txt_rb = (TextView) findViewById(R.id.txt_rb);
        rb_normal = (RatingBar) findViewById(R.id.rb_normal);
        sortAscendingOrder = (Button) findViewById(R.id.sort_ascending_order);
        sortDescendingOrder = (Button) findViewById(R.id.sort_descending_order);
        sortAscendingOrder.setOnClickListener(this);
        sortDescendingOrder.setOnClickListener(this);
        mScrollView = (ObservableScrollView) findViewById(R.id.scroll_view);
        mScrollView.setScrollViewListener(this);


        rb_normal.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                txt_rb.setText("您给他们的评分是" + rating + "分");
//                Toast.makeText(MainActivity.this,"rating" + String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });



        sb_normal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_cur.setText("当前的进度值为" + progress + "  /100");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "触碰SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "放开SeekBar", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * 功能设置EditText的取值范围
     */
    private void setRegion(final EditText ip) {
        ip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (start > 1) {
                    if (min != -1 && max != -1) {
                        int num = Integer.parseInt(s.toString());
                        if (num > max) {
                            s = String.valueOf(max);
                            ip.setText(s);
                        } else if (num < min)
                            s = String.valueOf(min);
                        return;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                //首数字不能是0
                String text = s.toString();
                int len = s.toString().length();
                if (len > 1 && text.startsWith("0")) {
                    s.replace(0, 1, "");
                }

                if (s != null && !s.equals(" ")) {
                    int value = 0;
                    try {
                        value = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        value = 0;
                    }
//                int value = Integer.parseInt(s.toString());

                    if (value > max) {
                        AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
                        dialog1.setTitle("提示");
                        dialog1.setCancelable(false);
                        dialog1.setMessage("无效的IP地址");
                        dialog1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog1.show();
                        ip.setText(String.valueOf(max));

                    }
                    return;
                }

            }
        });
    }


    /**
     * 功能：将选中的值付给textview
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String content = mAdapter.getItem(position);
        tvContent.setText("你获得的名次为" + content);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (scrollView == mScrollView){
            mScrollView.scrollTo(x,y);
        }
    }
}
