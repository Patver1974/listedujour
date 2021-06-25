package be.bxl.formation.exercicelistedujour.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

public class CounterTask extends AsyncTask<Integer, Integer, String> {
    //region Event


    public interface EventCounter {
        void onProgressCount(int count);
    }


    private EventCounter eventCounter;


    public void setEventCounter(EventCounter eventCounter) {
        this.eventCounter = eventCounter;
    }
    //endregion


    @Override
    protected String doInBackground(Integer... valeursInitial) {
        if(valeursInitial == null || valeursInitial.length != 1) {
            throw new RuntimeException();
        }

        // On recupere la valeur initial
        int counter = valeursInitial[0];

        while(counter > 0) {
            publishProgress(counter);
            counter--;

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                Log.e("DEMO_ASYNC", "Erreur durant le sleep", e);
            }
        }

        // Envois du parametre de sortie
        return "BOUM !!!";
    }

    @Override
    protected void onPostExecute(String s) {

        if(eventCounter != null) {
            eventCounter.onProgressCount(0);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int count = values[0];

        if(eventCounter != null) {
            eventCounter.onProgressCount(count);
        }
    }
}