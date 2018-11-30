package estgf.ipp.pt.cmu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private Context context;
    private ArrayList<RecipeResult> list;
    private boolean isSet;

    public ResultAdapter(Context context){
        this.context=context;
        list= new ArrayList<RecipeResult>();
        this.isSet=false;

    }

    public synchronized void setList(ArrayList<RecipeResult> list) {
        this.list= list;
        this.isSet=true;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context= viewGroup.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);

        View resultView= inflater.inflate(R.layout.row_layout,viewGroup,false);

        return new ResultViewHolder(resultView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder resultViewHolder, int i) {
        RecipeResult temp = list.get(i);
        isSet=true;
        TextView textView = resultViewHolder.textView;
       if(temp.getType().equals(ResultType.Recipe)){
            textView.setText(temp.getTitle());
        }else{
            textView.setText(temp.getName());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<RecipeResult> x){
        list.addAll(x);
        notifyDataSetChanged();
    }

    public boolean isSet() {
        return isSet;
    }
}
