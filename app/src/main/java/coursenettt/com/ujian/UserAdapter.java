package coursenettt.com.ujian;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{

    ArrayList<Register>data;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vw = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_user,viewGroup,false);

        UserViewHolder rv = new UserViewHolder(vw);
        return rv;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

        Register r = data.get(i);
        userViewHolder.t1.setText(r.username);
        userViewHolder.t2.setText(r.phone);
        userViewHolder.t3.setText(r.id + "");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
