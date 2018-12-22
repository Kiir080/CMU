package estgf.ipp.pt.cmu.Entities.Result;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import estgf.ipp.pt.cmu.R;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private Context context;
    private ArrayList<Result> list;
    private boolean isSet;
    private int responseCount;

    public ResultAdapter(Context context){
        this.context=context;
        list= new ArrayList<Result>();
        this.isSet=false;
        this.responseCount=0;

    }

    public synchronized void setList(ArrayList<Result> list) {
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
        Result temp = list.get(i);
        isSet=true;
        TextView textView = resultViewHolder.textView;
       if(temp.getType().equals(ResultType.Recipe) || temp.getType().equals(ResultType.Product) ){
            textView.setText(temp.getTitle());
        }else{
            textView.setText(temp.getName());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<Result> x){

        if(x != null){

        if(!isSet){
            RelativeLayout relativeLayout = ((Activity) context).findViewById(R.id.loadingPanel);
            relativeLayout.setVisibility(View.GONE);

        }

        list.addAll(x);
        notifyDataSetChanged();
        }
        this.responseCount++;
    }

    public boolean isSet() {
        return isSet;
    }

    public void cleanList(){
        if(list != null){
            list.clear();
            isSet=false;

        }
    }
}
