package in.psapps.workout;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment{

    private long workoutId;

    //for stopwatch frsg transaction
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        if(savedInstanceState != null){
            workoutId = savedInstanceState.getLong("workoutId");
        }
        else {
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            StopWatchFragment stopWatchFragment = new StopWatchFragment();
            ft.replace(R.id.stopwatch_container, stopWatchFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        return inflater.inflate(R.layout.fragment_workout_detail,container,false);
    }

    @Override
    public void onStart(){
        super.onStart();
        //get root view i.e mainactivity
        View view = getView();
        if(view!=null){
            TextView title=(TextView)view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            TextView description=(TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstaceState){
        savedInstaceState.putLong("workoutId", workoutId);
    }

    public void setWorkout(long id){
        this.workoutId=id;
    }

}
