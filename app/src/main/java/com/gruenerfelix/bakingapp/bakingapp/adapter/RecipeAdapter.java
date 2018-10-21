package com.gruenerfelix.bakingapp.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gruenerfelix.bakingapp.bakingapp.R;
import com.gruenerfelix.bakingapp.bakingapp.RecipeDetailActivity;
import com.gruenerfelix.bakingapp.bakingapp.model.BakingProcess;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    //Refererence to MainActivity
    private Context mContext;
    private List<BakingProcess> recipeList = new ArrayList<>();

    public RecipeAdapter(Context mContext, List<BakingProcess> recipeList) {
        this.recipeList = recipeList;
        this.mContext = mContext;
    }

    //One Card as View gets inflated
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);

        return new MyViewHolder(view);
    }


    //Binds Data to that Card
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BakingProcess recipe = recipeList.get(position);
        holder.title.setText(recipe.getName());

    }


    @Override
    public int getItemCount(){
        return recipeList.size();

    }
    //inner class of RecyclerView. Adapter
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        ImageView image;

        public MyViewHolder(View view){

            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            image = (ImageView) view.findViewById(R.id.recipeImage);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        BakingProcess clickedDataItem = recipeList.get(pos);

                        Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                        intent.putExtra("Recipe", clickedDataItem);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
