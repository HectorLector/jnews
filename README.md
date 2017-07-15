# jnews
Craft-IT Coding Challenge "JAVA News API" (https://github.com/tomw1808/cc2017_07_java)
--------------------------------------------------------------------------------------
Author: David Seywald, d.seywald@gmail.com

Developed/Tested under Linux Mint 18.1 with openjdk version "1.8.0_131"
with Eclipse Version: 3.8.1

Powered by NewsApi: newsapi.org/

Used libraries:

 - jackson 2.8.9
 - sqlite-jdbc 3.19.3
 
 All libraries are located in the "lib" folder.
 
 A sample config file can be found in the "config" folder.
 Dont forget to enter your API KEY there.
 The config file encoding should be UTF-8
 
 Keyword filter is implemented (not case sensitive, but no weird unicode support)
 
 Interfaces are defined, so we can easily change the NewsProvider (newsapi.org) and
 the NewsDataSource (local sqlite database)
 
 Unit Tests/Proper comments are intentionally omitted for this small Proof of concept.
 
 Run the program with one parameter = path to the json config file.
 
 The program will spawn one thread for each specified news source
 and one thread for polling the database.
 
 
 Thank you.
 
