#!/bin/bash

echo "Añadiendo jQuery"
cp node_modules/jquery/dist/jquery.min.js node_modules/jquery/dist/jquery.min.map www/vendor/js
echo "Añadiendo PopperJS"
cp node_modules/popper.js/dist/popper.min.js node_modules/popper.js/dist/popper.min.js.map www/vendor/js
echo "Añadiendo Bootstrap"
cp node_modules/bootstrap/dist/css/bootstrap.min.css www/vendor/css
cp node_modules/bootstrap/dist/js/bootstrap.min.js node_modules/bootstrap/dist/js/bootstrap.min.js.map www/vendor/js
