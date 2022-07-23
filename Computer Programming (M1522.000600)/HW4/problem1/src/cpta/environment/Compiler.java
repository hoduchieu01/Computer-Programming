package cpta.environment;

import cpta.exceptions.CompileErrorException;
import cpta.exceptions.FileSystemRelatedException;
import cpta.exceptions.InvalidFileTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Compiler {
    public void compile(String filePath)
            throws CompileErrorException, InvalidFileTypeException, FileSystemRelatedException {
        try {
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                throw new FileSystemRelatedException("File does not exist.");
            }

            String dirPath = file.getParent();
            String fileNameWithExtension = file.getName();
            String[] pieces = fileNameWithExtension.split("\\.");
            if (pieces.length != 2) {
                throw new InvalidFileTypeException("File name is invalid.");
            }

            String fileName = pieces[0];
            String fileExtension = pieces[1];

            if (!fileExtension.toLowerCase().equals("sugo")) {
                throw new InvalidFileTypeException("File does not have .sugo extension.");
            }

            String outputFilePath = Paths.get(dirPath, fileName + ".yo").toString();

            Scanner in = new Scanner(file);
            FileWriter out = new FileWriter(outputFilePath);
            while (in.hasNext()) {
                String line = in.nextLine();
                if (!line.startsWith(">> ")) {
                    in.close();
                    out.close();
                    File outputFile = new File(outputFilePath);
                    outputFile.delete();
                    throw new CompileErrorException();
                }
                out.write(line.substring(3) + "\n");
            }
            in.close();
            out.close();
        } catch (FileNotFoundException exception) {
            throw new FileSystemRelatedException("File not found: " + exception.getMessage());
        } catch (IOException exception) {
            throw new FileSystemRelatedException("IOException: " + exception.getMessage());
        }
    }
}

