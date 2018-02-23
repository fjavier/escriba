package ni.gob.mefcca.impresora;

import ni.gob.mefcca.documento.Archivo;

import javax.print.PrintService;

public abstract class DispositivoImpresion {
    Configuracion configuracionDispositivo;
    PrintService dispositivo;
    Archivo documento;

    public DispositivoImpresion(Configuracion configuracion, Archivo documento){
        this.aplicarConfiguracion(configuracion);
        this.setDocumento(documento);
    }

    private void aplicarConfiguracion(Configuracion configuracion){
        this.configuracionDispositivo = configuracion;
    }

    private void setDocumento(Archivo documento){
        this.documento = documento;
    }

    public abstract void imprimir();
}