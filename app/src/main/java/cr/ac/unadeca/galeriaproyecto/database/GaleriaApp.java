package cr.ac.unadeca.galeriaproyecto.database;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by pc on 11/4/2018.
 */

public class GaleriaApp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        FlowManager.init(this);

    }
}


