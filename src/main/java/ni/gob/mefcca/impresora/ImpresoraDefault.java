package ni.gob.mefcca.impresora;


import ni.gob.mefcca.documento.Archivo;

import javax.print.*;

public class ImpresoraDefault extends DispositivoImpresion {

    public ImpresoraDefault(Configuracion configuracion, Archivo documentoImprimir){
        super(configuracion, documentoImprimir);
        this.dispositivo = PrintServiceLookup.lookupDefaultPrintService();
    }

    @Override
    public void imprimir() {
        try {
            if (this.documento.getArchivo() != null || this.configuracionDispositivo.getTipoDocumento()!=null){
                DocPrintJob trabajoImpresion =  this.dispositivo.createPrintJob();
                Doc documento = new SimpleDoc(this.documento.getArchivo(), this.configuracionDispositivo.getTipoDocumento(), null);
                ObservadorTrabajoImpresion observador = new ObservadorTrabajoImpresion(trabajoImpresion);
                trabajoImpresion.print(documento, this.configuracionDispositivo.getPropiedades());
                observador.esperarTerminarImpresion();
                this.documento.cerrarArchivo();
            }else{
                throw new IllegalArgumentException("Documento y Tipo de Documento Requeridos.");
            }

        } catch (PrintException e) {
            e.printStackTrace();
        }
    }
}
