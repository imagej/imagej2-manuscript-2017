#!/bin/bash

process () {
  ijds=0
  ijp=0
  ij2ds=0
  ij2p=0

  for var in "$@"
  do
    git clone --depth 1 "git://github.com/$var" t 2> /dev/null
    cd t
    #echo " ======== $1 ======== "
    ijds=$(($ijds+$(git grep 'import ij'|grep -v 'import ij.plugin'|wc -l)))
    ijp=$(($ijp+$(git grep 'import ij.plugin'|wc -l)))
    ij2ds=$(($ij2ds+$(git grep 'import net.\(imagej\|imglib2\)'|wc -l)))
    ij2p=$(($ij2p+$(git grep 'import org.scijava'|wc -l)))

    #echo "ImageJ 1.x data structures: $ijds"
    #echo "ImageJ 1.x plugin framework: $ijp"
    #echo "ImageJ2 data structures: $ij2ds"
    #echo "SciJava framework: $ij2p"
    cd ..
    rm -rf t
  done

  dsr=$(echo "scale=10;$ij2ds/( $ij2ds+$ijds )" | bc)
  pr=$(echo "scale=10;$ij2p/( $ij2p+$ijp )" | bc)
  #echo "Data structure ratio: $dsr"
  #echo "Plugin framework ratio: $pr"
  #echo

  echo "var d$(($c+1)) = [[$dsr, $pr]];"
  createLegend
  createDataArray
  updateCounters
}

createLegend () {
  if [ $l -gt 4 ]
  then
    l=0
  fi
  if [ $s -gt 3 ]
  then
    s=0
  fi

  legend="$legend<tr>
  <td><div id=\"${shapes[$s]}\" style=\"${shapeColor[$s]}${colors[$l]};\">&nbsp;</div></td>
  <td style=\"padding-left: 10px;\">${labels[$c]}</td>
  </tr>"

}

createDataArray () {
  if [ $l -gt 4 ]
  then
    l=0
  fi
  if [ $s -gt 3 ]
  then
    s=0
  fi

  data="$data{ label: \"${labels[$c]}\", data: d$((c+1)), points: { symbol: \"${shapes[$s]}\", fillColor: \"${colors[$l]}\" }, color: \"${colors[$l]}\"  }, "
}

updateCounters () {
  c=$((c+1)) 
  s=$((s+1))
  l=$((l+1)) 
}

c=0
l=0
s=0
colors=("#277bab" "#6bbf20" "#cc0000" "#ffc000" "#de34f7")
shapes=("circle" "square" "diamond" "triangle")
shapeColor=("background: " "background: " "background: " "border-bottom-color: ")
labels=("TrackMate" "MaMuT" "SPIM Registration / Multiview-Reconstruction" "MoMA" "ASA" "KymographBuilder" "Z-Spacing Correction" "Trainable Weka Segmentation" "Pendent Drop" "SciView" "BigDataViewer" "Image Stitching" "Coloc 2" "MorphoLibJ")

data="var data1 = [ "
legend=""

echo "======== Data Points ========"
echo

process fiji/TrackMate
process fiji/MaMuT
process fiji/SPIM_Registration
process fjug/MoMA
process tferr/ASA
process fiji/KymographBuilder
process saalfeldlab/z-spacing
process fiji/Trainable_Segmentation
process adaerr/pendent-drop
process scenerygraphics/SciView
process bigdataviewer/bigdataviewer-core bigdataviewer/bigdataviewer_fiji
process fiji/Stitching
process fiji/Colocalisation_Analysis
process ijpb/MorphoLibJ

data="$data ];"
echo "======== Data Array ========"
echo
echo $data

echo "======== Legend ========"
echo
echo $legend
