package com.example.recyclermvvmapp;

import androidx.test.rule.ActivityTestRule;

import com.example.recyclermvvmapp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void getClassName() {
        MainActivity activity = Mockito.mock(MainActivity.class);
        assertNotNull(activity.getName(), is("MainActivity"));
    }
}