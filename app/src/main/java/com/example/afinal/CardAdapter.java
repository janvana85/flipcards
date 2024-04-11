package com.example.afinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CardAdapter extends ArrayAdapter<Card> {

    private Context context;
    private List<Card> cardList;

    public CardAdapter(Context context, List<Card> cardList) {
        super(context, R.layout.item_card, cardList);
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        }

        final Card card = cardList.get(position);

        TextView textCard = itemView.findViewById(R.id.textFront);
        ImageView imageDelete = itemView.findViewById(R.id.btnDelete);

        textCard.setText(card.getFrontText() + " |  " +card.getBackText());

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the card from the database
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete(DatabaseHelper.TABLE_CARDS, "id=?", new String[]{String.valueOf(card.getId())});
                db.close();

                // Remove the card from the list and notify the adapter
                cardList.remove(card);
                notifyDataSetChanged();
            }
        });

        return itemView;
    }
}
