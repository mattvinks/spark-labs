#!/bin/bash

## compresses json files

mkdir -p 'logs-json-gz'
for f in `ls logs-json`
do
  input="logs-json/$f"
  output="logs-json-gz/$f.gz"
  echo "compressing $input --> $output"
  gzip < $input > $output
done