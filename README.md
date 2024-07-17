# Illumio

### Task
A map-reduce program that reads a file and finds matches against a predefined set of words. There can be up to 10K entries in the list of predefined words.


### How to Run the Program

Follow below steps to run the program:
1. Open the project in IntelliJ and run maven clean-install
2. Once the build is successful, all the dependencies will be installed automatically.
3. Go to folder -> Common -> Constants.java and change the absolute path of input and output files. Example provided in the file itself.
4. Run the main class, WordMatchController.java
5. After the program execution is complete, go to data/Output directory.
6. There will be 4 files generated, the output is in file with extension .csv
7. Open the file in Excel(Windows)/Numbers(Mac) to verify results

### What has been tested?
I have tested the code on my local machine (Macbook). All details on implementation & performance optimisation are provided in a separate Approach.pdf file.
Below are the job runtimes:

####Scenario 1: 

Sentences: 30k , Input File size:2.5 MB , PreDefined Words: 800

####Results:
=> Cache creation - 9 msec
=> Complete job execution: 1337 msec


####Scenario 2:

Sentences: 60k , Input File size:5 MB , PreDefined Words: 2002

####Results:

=> Cache creation - 17 msec
=> Complete job execution: 1418 msec

** All assumptions for the given task are included in Approach.pdf
