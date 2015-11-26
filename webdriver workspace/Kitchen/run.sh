#!/bin/bash
echo "password" | sudo xhost +
cd /home/kustommade/workspace/Kitchen
cp -r /home/kustommade/workspace/Kitchen/XSLT_Reports /home/kustommade/testreports/kitchen/
echo "password" | sudo chmod 777 -R /home/kustommade/workspace
ant
