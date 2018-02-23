package ni.gob.mefcca.documento;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DocumentoTexto extends Archivo {
    private final static String slash = "\\";

    public DocumentoTexto(String url){
        this.abrirArchivo(url);
    }

    public DocumentoTexto(){}

    public static void crearDocumento(String ruta, String nombreArchivo) {
        try {
            String rutaCompleta = ruta.concat(slash).concat(nombreArchivo);
            File documento = new File(rutaCompleta);
            eliminarArchivo(documento);
            FileUtils.touch(documento);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarArchivo(File archivo){
        FileUtils.deleteQuietly(archivo);
    }

    public static void crearDocumentoTemporal(String nombreArchivo){
        crearDocumento(FileUtils.getTempDirectoryPath(),nombreArchivo);
    }
}
