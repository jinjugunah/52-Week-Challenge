package ke.co.safaricom.android.a52weekchallenge.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ke.co.safaricom.android.a52weekchallenge.R;
import ke.co.safaricom.android.a52weekchallenge.model.ContributionModel;
import ke.co.safaricom.android.a52weekchallenge.util.GeneralHelper;
import ke.co.safaricom.android.a52weekchallenge.util.NavigationHelper;
import ke.co.safaricom.android.a52weekchallenge.util.ToastService;
import ke.co.safaricom.android.a52weekchallenge.view.adpter.ContributionsAdapter;
import ke.co.safaricom.android.a52weekchallenge.viewmodel.ContributionViewModel;

public class FragmentHome extends Fragment {
    public static FragmentHome newInstance(){
        return new FragmentHome();
    }

    private View mainView;
    @BindView(R.id.rcvContributions)
    RecyclerView rcvContributions;


    @BindView(R.id.tvNextContribution)
    TextView tvNextContribution;

    @BindView(R.id.tvTotalContribution)
    TextView tvTotalContribution;

    @BindView(R.id.menu_contribute)
    FloatingActionButton fabContribute;
    ContributionViewModel contributionViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_home,container,false);
        ButterKnife.bind(this,mainView);

        return mainView;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    @OnClick(R.id.menu_contribute)
    void contribute (View view){
        if(contributionViewModel==null){
            contributionViewModel = ViewModelProviders.of(FragmentHome.this).get(ContributionViewModel.class);
        }
    contributionViewModel.getContributions(getContext()).observe(this,contributions->{
        if(contributions==null){
            new NavigationHelper().startFragment(getActivity(),FragmentContribution.newInstance(),FragmentHome.this);
        }
        else{
            if(contributions.size()>0) {
                double lastAmount = contributions.get(contributions.size() - 1).getAmount();
                double startAmount = contributions.get(0).getAmount();
                double newAmount = startAmount+lastAmount;
                ContributionModel contributionModel = new ContributionModel(GeneralHelper.generateString(),newAmount,"08-06-2-2019");
                contributionViewModel.makeContribution(contributionModel,getContext()).observe(this,result->{
                    if(result){
                        ToastService.displayToast(getContext(),"Deposit successfull");
                        init();
                    }
                    else{
                        ToastService.displayToast(getContext(),"Deposit Failed");
                    }
                });
            }


        }
    });
    }


    private void init(){
        rcvContributions.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(contributionViewModel==null){
            contributionViewModel = ViewModelProviders.of(this).get(ContributionViewModel.class);
        }

        contributionViewModel.getContributions(getContext()).observe(this, contributions->{
            if(contributions!=null){
                rcvContributions.setAdapter(new ContributionsAdapter(contributions));
                if(contributions.size()>0) {
                    double lastAmount = contributions.get(contributions.size() - 1).getAmount();
                    double startAmount = contributions.get(0).getAmount();
                    double totalAmount = 0;
                    double newAmount = startAmount + lastAmount;

                    int length = contributions.size();
                    for(int i=0;i<length;i++){
                        totalAmount +=contributions.get(i).getAmount();
                    }
                    tvTotalContribution.setText(totalAmount+" KES");
                    tvNextContribution.setText(newAmount+" KES");


                }
            }

        });
    }


}


