package in.psapps.workout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

public class StopWatchFragment extends Fragment implements View.OnClickListener{
    //For No. of sec
    private int seconds=0;
    //To stop watch from running
    private boolean running;
    //To check if stop watch running before onStop() is called
    private boolean wasRunning;
    //to check which life cycle action of activity is running
    private static String msg;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceSate){
        View layout = inflater.inflate(R.layout.fragment_stop_watch,container,false);
        runTimer(layout);
        Button startButton = (Button) layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = (Button) layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = (Button) layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.start_button:
                onClickStart(view);
                break;
            case R.id.stop_button:
                onClickStop(view);
                break;
            case R.id.reset_button:
                onClickReset(view);
                break;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        wasRunning=running;
        running = false;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(wasRunning) {
            running = true;
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onStop(){
        super.onStop();
        wasRunning=running;
        running = false;
    }

    //Start the stopwatch when start_button is clicked
    public void onClickStart(View view){
        running=true;
    }

    //Stop the watch when stop_button is clicked
    public void onClickStop(View view){
        running=false;
    }

    //Reset the whatch when Reset_button is clicked
    public void onClickReset(View view){
        running=false;
        seconds=0;
    }

    //set the no of sec on timer
    private void runTimer(View view){
        final TextView timeview = (TextView) view.findViewById(R.id.time_view);
        //handling and updating time each sec in new thread
        final Handler timehandler = new Handler();
        timehandler.post(new Runnable() {
            @Override
            public void run() {
                //formating timeview to show time
                int hours = seconds / 3600;
                int minutes = seconds / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeview.setText(time);
                if (running) {
                    seconds++;
                }
                timehandler.postDelayed(this, 1000);
            }
        });
    }


}