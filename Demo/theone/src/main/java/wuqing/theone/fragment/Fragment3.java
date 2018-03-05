package wuqing.theone.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wuqing.theone.R;

/**
 * Created by 无情 on 2018/1/24.
 */

public class Fragment3 extends Fragment {
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater .inflate(R.layout.fragment3,container,false);
        }
        return  mView;
    }

}
