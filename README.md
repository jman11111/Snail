# Snail

Coding Challenge for TopBloc

# How to Run

If on Windows, run the batch file run.bat to have Maven create and run the Jar file
Otherwise, run `mvn clean package` and navigate to the target directory to run the created JAR file

# Overview

## InMemoryDB

Uses Apache AOI to read Excel Workbooks into a Hashmap, which can then be extracted

## Student

The simple Java Object that is used to represent students, and also helps get their highest score

## ChallengeMain

Main class, extracts average of student scores and Women in Computer Science from InMemoryDB, and POSTs it to the specified URL
