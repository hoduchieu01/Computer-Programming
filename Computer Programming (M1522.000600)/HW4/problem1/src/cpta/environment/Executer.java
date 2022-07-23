package cpta.environment;

import cpta.exceptions.FileSystemRelatedException;
import cpta.exceptions.InvalidFileTypeException;
import cpta.exceptions.RunTimeErrorException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Executer {
    int value;

    private void run(List<String> commands, File targetFile, Scanner inputIn, FileWriter out) throws Exception {
        for(String line : commands) {
            if (line.startsWith("INPUT")) {
                value = inputIn.nextInt();
            } else if (line.startsWith("OUTPUT")) {
                out.write(value + "");
            } else if (line.startsWith("ADD")) {
                String rhsString = line.split(" ")[1];
                int rhs = rhsString.equals("VALUE") ? value : Integer.parseInt(rhsString);
                value += rhs;
            } else if (line.startsWith("SUB")) {
                String rhsString = line.split(" ")[1];
                int rhs = rhsString.equals("VALUE") ? value : Integer.parseInt(rhsString);
                value -= rhs;
            } else if (line.startsWith("MUL")) {
                String rhsString = line.split(" ")[1];
                int rhs = rhsString.equals("VALUE") ? value : Integer.parseInt(rhsString);
                value *= rhs;
            } else if (line.startsWith("DIV")) {
                String rhsString = line.split(" ")[1];
                int rhs = rhsString.equals("VALUE") ? value : Integer.parseInt(rhsString);
                value /= rhs;
            } else if (line.startsWith("PRINT_SPACE")) {
                out.write(" ");
            } else if (line.startsWith("PRINT_TAB")) {
                out.write("\t");
            } else if (line.startsWith("PRINT")) {
                String rhsString = line.split(" ")[1];
                out.write(rhsString);
            } else if (line.startsWith("THROW")) {
                throw new Exception();
            } else if (line.startsWith("RUN")) {
                String rhsString = line.split(" ")[1];
                String path = Paths.get(targetFile.getParent(), rhsString).toString();
                File file = new File(path);
                Scanner scanner = new Scanner(file);
                List<String> nestedCommands = new ArrayList<>();
                while(scanner.hasNext()) {
                    nestedCommands.add(scanner.nextLine());
                }
                run(nestedCommands, file, inputIn, out);
                scanner.close();
            }
        }
    }

    public void execute(String targetFilePath, String inputFilePath, String outputFilePath)
            throws RunTimeErrorException, InvalidFileTypeException, FileSystemRelatedException {
        try {
            File targetFile = new File(targetFilePath);
            if (!targetFile.exists() || !targetFile.isFile()) {
                throw new FileSystemRelatedException("Target file does not exist or is a directory.");
            }

            File inputFile = new File(inputFilePath);
            if (!inputFile.exists() || !inputFile.isFile()) {
                throw new FileSystemRelatedException("Input file does not exist or is a directory.");
            }

            String[] pieces = targetFile.getName().split("\\.");
            if (pieces.length != 2) {
                throw new InvalidFileTypeException("Target file name is invalid.");
            }
            String targetFileExtension = pieces[1];
            if (!targetFileExtension.toLowerCase().equals("yo")) {
                throw new InvalidFileTypeException("Target file does not have .yo extension.");
            }

            Scanner targetIn = new Scanner(targetFile);
            Scanner inputIn = new Scanner(inputFile);
            FileWriter out = new FileWriter(outputFilePath);

            try {
                value = 0;
                List<String> commands = new ArrayList<>();
                while (targetIn.hasNext()) {
                    commands.add(targetIn.nextLine());
                }
                run(commands, targetFile, inputIn, out);
            } catch (Exception exception) {
                throw new RunTimeErrorException();
            }
            targetIn.close();
            inputIn.close();
            out.close();
        } catch (FileNotFoundException exception) {
            throw new FileSystemRelatedException("File not found: " + exception.getMessage());
        } catch (IOException exception) {
            throw new FileSystemRelatedException("IOException: " + exception.getMessage());
        }
    }
}

