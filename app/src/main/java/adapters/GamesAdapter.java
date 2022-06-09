package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.MyViewholder> {

    Context context;
    List<GamesListResponse> gamesList;

    public GamesAdapter(Context context, List<GamesListResponse> gamesList) {
        this.context = context;
        this.gamesList = gamesList;

    }




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
