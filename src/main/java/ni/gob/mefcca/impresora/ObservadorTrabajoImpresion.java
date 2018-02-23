package ni.gob.mefcca.impresora;

import javax.print.DocPrintJob;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

public class ObservadorTrabajoImpresion {
    private boolean impresionFinalizada;

    public ObservadorTrabajoImpresion(DocPrintJob trabajoImpresion) {
        trabajoImpresion.addPrintJobListener(new PrintJobAdapter() {
            @Override
            public void printDataTransferCompleted(PrintJobEvent printJobEvent) {
                allDone();
            }

            @Override
            public void printJobCompleted(PrintJobEvent printJobEvent) {
                allDone();
            }

            @Override
            public void printJobFailed(PrintJobEvent printJobEvent) {
                allDone();
            }

            void allDone() {
                synchronized (ObservadorTrabajoImpresion.this) {
                    impresionFinalizada = true;
                    ObservadorTrabajoImpresion.this.notify();
                }
            }
        });
    }

        public synchronized void esperarTerminarImpresion(){
            try{
                while (!impresionFinalizada){
                    wait();
                }
            }catch (InterruptedException exception){
                exception.printStackTrace();
            }

    }

}
