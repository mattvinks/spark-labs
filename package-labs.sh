#!/bin/bash

## install nbconvert as
##      conda install nbconvert
##              or
##      pip install nbconvert

## cleanup html
find . -name "*.html" -print0 | xargs -0 rm -f

## convert ipynb notebooks into HTML
notebooks=$(find . -type f -name "*.ipynb" | grep -v ".ipynb_checkpoints" )

jupyter nbconvert ${notebooks}

# move files in right place
#mv -f private/README-python.html  .

## Change all the links from README.html
sed 's/ipynb/html/g' < README-python.html > a.html
mv -f a.html  README-python.html

## convert ipynb notebooks into HTML
md_files=$(find . -type f -name "*.md" | grep -v ".ipynb_checkpoints" )

echo "converting md files --> html"
for md_file in $md_files
do
    #echo $md_file
    output_file="${md_file%\.*}.html"
    #echo $output_file
    pandoc $md_file -f markdown -t html -o  $output_file
done

sed 's/\.md/\.html/g' < README-scala.html  | sed 's/\>md\</\>html\</g'  > a.html
mv -f a.html  README-scala.html

# create a zipfile

zip_file_name=$(basename `pwd`)
rm -f *.zip
zip -q -x '*.DS_Store*'  -x "*.log" -x "*out/*" -x '*.git*'  -x '*zip*'  -x '*metastore_db*' -x '*out' -x '*.ipynb_checkpoints*' -x '*not-using*' -r "$zip_file_name" .
