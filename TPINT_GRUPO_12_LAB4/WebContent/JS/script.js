function mostrarModal() {
    document.getElementById("modalConfirmacion").style.display = "block";
}

function cerrarModal() {
    document.getElementById("modalConfirmacion").style.display = "none";
}

function confirmarAccion() {
	
    $.post("Servlets/admClientes_Servlet", {accion: "confirmar"}, function (respuesta) {
    	
    	console.log(respuesta);
    });

    cerrarModal();
}
