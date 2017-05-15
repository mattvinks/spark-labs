## Optional 2: Running Spark on Windows

If one has to do a Windows install, here is the magic

http://nishutayaltech.blogspot.com/2015/04/how-to-run-apache-spark-on-windows7-in.html

set winutils as described

For example

    \projects
    \projects\spark-labs
    \projects\spark-1.4.1-bin-hadoop2.4
    \projects\winutils\bin\winutils

System env variable

    HADOOP_HOME=\projects\winutils

With this download spark-1.4.1-bin-hadoop2.4
