package com.iamasoldier6.fragtabviewpagerdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author: Iamasoldier6
 * @date: 05/01/2017
 */

public class ContactsFragment extends Fragment {

    private View contactsFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contactsFragment = inflater.inflate(R.layout.text_contacts, container, false);
        return contactsFragment;
    }
}
