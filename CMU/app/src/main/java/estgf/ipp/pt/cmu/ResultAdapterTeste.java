package estgf.ipp.pt.cmu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Result.Result;
import estgf.ipp.pt.cmu.Entities.Result.ResultType;
import estgf.ipp.pt.cmu.Entities.Result.ResultViewHolder;
import estgf.ipp.pt.cmu.Utilities.OnResultSelectedListener;
import estgf.ipp.pt.cmu.Utilities.RecyclerViewItemClickListener;
import estgf.ipp.pt.cmu.Utilities.SearchLayoutManager;

public class ResultAdapterTeste extends RecyclerView.Adapter<ResultViewHolder> {

    private Context context;
    private ArrayList<Result> list;


    public ResultAdapterTeste(Context context) {
        this.context = context;
        this.list = new ArrayList<>();

    }




    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View resultView = inflater.inflate(R.layout.row_layout, viewGroup, false);




        return new ResultViewHolder(resultView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder resultViewHolder, int i) {
        Result temp = list.get(i);

        TextView textView = resultViewHolder.textView;
        if (temp.getType().equals(ResultType.Recipe) || temp.getType().equals(ResultType.Product)) {
            textView.setText(temp.getTitle());
        } else {
            textView.setText(temp.getName());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Result result) {
        if (result!= null) {
            list.add(result);
            notifyItemChanged(list.size()-1);
        }

    }

}
