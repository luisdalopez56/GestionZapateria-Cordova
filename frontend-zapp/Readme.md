# ZapApp

## Preparación del entorno

```console
$ npm install -g cordova
$ cordova create zapapp com.iesvdc.dam.acceso ZapApp
$ cd zapapp
$ cordova platform add browser
$ npm install jquery
$ npm install popper.js
$ npm install bootstrap
```

Ahora, de los directorios node_modules que se han creado 
para cada una de las instalaciones. Me creo un script añadir dependencias:

```bash
#!/bin/bash

echo "Añadiendo jQuery"
cp node_modules/jquery/dist/jquery.min.js node_modules/jquery/dist/jquery.min.map www/vendor/js
echo "Añadiendo PopperJS"
cp node_modules/popper.js/dist/popper.min.js node_modules/popper.js/dist/popper.min.js.map www/vendor/js
echo "Añadiendo Bootstrap"
cp node_modules/bootstrap/dist/css/bootstrap.min.css www/vendor/css
cp node_modules/bootstrap/dist/js/bootstrap.min.js node_modules/bootstrap/dist/js/bootstrap.min.js.map www/vendor/js

```


