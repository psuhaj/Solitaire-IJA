#!/bin/sh

destination="lib"
file1="cards"

wget -q "http://www.stud.fit.vutbr.cz/~xtotha01/$file1.zip" -P $destination
unzip -q $destination/$file1.zip -d $destination
rm -f $destination/$file1.zip
