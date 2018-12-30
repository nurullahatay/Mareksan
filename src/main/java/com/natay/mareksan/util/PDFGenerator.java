package com.natay.mareksan.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.natay.mareksan.model.Order;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class PDFGenerator {

    public static ByteArrayInputStream customerPDFReport(Order order) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();
        // Add Text to PDF file ->
        Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        Paragraph para = new Paragraph("Fatura", font);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);
        document.add(Chunk.NEWLINE);

        Paragraph paragraph = new Paragraph(String.format("%s/%s\n", "Mareksan", order.getId())+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) ,font);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(4);
        // Add PDF Table Header ->
        Stream.of("ID", "Sipariş adı", "Adet", "Tutar")
                .forEach(headerTitle -> {
                    PdfPCell header = new PdfPCell();
                    Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                    header.setBackgroundColor(BaseColor.WHITE);
                    header.setHorizontalAlignment(Element.ALIGN_LEFT);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(headerTitle, headFont));
                    table.addCell(header);
                });


        PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(order.getId())));
        table.addCell(setCellProperties(idCell));

        PdfPCell orderNameCell = new PdfPCell(new Phrase(order.getOrderName()));
        table.addCell(setCellProperties(orderNameCell));

        PdfPCell amountCell = new PdfPCell(new Phrase(String.valueOf(order.getAmount())));
        table.addCell(setCellProperties(amountCell));

        PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf(order.getPrice())));
        table.addCell(setCellProperties(priceCell));

        document.add(table);
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private static PdfPCell setCellProperties(PdfPCell pdfPCell){
        pdfPCell.setPaddingLeft(4);
        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return pdfPCell;
    }
}