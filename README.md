**Proyecto Escriba.**

Libreria utilitaria para enviar un documento a imprimir.

```java
public class App {
    public static void main( String[] args ){
        imprimirDocumentoLocal();
    }

    public static void imprimirDocumentoLocal(){
        int cantidadCopias =1;
        MediaSizeName tipoHoja = MediaSizeName.NA_LETTER;
        Archivo documento = new DocumentoTexto(FileUtils.getTempDirectoryPath()+"\\prueba.txt");
        Configuracion configuracionImpresora =
                new Configuracion.Builder(cantidadCopias, tipoHoja)
                        .setTipoDocumento(DocFlavor.INPUT_STREAM.AUTOSENSE)
                        .configurar();

        DispositivoImpresion impresora= new ImpresoraDefault(configuracionImpresora, documento);
        impresora.imprimir();
    }
}

```