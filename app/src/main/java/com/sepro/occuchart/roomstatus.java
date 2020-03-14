package com.sepro.occuchart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
public class roomstatus extends AppCompatActivity {

    @BindView(R.id.roomlist)
    RecyclerView roomlist;
    private FirebaseFirestore db;
    private static final String TAG = "Database";
    private FirestoreRecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomstatus);
        ButterKnife.bind(this);
        init();
        getRoomlist();

    }

    private void init(){
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        roomlist.setLayoutManager(linearLayoutManager);
        db = FirebaseFirestore.getInstance();
    }

    public class RoomsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvtypeee)
        TextView texttype;
        @BindView(R.id.tvavail)
        TextView textavailabile;
        @BindView(R.id.tvallotedto)
        TextView textallotedto;
        @BindView(R.id.tvroomnum)
        TextView textroomnum;
//        @BindView(R.id.textViewOptions)
//        TextView textoptions;


        public RoomsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    private void getRoomlist()
    {
        Query query = db.collection("rooms");

        FirestoreRecyclerOptions<roomprops> response = new FirestoreRecyclerOptions.Builder<roomprops>()
                .setQuery(query, roomprops.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<roomprops, RoomsHolder>(response) {
            @Override
            public void onBindViewHolder(RoomsHolder holder, int position, roomprops model) {
                holder.texttype.setText("Type: "+model.getType());
//                Log.d(TAG, "onBindViewHolder: "+model.getType());
                holder.textavailabile.setText("Availability: "+model.getAvailable()+"");
                if(model.getAvailable()==true)
                {
                    holder.textavailabile.setTextColor(Color.GREEN);
                }
                else
                {
                    holder.textavailabile.setTextColor(Color.RED);
                }
                holder.textallotedto.setText(model.getAlloted_to());
                holder.textroomnum.setText(model.getRoom_num());
//                holder.itemView.setOnClickListener(v -> {
//                   Snackbar.make(roomlist, model.getType()+", "+model.getAvailable()+" , "+model.getAlloted_to(), Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                });
            }

            @Override
            public RoomsHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext()).inflate(R.layout.list_item, group, false);
                RoomsHolder v2 = new RoomsHolder(view);

                return v2;
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };

        adapter.notifyDataSetChanged();
        roomlist.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}
