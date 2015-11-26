#!/bin/bash
rm -f $HOME/.ssh/known_hosts
echo "localhost"  > $HOME/hosts
rm -rf $HOME/spark
rm -rf $HOME/tachyon
(cd ~/spark-labs; git clean -d -x -f; git stash; git checkout master; git pull)