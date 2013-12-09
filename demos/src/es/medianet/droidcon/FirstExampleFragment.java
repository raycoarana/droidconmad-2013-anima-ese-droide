package es.medianet.droidcon;

import android.app.Fragment;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Rayco on 6/12/13.
 */
public class FirstExampleFragment extends Fragment implements View.OnClickListener {

    LinearLayout mLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = (LinearLayout)inflater.inflate(R.layout.example_first_layout, container, false);

        mLayout.findViewById(R.id.button1)
               .setOnClickListener(this);
        mLayout.findViewById(R.id.button2)
               .setOnClickListener(this);
        return mLayout;
    }

    @Override
    public void onClick(View view) {
        TransitionManager.beginDelayedTransition(mLayout, new ChangeBounds());

        if(mLayout.indexOfChild(view) == 0) {
            mLayout.removeView(view);
            mLayout.addView(view);
        } else {
            mLayout.removeView(view);
            mLayout.addView(view, 0);
        }
    }
}