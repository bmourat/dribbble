package ru.bmourat.dribbble.helper;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by bmourat on 14.08.16.
 */

public class FragmentStack {
    private FragmentManager manager;
    private int containerId;
    Random random;
    private FragmentManager.OnBackStackChangedListener listener;

    public static final String SAVE_TAG_FOR_ARGUMENTS = "_SAVE_TAG_FOR_ARGUMENTS";

    public FragmentStack(FragmentManager manager, int containerId) {
        this.manager = manager;
        this.containerId = containerId;
        random = new Random(System.currentTimeMillis());
    }

    public void showDialogFragment(DialogFragment dialog){
        dialog.show(manager, setTag(dialog));
    }

    public void setOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener listener) {
        if (listener != null) manager.removeOnBackStackChangedListener(listener);
        this.listener = listener;
        manager.addOnBackStackChangedListener(listener);
    }

    /**
     * Pushes a fragment to the top of the stack.
     */
    public void push(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        String tag = setTag(fragment);
        if (peek() != null)
            fragment.getArguments().putString("_before", peek().getArguments().getString("_tag"));
        transaction.replace(containerId, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
        manager.executePendingTransactions();
    }

    public boolean isBackStackEmpty(){
       return manager.getBackStackEntryCount() < 1;
    }

    /**
     * Replaces entire stack contents with just one fragment.
     */
    public void replace(Fragment fragment) {
        popAll();
        manager.beginTransaction()
                .replace(containerId, fragment, setTag(fragment))
                .commit();
        manager.executePendingTransactions();
        if (listener != null) listener.onBackStackChanged();
    }

    /**
     * Pops the topmost fragment from the stack.
     */
    public boolean pop() {
        if (manager.getBackStackEntryCount() == 0)
            return false;
        manager.popBackStack();
        return true;
    }


    public void popReturnArgs(Bundle args) {
        String tag = peek().getArguments().getString("_before", null);
        if (tag == null) throw new RuntimeException("Can not find link to previous fragment");
        Fragment pre = manager.findFragmentByTag(tag);
        if (pre == null) throw new RuntimeException("Previous fragment not found");
        Bundle arguments = pre.getArguments();
        if (arguments == null) throw new RuntimeException("Can not set arguments");
        removeSystemTags(args);
        args.putBoolean(SAVE_TAG_FOR_ARGUMENTS, true);
        arguments.putAll(args);
        manager.popBackStack();
    }

    private void popAll() {
        for (int i = manager.getBackStackEntryCount(); i > 0; i--) manager.popBackStackImmediate();
    }

    /**
     * Returns the topmost fragment in the stack.
     */
    public Fragment peek() {
        return manager.findFragmentById(containerId);
    }

    private String setTag(Fragment fragment) {
        String tag = random.nextLong() + "" + System.nanoTime();
        if (fragment.getArguments() == null) {
            fragment.setArguments(new Bundle());
        }
        fragment.getArguments().putString("_tag", tag);
        return tag;
    }

    public static void removeSystemTags(Bundle bundle) {
        if (bundle == null) return;
        Set<String> keys = new HashSet<>(bundle.keySet());
        for (String key : keys) if (key.startsWith("_")) bundle.remove(key);
    }
}
