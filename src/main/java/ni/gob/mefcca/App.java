package ni.gob.mefcca;

import ni.gob.mefcca.documento.Archivo;
import ni.gob.mefcca.documento.DocumentoTexto;
import ni.gob.mefcca.impresora.Configuracion;
import ni.gob.mefcca.impresora.DispositivoImpresion;
import ni.gob.mefcca.impresora.ImpresoraDefault;
import org.apache.commons.io.FileUtils;

import javax.print.DocFlavor;

import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;

public class App {
    public static void main( String[] args ){
        //ImprimirTicket impresion = new ImprimirTicket("asdfasdfasdf");
        imprimirDocumentoLocal();
    }

    public static void imprimirDocumentoLocal(){
        int cantidadCopias =10000;
        MediaSizeName tipoHoja = MediaSizeName.NA_LEGAL;
        Archivo documento = new DocumentoTexto(FileUtils.getTempDirectoryPath()+"\\AdobeARM.log");
        Configuracion configuracionImpresora =
                new Configuracion.Builder(cantidadCopias, tipoHoja)
                        .setTipoDocumento(DocFlavor.INPUT_STREAM.AUTOSENSE)
                        .configurar();

        DispositivoImpresion impresora= new ImpresoraDefault(configuracionImpresora, documento);
        impresora.imprimir();
    }

    public static void crearDocumento(){
        DocumentoTexto.crearDocumentoTemporal("archivo.txt");
    }
}
