package ni.gob.mefcca.documento;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Archivo {
    private FileInputStream archivo;

    public void abrirArchivo(String ruta){
            try {
                this.archivo = new FileInputStream(new File(ruta));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public void cerrarArchivo(){
            try {
                this.archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileInputStream getArchivo(){
        return this.archivo;
    }
}
