package es.medianet.droidcon;

import android.app.Fragment;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rayco on 6/12/13.
 */
public class SecondExampleFragment extends Fragment implements View.OnClickListener {

  ViewGroup mSceneRoot;

  Scene     mScene1;
  Scene     mScene2;

  Scene     mCurrentScene;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mSceneRoot = (ViewGroup)inflater.inflate(R.layout.example_second_layout, container, false);

    // getSceneForLayout tiene un bug, al cachear el sceneRoot, lo que hace que
    // al salir y volver a entrar en el fragment, las animaciones dejen de funcionar

    mScene1 = new Scene(mSceneRoot, (ViewGroup) inflater.inflate(R.layout.example_second_layout, mSceneRoot, false));
    mScene2 = new Scene(mSceneRoot, (ViewGroup) inflater.inflate(R.layout.example_second_layout_final_scene, mSceneRoot, false));

    Runnable clickAttach = new Runnable() {

      @Override
      public void run() {
        mCurrentScene.getSceneRoot().findViewById(R.id.button1).setOnClickListener(SecondExampleFragment.this);
      }
    };

    mScene1.setEnterAction(clickAttach);
    mScene2.setEnterAction(clickAttach);

    mCurrentScene = mScene1;

    mSceneRoot.findViewById(R.id.button1).setOnClickListener(SecondExampleFragment.this);

    return mSceneRoot;
  }

  @Override
  public void onClick(View view) {
    if (mScene1 == mCurrentScene) {
      mCurrentScene = mScene2;
    } else {
      mCurrentScene = mScene1;
    }

    TransitionSet transitionSet = new TransitionSet();
    transitionSet.addTransition(new ChangeBounds().excludeTarget(R.id.button3, true));
    transitionSet.addTransition(new Fade().addTarget(R.id.button3));
    TransitionManager.go(mCurrentScene, transitionSet);

  }
}