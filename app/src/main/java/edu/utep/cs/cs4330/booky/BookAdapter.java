package edu.utep.cs.cs4330.booky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter{

    private List<Book> bookList;

    public BookAdapter(List<Book> bookList){
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(itemView);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass = (ViewHolderClass) holder;

        Book book = bookList.get(position);
        viewHolderClass.title.setText(book.getTitle());
        viewHolderClass.author.setText(book.getAuthor());
        viewHolderClass.genre.setText(book.getGenre());
        viewHolderClass.isbn.setText(book.getISBN());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView title,author,genre,isbn;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitleTextView);
            author = itemView.findViewById(R.id.bookAuthorTextView);
            genre = itemView.findViewById(R.id.bookGenreTextView);
            isbn = itemView.findViewById(R.id.bookISBNTextView);
        }
    }
}
