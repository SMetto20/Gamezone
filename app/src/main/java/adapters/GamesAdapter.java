package adapters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.thegamezone.GamesActivity;
import com.moringaschool.thegamezone.GamesListResponse;
import com.moringaschool.thegamezone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.MyViewholder> implements Filterable {

    Context context;
    List<GamesListResponse> gamesList;



    public GamesAdapter(Context context, List<GamesListResponse> gamesList) {
        this.context = context;
        this.gamesList = gamesList;


    }

   @Override
   public Filter getFilter(){

       return gamesFilter;
   }
   private Filter gamesFilter = new Filter() {

       @Override
       protected FilterResults performFiltering(CharSequence charSequence) {
           Log.e("charactersequence", String.valueOf(gamesList.size()));
           List<GamesListResponse> filterdList = new ArrayList<>();
           if (charSequence == null || charSequence.length() == 0) {
               filterdList.addAll(gamesList);

           } else {
               String filterPattern = charSequence.toString().toLowerCase();
               for (GamesListResponse item : gamesList) {
                   if (item.getTitle().toLowerCase().contains(filterPattern.toLowerCase())) {
                       filterdList.add(item);

                   }
               }
           }
          FilterResults results = new FilterResults();
           results.values = filterdList;
           return results;
       }


       @Override
       protected void publishResults(CharSequence charSequence, FilterResults results) {
           gamesList.clear();
           gamesList.addAll((Collection<? extends GamesListResponse>) results.values);
           notifyDataSetChanged();

       }
   };


    @NonNull
    @Override
    public GamesAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.game_list_item,parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesAdapter.MyViewholder holder, int position) {
    GamesListResponse gamesListResponse = gamesList.get(position);
    holder.gamename.setText(gamesListResponse.getTitle());
    holder.description.setText(gamesListResponse.getShortDescription());
    holder.date.setText(gamesListResponse.getReleaseDate());
    Picasso.get().load(gamesListResponse.getThumbnail()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }
    public static class MyViewholder extends RecyclerView.ViewHolder{
        TextView gamename, description, date, gameTextView;
        ImageView image;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            gamename = itemView.findViewById(R.id.gameNameTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            date = itemView.findViewById(R.id.dateTextView);
            image = itemView.findViewById(R.id.gameImageView);

        }
    }
}
