package com.iamasoldier6.fragtabdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Iamasoldier6 on 9/1/16.
 */
public class ContactsFragment extends Fragment {

    private View contactsFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contactsFragment = inflater.inflate(R.layout.fragment_contacts, container, false);
        return contactsFragment;
    }
}
