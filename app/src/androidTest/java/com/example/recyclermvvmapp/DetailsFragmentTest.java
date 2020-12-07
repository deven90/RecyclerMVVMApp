package com.example.recyclermvvmapp;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.example.recyclermvvmapp.ui.DetailsAdapter;
import com.example.recyclermvvmapp.ui.DetailsFragment;
import com.example.recyclermvvmapp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DetailsFragmentTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivity = new IntentsTestRule(MainActivity.class, true, false);

    @Test
    public void testShowError() {
        mActivity.launchActivity(new Intent());
        mActivity.getActivity().runOnUiThread(() -> {
            DetailsFragment detailsFragment = getFragment();
            detailsFragment.showError("Test Error");
        });
    }

    @Test
    public void testShowRecycler() {
        mActivity.launchActivity(new Intent());
        mActivity.getActivity().runOnUiThread(() -> {
            DetailsFragment detailsFragment = getFragment();
            detailsFragment.showRecycler();
        });
    }

    public DetailsFragment getFragment() {
        DetailsFragment fragment = (DetailsFragment) mActivity.getActivity().getSupportFragmentManager().findFragmentById(R.id.itemsFragment);
        return fragment;
    }

    @Test
    public void ensureListViewIsPresent() throws Exception {

        mActivity.launchActivity(new Intent());
        mActivity.getActivity().runOnUiThread(() -> {
            try {
                Thread.sleep(5000, 0);
                DetailsFragment detailsFragment = getFragment();
                View viewById = detailsFragment.getView().findViewById(R.id.recyclerDetails);
                assertThat(viewById, notNullValue());
                assertThat(viewById, instanceOf(RecyclerView.class));
                RecyclerView listView = (RecyclerView) viewById;
                RecyclerView.Adapter adapter = listView.getAdapter();
                assertThat(adapter, instanceOf(DetailsAdapter.class));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
