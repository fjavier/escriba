package ni.gob.mefcca;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.OrientationRequested;


public class ImprimirTicket implements Printable{
    private String codigoInventario;


    public String getCodigoInventario() {
        return codigoInventario;
    }


    public void setCodigoInventario(String codigoInventario) {
        this.codigoInventario = codigoInventario;
    }

    public ImprimirTicket(String codigoInventario) {
        this.setCodigoInventario(codigoInventario);
        Imprimir();
    }


    public void Imprimir() {

        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(OrientationRequested.PORTRAIT);
        aset.add(new Copies(1));
        aset.add(new JobName("Codigo", null));

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(this);
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();


        try {
            pj.setPrintService(printService);
            pj.print(aset);
        } catch (PrinterException pe) {
            System.err.println(pe);
        }
    }


    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex == 0) {
            Graphics2D g2d= (Graphics2D)g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            g2d.setColor(Color.black);
            g2d.drawString(this.getCodigoInventario(), 5, 10);

            return Printable.PAGE_EXISTS;
        } else {
            return Printable.NO_SUCH_PAGE;
        }
    }

}