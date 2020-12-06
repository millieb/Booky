package edu.utep.cs.cs4330.booky;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends ArrayAdapter {

    private Activity mContext;
    private List<Book> bookList;

    public BookAdapter(Activity mContext, List<Book> bookList){
        super(mContext, R.layout.item, bookList);
        this.mContext = mContext;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.item,null,true);

        TextView title = listItemView.findViewById(R.id.bookTitleTextView);
        TextView author = listItemView.findViewById(R.id.bookAuthorTextView);
        TextView genre = listItemView.findViewById(R.id.bookGenreTextView);
        TextView isbn = listItemView.findViewById(R.id.bookISBNTextView);

        Book book = bookList.get(position);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        genre.setText(book.getGenre());
        isbn.setText(book.getISBN());

        return listItemView;
    }
}
