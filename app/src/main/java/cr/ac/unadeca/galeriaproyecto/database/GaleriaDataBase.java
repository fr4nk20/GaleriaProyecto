package cr.ac.unadeca.galeriaproyecto.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by pc on 11/4/2018.
 */
@Database(name=GaleriaDataBase.db_name, version= GaleriaDataBase.version)
public class GaleriaDataBase {
    public static final String db_name="galeriaDB";
    public static final int version = 1;
}
