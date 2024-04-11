package com.example.afinal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CardFragment extends Fragment {

    private String frontText;
    private String backText;
    private boolean isFront = true;

    public CardFragment(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        final FrameLayout cardContainer = view.findViewById(R.id.cardContainer);
        final TextView frontTextView = view.findViewById(R.id.frontTextView);
        final TextView backTextView = view.findViewById(R.id.backTextView);

        frontTextView.setText(frontText);
        backTextView.setText(backText);

        // Set onClickListener for frontTextView to toggle to backText with animation
        frontTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFront) {
                    flipTextView(frontTextView, backTextView);
                    isFront = false;
                } else {
                    flipTextView(backTextView, frontTextView);
                    isFront = true;
                }
            }
        });

        // Set onClickListener for backTextView to toggle to frontText with animation
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFront) {
                    flipTextView(frontTextView, backTextView);
                    isFront = false;
                } else {
                    flipTextView(backTextView, frontTextView);
                    isFront = true;
                }
            }
        });

        return view;
    }

    private void flipTextView(final TextView visibleTextView, final TextView invisibleTextView) {
        visibleTextView.animate().alpha(0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                visibleTextView.setVisibility(View.GONE);
                invisibleTextView.setAlpha(0f);
                invisibleTextView.setVisibility(View.VISIBLE);
                invisibleTextView.animate().alpha(1f).setDuration(200).setListener(null);
            }
        });
    }
}
