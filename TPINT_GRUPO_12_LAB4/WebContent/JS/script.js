function mostrarModal() {
    document.getElementById("modalConfirmacion").style.display = "block";
}

function cerrarModal() {
    document.getElementById("modalConfirmacion").style.display = "none";
}

function confirmarAccion() {
	
    $.post("Servlets_AdministraCuentas", {accion: "confirmar"}, function (respuesta) {
    	
    	console.log(respuesta);
    	alert(respuesta);
    });

    cerrarModal();
}
