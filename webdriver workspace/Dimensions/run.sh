#!/bin/bash
echo "password" | sudo xhost +
cd /home/kustommade/workspace/Dimensions
cp -r /home/kustommade/workspace/Dimensions/XSLT_Reports /home/kustommade/testreports/dimensions/
echo "password" | sudo chmod 777 -R /home/kustommade/workspace
ant
