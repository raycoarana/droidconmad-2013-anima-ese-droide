package es.medianet.droidcon;

import android.app.Fragment;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Rayco on 6/12/13.
 */
public class ThirdExampleFragment extends Fragment implements View.OnClickListener {

    TransitionManager mTransitionManager;
    ViewGroup         mSceneRoot;
    Scene             mScene1, mScene2, mScene3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.example_third_layout, container, false);

        mSceneRoot = (FrameLayout)view.findViewById(R.id.content);

        view.findViewById(R.id.scene_1)
            .setOnClickListener(this);
        view.findViewById(R.id.scene_2)
            .setOnClickListener(this);
        view.findViewById(R.id.scene_3)
            .setOnClickListener(this);

        //getSceneForLayout tiene un bug, al cachear el sceneRoot, lo que hace que al salir y 
        //volver a entrar en el fragment, las animaciones dejen de funcionar
        
        mScene1 = Scene.getSceneForLayout(mSceneRoot, R.layout.example_third_scene_1, getActivity());
        mScene2 = Scene.getSceneForLayout(mSceneRoot, R.layout.example_third_scene_2, getActivity());
        mScene3 = Scene.getSceneForLayout(mSceneRoot, R.layout.example_third_scene_3, getActivity());
        mTransitionManager = TransitionInflater.from(getActivity())
                                               .inflateTransitionManager(R.transition.complex_transition_manager, mSceneRoot);

        mTransitionManager.transitionTo(mScene1);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scene_1:
                mTransitionManager.transitionTo(mScene1);
                break;
            case R.id.scene_2:
                mTransitionManager.transitionTo(mScene2);
                break;
            case R.id.scene_3:
                mTransitionManager.transitionTo(mScene3);
                break;
        }
    }
}