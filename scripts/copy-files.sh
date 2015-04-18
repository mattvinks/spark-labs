#!/bin/bash
hosts=$(cat ~/hosts | grep -v '#')
for host in $hosts
do
	      echo "------------------" $host "------------"  
        # spark
        rsync -avz --delete --exclude 'logs/*' --exclude 'pids/*' --exclude "work/*" -e 'ssh -o StrictHostKeyChecking=no' ~/spark/  $host:spark/

        # tachyon
        #rsync -avz --delete --exclude 'logs/*' --exclude 'pids/*' --exclude underfs --exclude journal -e 'ssh -o StrictHostKeyChecking=no' ~/tachyon/  $host:tachyon/
done
