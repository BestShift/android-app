#!/bin/bash
# Compile the document

if [ "$1" = "clean" ]; then
	echo "Cleaning please wait ..."
	rm -f ./*~
	rm -rf ./*.aux
	rm -rf ./*.bbl
	rm -rf ./*.blg
	rm -rf ./*.d
	rm -rf ./*.fls
	rm -rf ./*.ilg
	rm -rf ./*.ind
	rm -rf ./*.toc*
	rm -rf ./*.lot*
	rm -rf ./*.lof*
	rm -rf ./*.log
	rm -rf ./*.idx
	rm -rf ./*.out*
	rm -rf ./*.nlo
	rm -rf ./*.nls
	rm -rf document.pdf
	rm -rf document.ps
	rm -rf document.dvi
	rm -rf ./*#* 
	echo "Cleaning complete!"
	exit
elif [ "$1" = "compile" ]; then
	echo "Compiling your file...please wait...!"
	pdflatex -interaction=nonstopmode document.tex
	bibtex document.aux 	
	makeindex document.aux
	makeindex document.idx
	makeindex document.nlo -s nomencl.ist -o document.nls
	pdflatex -interaction=nonstopmode document.tex
	makeindex document.nlo -s nomencl.ist -o document.nls
	pdflatex -interaction=nonstopmode document.tex
	echo "Cleaning please wait ..."
	rm -f ./*~
	rm -rf ./*.aux
	rm -rf ./*.bbl
	rm -rf ./*.blg
	rm -rf ./*.d
	rm -rf ./*.fls
	rm -rf ./*.ilg
	rm -rf ./*.ind
	rm -rf ./*.toc*
	rm -rf ./*.lot*
	rm -rf ./*.lof*
	rm -rf ./*.log
	rm -rf ./*.idx
	rm -rf ./*.out*
	rm -rf ./*.nlo
	rm -rf ./*.nls
	rm -rf ./*#* 
	echo "Cleaning complete!"
	echo "Success!"
	exit
else 
	echo "Shell script for compiling the document"
	echo "Usage: sh ./compile.sh [OPTIONS]"
	echo "[option]  compile: Compiles the given TeX file"
	echo "[option]  clean: removes temporary files"
	exit
fi
