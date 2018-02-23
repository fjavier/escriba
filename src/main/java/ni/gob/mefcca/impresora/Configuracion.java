package ni.gob.mefcca.impresora;

import javax.print.*;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;

public class Configuracion {
    private final DocFlavor tipoDocumento;
    private final int cantidadCopias;
    private final MediaSizeName tipoHoja;
    private final PrintRequestAttributeSet propiedades;

    public static class Builder {
        private DocFlavor tipoDocumento;
        private final PrintRequestAttributeSet propiedades = new HashPrintRequestAttributeSet();
        private final int cantidadCopias;
        private final MediaSizeName tipoHoja;

        public Builder(final int cantidadCopias, MediaSizeName tamanioHoja){
            this.cantidadCopias = cantidadCopias;
            this.tipoHoja = tamanioHoja;
            this.establecerCantidadCopias(this.cantidadCopias);
            this.establecerTamanioHoja(tipoHoja);
            tipoDocumento = null;
        }

        public Builder setTipoDocumento(final DocFlavor tipoDocumento){
            if (tipoDocumento==null){
                throw new IllegalArgumentException("TIPO DE DOCUMENTO NO PUEDE SER NULO");
            }
            this.tipoDocumento = tipoDocumento;
            return this;
        }

        public Builder agregarPropiedad(final Attribute propiedad){
            if (propiedad==null){
                throw new IllegalArgumentException("PROPIEDAD NO PUEDE SER NULO");
            }
            this.propiedades.add(propiedad);
            return this;
        }

        private void establecerCantidadCopias(final int cantidadCopias){
            if (cantidadCopias < 1){
                throw new IllegalArgumentException("CANTIDAD DE COPIAS DEBE SER MAYOR A 0");
            }
            this.agregarPropiedad(new Copies(cantidadCopias));
        }

        private void establecerTamanioHoja(final MediaSizeName tamanioHoja){
            if(tipoHoja == null){
                throw new IllegalArgumentException("TIPO DE HOJA NO DEBE SER NULO");
            }
            this.agregarPropiedad(tamanioHoja);
        }


        public Configuracion configurar(){
            return new Configuracion(this);
        }
    }

    private Configuracion(Builder builder){
        this.tipoDocumento = builder.tipoDocumento;
        this.cantidadCopias = builder.cantidadCopias;
        this.tipoHoja = builder.tipoHoja;
        this.propiedades = builder.propiedades;
    }

    public PrintRequestAttributeSet getPropiedades(){
        return this.propiedades;
    }

    public DocFlavor getTipoDocumento(){
        return  this.tipoDocumento;
    }
}
