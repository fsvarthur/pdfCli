package org.example;

import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Merge {
    public static void main(String[] args) throws IOException {
        String pathString = "D:\\Notes" ;
        //
        List<String> fileNames = new ArrayList<>();
        DirectoryStream<Path> directoryStream;
        directoryStream = Files.newDirectoryStream(Paths.get(pathString));

        //declaring PDFMergeUtility
        PDFMergerUtility pdf = new PDFMergerUtility();
        pdf.setDestinationFileName(pathString+"\\merged.pdf");

        for(Path path : directoryStream){
            fileNames.add(path.toString());
        }
        fileNames.sort(String::compareToIgnoreCase);
        for(int i = 0; i < fileNames.size(); i ++){
            pdf.addSource(fileNames.get(i));
        }
        pdf.mergeDocuments();
    }
}
