package wuqing.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 无情 on 2018/1/22.
 */

public class ContactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contactsLayout = inflater.inflate(R.layout.contacts_layout,
                container, false);
        return contactsLayout;
    }

}
