package in.psapps.workout;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            Toast.makeText(MainActivity.this, "TAB", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(MainActivity.this, "Phone", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemClicked(long id) {
        //The code to set the detail will go here

        //For Tab
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            details.setWorkout(id);
            ft.replace(R.id.fragment_container, details);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        //for Phone
        else {
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID,(int) id);
            startActivity(intent);
        }
    }
}
