package coursenettt.com.ujian;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserViewHolder extends RecyclerView.ViewHolder{


    TextView t1;
    TextView t2;
    TextView t3;

    public UserViewHolder(@NonNull final View itemView) {
        super(itemView);
        t1 = (TextView)itemView.findViewById(R.id.t1);
        t2 = (TextView)itemView.findViewById(R.id.t2);
        t3 = (TextView)itemView.findViewById(R.id.t3);

    }
}
