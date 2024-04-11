package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

public class CardPagerAdapter extends FragmentPagerAdapter {

    private List<Card> cardList;

    public CardPagerAdapter(@NonNull FragmentManager fm, List<Card> cardList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Card card = cardList.get(position);
        return new CardFragment(card.getFrontText(), card.getBackText());
    }

    @Override
    public int getCount() {
        return cardList.size();
    }
}
