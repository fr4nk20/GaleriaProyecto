package cr.ac.unadeca.galeriaproyecto.database.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import cr.ac.unadeca.galeriaproyecto.database.GaleriaDataBase;

/**
 * Created by pc on 11/4/2018.
 */
@Table(database = GaleriaDataBase.class)
public class Imagen extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    public long id;
    //uri es como una url pero para un archivo local
    @Column
    public String uri;

    @Column
    public String nombre;


    @Column
    public String descripcion;
}
