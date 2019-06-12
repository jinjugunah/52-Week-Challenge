package ke.co.safaricom.android.a52weekchallenge.view.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.safaricom.android.a52weekchallenge.R;
import ke.co.safaricom.android.a52weekchallenge.model.ContributionModel;

public class ContributionsAdapter extends RecyclerView.Adapter<ContributionsAdapter.ContributionViewHolder> {

    private List<ContributionModel> contributionModels;

    public ContributionsAdapter(List<ContributionModel> contributionModels) {
        this.contributionModels = contributionModels;
    }

    @NonNull
    @Override
    public ContributionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_contribution,parent,false);
        return new ContributionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContributionViewHolder holder, int position) {
        ContributionModel contributionModel = contributionModels.get(position);
        holder.tvAmount.setText(contributionModel.getAmount()+holder.itemView.getContext().getString(R.string.currency));
        holder.tvWeekCount.setText(holder.itemView.getContext().getString(R.string.week_label)+position);
        holder.tvTransactionCode.setText(contributionModel.getConfirmationCode());
        holder.tvTransactionDate.setText(contributionModel.getDate());
    }

    @Override
    public int getItemCount() {
        return contributionModels.size();
    }

    class ContributionViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.transactionDate)
        TextView tvTransactionDate;
        @BindView(R.id.tvTransactionCode)
        TextView tvTransactionCode;
        @BindView(R.id.tvAmount)
        TextView tvAmount;
        @BindView(R.id.tvWeekCount)
        TextView tvWeekCount;
        
        public ContributionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
