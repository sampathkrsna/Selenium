#!/bin/bash
echo "password" | sudo xhost +
cd /home/kustommade/workspace/Wardrobe
cp -r /home/kustommade/workspace/Wardrobe/XSLT_Reports /home/kustommade/testreports/wardrobe/
echo "password" | sudo chmod 777 -R /home/kustommade/workspace
ant
