package in.psapps.workout;

import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.widget.ListView;

public class WorkoutListFragment extends ListFragment{

    //interface to interact with activity
    static interface WorkoutListListener{
        void itemClicked(long id);
    }

    private WorkoutListListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        //String array on names
        String[] names = new String[Workout.workouts.length];
        for (int i=0;i<names.length;i++){
            names[i]=Workout.workouts[i].getName();
        }
        //ArrayAdapter for list frag
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                inflater.getContext(),android.R.layout.simple_list_item_1,names
        );
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //this is called when frag succesfully atched to activity
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (WorkoutListListener) activity;
    }

    //telling the listener when the item in list is clicked
    @Override
    public void onListItemClick(ListView l,View v,int position,long id){
        if(listener != null){
            listener.itemClicked(id);
        }
    }

}
