package estgf.ipp.pt.cmu.Entities.Result;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import estgf.ipp.pt.cmu.R;
import estgf.ipp.pt.cmu.Utilities.RecyclerViewItemClickListener;
import estgf.ipp.pt.cmu.Utilities.SearchLayoutManager;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private Context context;
    private ArrayList<Result> list;
    private boolean isSet;
    private int EmptyResponseCount;
    private SearchLayoutManager searchLayoutManager;

    public ResultAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        this.isSet = false;
        this.EmptyResponseCount = 0;
        this.searchLayoutManager = new SearchLayoutManager(R.id.loadingPanel, R.id.ErrorMessage, R.id.loadingBar, this.context);
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

        resultViewHolder.setRecyclerViewItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<Result> listToAdd) {
        if (listToAdd != null) {
            if (!isSet) {
                this.searchLayoutManager.MakeLoadingPanelGone();
                isSet = true;
            }

            list.addAll(listToAdd);
            notifyDataSetChanged();
        }

    }

    public boolean isSet() {
        return isSet;
    }

    public void cleanList() {
        if (list != null) {
            list.clear();
            isSet = false;
        }
    }

    public void emptyResponse() {
        this.EmptyResponseCount++;
        if (EmptyResponseCount >= 3) {
            this.searchLayoutManager.DisplayErrorMessage("Results Not Found");
        }
    }
}
