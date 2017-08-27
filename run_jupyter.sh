#!/bin/bash

#export SPARK_HOME="$HOME/apps/spark"
export PYSPARK_PYTHON=python3 
export PYSPARK_DRIVER_PYTHON="jupyter" 
export PYSPARK_DRIVER_PYTHON_OPTS="notebook" 
~/spark/bin/pyspark


## one liner
 #PYSPARK_PYTHON=python3 PYSPARK_DRIVER_PYTHON="jupyter" PYSPARK_DRIVER_PYTHON_OPTS="notebook" ~/spark/bin/pyspark
