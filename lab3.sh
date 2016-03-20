#!/bin/bash
javac runMain.java ParseCmd.java Converters.java Recover.java Account.java 
java runMain -p password.txt -d english.0 
sort -d hashes.txt > sorted.txt
