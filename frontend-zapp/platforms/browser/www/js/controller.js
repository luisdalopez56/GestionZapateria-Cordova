/**
 * Este Objeto es usado para controlar el panel activo en cada momento
 * y es llamado desde los otros objetos.
 */

// El controlador es un objeto
$.controller = {};

// propiedades del controlador (atributos)
$.controller.host="localhost";
$.controller.port="8080";
$.controller.api="api";

$.controller.url= "http://"+$.controller.host+":"+$.controller.port+
    "/"+$.controller.api+"/";

$.controller.username="";
$.controller.password="";
$.controller.active_panel = "";

/**
 * Esta función gestiona qué panel está activo en cada momento (sólo puede
 * haber uno)
 * @param {type} panel_name el nombre del panel a activar
 */
$.controller.activate = function (panel_name) {
    $($.controller.active_panel).hide();
    $(panel_name).show();
    $.controller.active_panel = panel_name;
};

/**
 * Función para recoger los datos del Login
 */
$.controller.login = function () {
    $.controller.username = $("#username").val();
    $.controller.password = $("#password").val();
    $.controller.activate("#panel_main");
};

/**
 * Función para gestionar el panel de mensajes de error.
 * @param {type} title título del error
 * @param {type} msg mensaje del error (descripción)
 */
$.controller.error = function (title, msg) {
    var caja_error = $("#panel_error");
    caja_error.empty();
    caja_error.append('<h3>' + title + '</h3>');
    caja_error.append('<p>' + msg + '</p>');
    // cargamos el panel error
    $.controller.activate("#panel_error");
};

/**
 * Función para gestionar los códigos de retorno (error) del servidor
 * tras hacer las peticiones REST
 * @param {type} codigo
 */
$.controller.errorManager = function (codigo) {
    switch (codigo) {
        case 500: // error interno
            $.controller.error("Error 500", "No se ha podido completar la operación en el servidor");
            break;
        case 401: // no autorizado
            $.controller.activate("#panel_login");
            break;
        case 204: // sin respuesta (ej. tras un delete o un put
            $.controller.activate("#panel_main");
            break;
        default:
            $.controller.error("Error de conexión", "En estos momentos no ha sido posible acceder al servidor");
    }
}; 

/**
 * Función para gestionar la autorización contra el servidor.
 * Puedes cambiarla para hacerlo con sesiones, sessionStorage, token, OAuth...
 * @param {type} xhr las cabeceras
 */
$.controller.authorize = function(xhr) {
    xhr.setRequestHeader ("Authorization", "Basic " +  
        btoa($.controller.username+":"+$.controller.password));
};

/**
 * Función para crear todos los "onClick" de los menús y
 * asociarlos con cada panel correspondiente.
 */
$.controller.init = function (panel_inicial) {
    $('[id^="menu_"]').each(function () {
        var $this = $(this);
        var menu_id = $this.attr('id');
        var panel_id = menu_id.replace('menu_', 'panel_');

        $("#" + menu_id).click(function () {
            $.controller.activate("#" + panel_id);
        });
        console.log("id_menu::" + menu_id + "  id_panel" + panel_id);
    });
    $(".panel").hide();
    $(panel_inicial).show();
    $.controller.active_panel = panel_inicial;

    //LISTADO CLIENTES
    $("#menu_cli_read").click(()=>{
        $.controller.ClienteDetalleREST();
    });
    
    //ELIMINAR CLIENTE
    $("#menu_cli_delete").click(()=>{
        $.controller.doGet($.controller.url+"cliente",(datos)=>{
            $("#select_cli_delete").empty();
            
            datos.forEach(cliente => {
                $("#select_cli_delete").append('<option value="'+
                    cliente.id+'">'+
                    cliente.apellidos+", "+cliente.nombre+
                    "</option>")
            });
        });
    });

    //UPDATE CLIENTE
    $("#menu_cli_update").click(()=>{
        $.controller.ClienteUpdateREST();
    });

//-----------------------------CATEGORÍAS----------------------------------------------
    //LISTADO CATEGORIAS
    $("#menu_cat_read").click(()=>{
        /*$.controller.doGet($.controller.url+"productocategoria",function (json) {
            $("#panel_cat_read").empty();
            $("#panel_cat_read").append('<h3>CATEGORIAS</h3>');
            var table = $('<table />').addClass('table table-stripped');
            table.append($('<thead />').append($('<tr />').append('<th>id</th>', '<th>nombre</th>')));
            var tbody = $('<tbody />');
            for (var clave in json) {
                // le damos a cada fila un ID para luego poder recuperar los datos para el formulario en el siguiente paso
                tbody.append($('<tr/>').append('<td>' + json[clave].id + '</td>',
                        '<td>' + json[clave].nombre + '</td>'));
            }
            table.append(tbody);
            $("#panel_cat_read").append($('<div />').append(table));
            $('tr:odd').css('background', '#CCCCCC');
            $.controller.activate($("#panel_cat_read"));
        });*/
        $.controller.CategoriaDetalleREST();
    });

    //ELIMINAR CATEGORIAS
    $("#menu_cat_delete").click(()=>{
        $.controller.doGet($.controller.url+"productocategoria",(datos)=>{
            $("#select_cat_delete").empty();
            
            datos.forEach(categoria => {
                $("#select_cat_delete").append('<option value="'+
                    categoria.id+'">'+categoria.nombre+"</option>")
            });
        });
    });

    //UPDATE CATEGORIAS
    $("#menu_cat_update").click(()=>{
        $.controller.CategoriaUpdateREST();
    });
};


//Métodos de interacción de los botones de nuestra APP--------------------------------------
//BOTON CREATE CLIENTE
$("#btn_cli_create").click(()=>{
    var datos = {
        'nombre': $("#nombre_cli_create").val(),
        'apellidos': $("#apellidos_cli_create").val(),
        'dni': $("#dni_cli_create").val()
    };
    //comprobamos que en el formulario haya datos
    if (datos.nombre.length > 2 ) {
        $.controller.doPost(
            $.controller.url+"cliente",
            datos,
            function () {
                $("#menu_cli_read").click();
            });
    }
});

//BOTON ELIMINAR CLIENTE
$("#btn_cli_delete").click(()=>{
    id = $('#select_cli_delete').val();
    if (id !== undefined) {
        $.controller.doDelete(
            $.controller.url+"cliente",
            id,
            function () {
                $("#menu_cli_read").click();
            });
    }
});

//BOTON CREAR CATEGORIA
$("#btn_cat_create").click(()=>{
    var datos = {
        'nombre': $("#nombre_cat_create").val()
    };
    //comprobamos que en el formulario haya datos
    if (datos.nombre.length > 2 ) {
        $.controller.doPost(
            $.controller.url+"productocategoria",
            datos,
            function () {
                $("#menu_cat_read").click();
            });
    }
});

//BOTON ELIMINAR CATEGORIA
$("#btn_cat_delete").click(()=>{
    id = $('#select_cat_delete').val();
    if (id !== undefined) {
        $.controller.doDelete(
            $.controller.url+"productocategoria",
            id,
            function () {
                $("#menu_cat_read").click();
            });
    }
});

//Funciones de nuestra app-------------------------------------------------------------------
$.controller.ClienteUpdateREST = function (id, envio) {
    // si no le pasamos parámetro, hay que sacar la lista para 
    // pulsar sobre quien queremos actualizar
    if (id === undefined) {
        $.controller.doGet(
            $.controller.url+"cliente",
            function (json) {
                $("#panel_cli_read").empty();
                $("#panel_cli_read").append('<h3>Pulse sobre un cliente</h3>');
                var table = $('<table />').addClass('table table-stripped');
                table.append($('<thead />').append($('<tr />').append('<th>id</th>', '<th>nombre</th>', '<th>apellidos</th>', '<th>dni</th>')));
                var tbody = $('<tbody />');
                for (var clave in json) {
                    // le damos a cada fila un ID para luego poder recuperar los datos para el formulario en el siguiente paso
                    tbody.append($('<tr id="fila_' + json[clave].id + '" onclick="$.controller.ClienteUpdateREST(' + json[clave].id + ')"/>').append('<td>' + json[clave].id + '</td>',
                            '<td>' + json[clave].nombre + '</td>', '<td>' + json[clave].apellidos + '</td>', '<td>' + json[clave].dni + '</td>'));
                }
                table.append(tbody);
                $("#panel_cli_read").append($('<div />').append(table));
                $('tr:odd').css('background', '#CCCCCC');
                $.controller.activate($("#panel_cli_read"));
            });
    } else if (envio === undefined) {
        var seleccion = "#fila_" + id + " td";
        var cli_id = ($(seleccion))[0];
        var cli_nombre = ($(seleccion))[1];
        var cli_apellidos = ($(seleccion))[2];
        var cli_dni = ($(seleccion))[3];
        $("#u_cli_id").val(cli_id.childNodes[0].data);
        $("#u_cli_nombre").val(cli_nombre.childNodes[0].data);
        $("#u_cli_apellidos").val(cli_apellidos.childNodes[0].data);
        $("#u_cli_dni").val(cli_dni.childNodes[0].data);
        // cargamos el panel 
        $.controller.activate("#panel_cli_update");
    } else {
        //HACEMOS LA LLAMADA REST
        var datos = {
            'id': $("#u_cli_id").val(),
            'nombre': $("#u_cli_nombre").val(),
            'apellidos': $("#u_cli_apellidos").val(),
            'dni': $("#u_cli_dni").val()
        };
        // comprobamos que en el formulario haya datos...
        if (datos.nombre.length > 2 && datos.apellidos.length > 2) {
            // doPut(target, id, datos, fn_exito)
            $.controller.doPut(
                $.controller.url+"cliente",
                $("#u_cli_id").val(),
                datos,
                function() { 
                    // esto es lo que se ejecuta cuando tengamos éxito tras el PUT
                    $("#menu_cli_read").click();
                }
            );
            // cargamos el panel con id r_alumno.
            $.controller.activate($("#panel_cli_read"));
        }
    }
};

$.controller.ClienteDetalleREST = function (id, envio) {
    // pulsar sobre quien queremos ver detalle
    if (id === undefined) {
        $.controller.doGet(
            $.controller.url+"cliente",
            function (json) {
                $("#panel_cli_read").empty();
                $("#panel_cli_read").append('<h3>Pulse sobre un cliente</h3>');
                var table = $('<table />').addClass('table table-stripped');
                table.append($('<thead />').append($('<tr />').append('<th>id</th>', '<th>nombre</th>', '<th>apellidos</th>', '<th>dni</th>')));
                var tbody = $('<tbody />');
                for (var clave in json) {
                    // le damos a cada fila un ID para luego poder recuperar los datos para el formulario en el siguiente paso
                    tbody.append($('<tr id="fila_' + json[clave].id + '" onclick="$.controller.ClienteDetalleREST(' + json[clave].id + ')"/>').append('<td>' + json[clave].id + '</td>',
                            '<td>' + json[clave].nombre + '</td>', '<td>' + json[clave].apellidos + '</td>', '<td>' + json[clave].dni + '</td>'));
                }
                table.append(tbody);
                $("#panel_cli_read").append($('<div />').append(table));
                $('tr:odd').css('background', '#CCCCCC');
                $.controller.activate($("#panel_cli_read"));
            });
    } else if (envio === undefined) {
        var seleccion = "#fila_" + id + " td";
        var cli_id = ($(seleccion))[0];

        $.controller.doGet(
            $.controller.url+"cliente"+"/"+cli_id.childNodes[0].data+"/"+"direccion",
            function (direcciones){
                $("#panel_cli_detalle").empty();
                $("#panel_cli_detalle").append('<h3>Direcciones</h3>');
                var table = $('<table />').addClass('table table-stripped');
                table.append($('<thead />').append($('<tr />').append('<th>id</th>', '<th>nombre</th>', '<th>calle</th>', '<th>cp</th>', '<th>localidad</th>')));
                var tbody = $('<tbody />');
                for (var clave in direcciones) {
                    // le damos a cada fila un ID para luego poder recuperar los datos para el formulario en el siguiente paso
                    tbody.append($('<tr/>').append('<td>' + direcciones[clave].idDireccion + '</td>',
                            '<td>' + direcciones[clave].nombre + '</td>', '<td>' + direcciones[clave].nombreVia + '</td>', '<td>' + direcciones[clave].cp.cp + '</td>', '<td>' + direcciones[clave].cp.localidad + '</td>'));
                }
                table.append(tbody);
                $("#panel_cli_detalle").append($('<div />').append(table));
                $('tr:odd').css('background', '#CCCCCC');
                $.controller.activate($("#panel_cli_detalle"));
            }
        )
    } else {
        
    }
};

$.controller.CategoriaUpdateREST = function (id, envio) {
    // si no le pasamos parámetro, hay que sacar la lista para 
    // pulsar sobre quien queremos actualizar
    if (id === undefined) {
        $.controller.doGet(
            $.controller.url+"productocategoria",
            function (json) {
                $("#panel_cat_read").empty();
                $("#panel_cat_read").append('<h3>Pulse sobre una categoria</h3>');
                var table = $('<table />').addClass('table table-stripped');
                table.append($('<thead />').append($('<tr />').append('<th>id</th>', '<th>nombre</th>')));
                var tbody = $('<tbody />');
                for (var clave in json) {
                    // le damos a cada fila un ID para luego poder recuperar los datos para el formulario en el siguiente paso
                    tbody.append($('<tr id="fila_' + json[clave].id + '" onclick="$.controller.CategoriaUpdateREST(' + json[clave].id + ')"/>').append('<td>' + json[clave].id + '</td>',
                            '<td>' + json[clave].nombre + '</td>'));
                }
                table.append(tbody);
                $("#panel_cat_read").append($('<div />').append(table));
                $('tr:odd').css('background', '#CCCCCC');
                $.controller.activate($("#panel_cat_read"));
            });
    } else if (envio === undefined) {
        var seleccion = "#fila_" + id + " td";
        var cat_id = ($(seleccion))[0];
        var cat_nombre = ($(seleccion))[1];
        $("#u_cat_id").val(cat_id.childNodes[0].data);
        $("#u_cat_nombre").val(cat_nombre.childNodes[0].data);
        // cargamos el panel 
        $.controller.activate("#panel_cat_update");
    } else {
        //HACEMOS LA LLAMADA REST
        var datos = {
            'id': $("#u_cat_id").val(),
            'nombre': $("#u_cat_nombre").val()
        };
        // comprobamos que en el formulario haya datos...
        if (datos.nombre.length > 2 ) {
            // doPut(target, id, datos, fn_exito)
            $.controller.doPut(
                $.controller.url+"productocategoria",
                $("#u_cat_id").val(),
                datos,
                function() { 
                    // esto es lo que se ejecuta cuando tengamos éxito tras el PUT
                    $("#menu_cat_read").click();
                }
            );
            // cargamos el panel con id r_alumno.
            $.controller.activate($("#panel_cat_read"));
        }
    }
};

$.controller.CategoriaDetalleREST = function (id, envio) {
    // pulsar sobre quien queremos ver detalle
    if (id === undefined) {
        $.controller.doGet(
            $.controller.url+"productocategoria",
            function (json) {
                $("#panel_cat_read").empty();
                $("#panel_cat_read").append('<h3>Pulse sobre una categoria</h3>');
                var table = $('<table />').addClass('table table-stripped');
                table.append($('<thead />').append($('<tr />').append('<th>id</th>', '<th>nombre</th>')));
                var tbody = $('<tbody />');
                for (var clave in json) {
                    // le damos a cada fila un ID para luego poder recuperar los datos para el formulario en el siguiente paso
                    tbody.append($('<tr id="fila_' + json[clave].id + '" onclick="$.controller.CategoriaDetalleREST(' + json[clave].id + ')"/>').append('<td>' + json[clave].id + '</td>',
                            '<td>' + json[clave].nombre + '</td>'));
                }
                table.append(tbody);
                $("#panel_cat_read").append($('<div />').append(table));
                $('tr:odd').css('background', '#CCCCCC');
                $.controller.activate($("#panel_cat_read"));
            });
    } else if (envio === undefined) {
        var seleccion = "#fila_" + id + " td";
        var cat_id = ($(seleccion))[0];

        $.controller.doGet(
            $.controller.url+"productocategoria"+"/"+cat_id.childNodes[0].data+"/"+"producto",
            function (productos){
                $("#panel_cat_detalleproducto").empty();
                $("#panel_cat_detalleproducto").append('<h3>Productos</h3>');
                var table = $('<table />').addClass('table table-stripped');
                table.append($('<thead />').append($('<tr />').append('<th>id</th>', '<th>rutaImagen</th>')));
                var tbody = $('<tbody />');
                for (var clave in productos) {
                    // le damos a cada fila un ID para luego poder recuperar los datos para el formulario en el siguiente paso
                    tbody.append($('<tr id="fila_' + productos[clave].id + '" onclick="$.controller.ProductoDetalleREST(' + productos[clave].id + ')"/>').append('<td>' + productos[clave].id + '</td>',
                            '<td>' + productos[clave].rutaImagen + '</td>'));
                }
                table.append(tbody);
                $("#panel_cat_detalleproducto").append($('<div />').append(table));
                $('tr:odd').css('background', '#CCCCCC');
                $.controller.activate($("#panel_cat_detalleproducto"));

                $("#menu_cli_update").click(()=>{
                    $.controller.ProductoDetalleREST();
                });
            }
        )
    } else {
        
    }
};

$.controller.ProductoDetalleREST = function(id, envio) {
    // pulsar sobre quien queremos ver detalle
    if (id === undefined) {
        
    } else if (envio === undefined) {
        var seleccion = "#fila_" + id + " td";
        var pro_id = ($(seleccion))[0];

        $.controller.doGet(
            $.controller.url+"productoidioma"+"/"+"producto"+"/"+pro_id.childNodes[0].data,
            function (productoidi){
                $("#panel_cat_detalleproducto").empty();
                $("#panel_cat_detalleproducto").append('<h3>Idiomas del producto</h3>');
                var table = $('<table />').addClass('table table-stripped');
                table.append($('<thead />').append($('<tr />').append('<th>id</th>', '<th>cod_idioma</th>', '<th>nombre</th>', '<th>descripcion</th>', '<th>id_producto</th>')));
                var tbody = $('<tbody />');
                for (var clave in productoidi) {
                    // le damos a cada fila un ID para luego poder recuperar los datos para el formulario en el siguiente paso
                    tbody.append($('<tr/>').append('<td>' + productoidi[clave].id + '</td>',
                            '<td>' + productoidi[clave].cod_idioma + '</td>', '<td>' + productoidi[clave].nombre + '</td>', '<td>' + productoidi[clave].descripcion + '</td>', '<td>' + productoidi[clave].id_producto + '</td>'));
                }
                table.append(tbody);
                $("#panel_cat_detalleproducto").append($('<div />').append(table));
                $('tr:odd').css('background', '#CCCCCC');
                $.controller.activate($("#panel_cat_detalleproducto"));
            }
        )
    } else {
        
    }
}


/**
 * Función para hacer el GET al servicio REST
 * @param {type} target la URL donde está el servicio REST
 * @param {type} fn_exito función a llamar cuando tenga éxito 
 */
$.controller.doGet = function (target, fn_exito) {
    $.get({
        url: target,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        crossDomain: true,
        beforeSend: function (xhr) {
            $.controller.authorize(xhr);
        },
        success: fn_exito,
        error: function (xhr, status) {
            $.controller.errorManager(xhr.status);                
        }
    });
};

/**
 * Función para hacer el POST al servicio REST
 * @param {type} target la URL donde está el servicio REST
 * @param {type} datos los datos a subir
 * @param {type} fn_exito función a llamar cuando tenga éxito 
 */
$.controller.doPost = function(target, datos, fn_exito) {
    $.ajax({
            url: target,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(datos),
            beforeSend: function (xhr) {
                $.controller.authorize(xhr);
            },
            success: fn_exito,
            error: function (xhr, status) {
                $.controller.errorManager(xhr.status);                
            }
        });
};

/**
 * 
 * @param {type} target
 * @param {type} id
 * @param {type} datos
 * @param {type} fn_exito
 */
$.controller.doPut = function(target, id, datos, fn_exito) {
    $.ajax({
        url: target+'/'+id,
        type: 'PUT',
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(datos),
        beforeSend: function (xhr) {
            $.controller.authorize(xhr);
        },
        success: fn_exito,
        error: function (xhr, status) {
            $.controller.errorManager(xhr.status);                
        }
    });
};

/**
 * 
 * @param {type} target
 * @param {type} id
 * @param {type} fn_exito
 */
$.controller.doDelete = function(target, id, fn_exito) {
    $.ajax({
        url: target + '/' + id,
        type: 'DELETE',
        dataType: 'json',
        contentType: "application/json",
        beforeSend: function (xhr) {
            $.controller.authorize(xhr);
        },
        // data: JSON.stringify(datos),
        success: fn_exito,
        error: function (xhr, status) {
            $.controller.errorManager(xhr.status);                
        }
    });
};







