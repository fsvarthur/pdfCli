package org.cli;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "merge", version = "merge 0.1", description = "Merge documents passed by")
public class Merge implements Callable<String> {
    @Parameters(index = "0", description = "The source directory")
    private String pathString;

    @Parameters(index = "1", description = "The file name without extensions")
    private String name;

    @Override
    public String call() throws Exception {
        //String pathString = "D:\\Notes" ;
        List<String> fileNames = new ArrayList<>();
        DirectoryStream<Path> directoryStream;
        directoryStream = Files.newDirectoryStream(Paths.get(pathString));

        //declaring PDFMergeUtility
        PDFMergerUtility pdf = new PDFMergerUtility();
        pdf.setDestinationFileName(pathString+"\\"+name+".pdf");
        //pdf.setDestinationFileName(pathString+"\\merged.pdf");

        for(Path path : directoryStream){
            fileNames.add(path.toString());
        }
        fileNames.sort(String::compareToIgnoreCase);
        for(int i = 0; i < fileNames.size(); i ++){
            pdf.addSource(fileNames.get(i));
        }
        pdf.mergeDocuments(null);
        return null;
    }

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new Merge()).execute(args);
        System.exit(exitCode);
    }
}
