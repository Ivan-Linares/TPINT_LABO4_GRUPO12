const cuentas = [
    {
        cliente: "Juan Pérez",
        fechaCreacion: "01-01-2022",
        tipo: "Cuenta de Ahorros",
        numeroCuenta: "1234-5678-9101-1121",
        CBU: "0000000000000123456789",
        saldo: "$1,500.00",
        movimientos: [
            { fecha: "02-01-2022", descripcion: "Depósito", monto: "$200.00" },
            { fecha: "03-01-2022", descripcion: "Retiro", monto: "-$50.00" }
        ]
    },
    {
        cliente: "Ana García",
        fechaCreacion: "15-02-2022",
        tipo: "Cuenta Corriente",
        numeroCuenta: "2234-5678-9101-1121",
        CBU: "0000000000000223456789",
        saldo: "$3,200.00",
        movimientos: [
            { fecha: "16-02-2022", descripcion: "Depósito", monto: "$1,000.00" },
            { fecha: "17-02-2022", descripcion: "Compra", monto: "-$100.00" },
            { fecha: "18-02-2022", descripcion: "Transferencia recibida", monto: "$300.00" }
        ]
    }
];

function cargarHistorial() {
    const listaCuentas = document.getElementById('listaCuentas');
    const indexSeleccionado = parseInt(listaCuentas.value);
    const cuenta = cuentas[indexSeleccionado];
    const infoCuentaDiv = document.getElementById('infoCuenta');
    const historialMovimientos = document.getElementById('historialMovimientos');

    infoCuentaDiv.innerHTML = '';
    historialMovimientos.innerHTML = '';

    infoCuentaDiv.innerHTML = `
        <strong>Cliente:</strong> ${cuenta.cliente}<br>
        <strong>Fecha de Creación:</strong> ${cuenta.fechaCreacion}<br>
        <strong>Tipo de Cuenta:</strong> ${cuenta.tipo}<br>
        <strong>Número de Cuenta:</strong> ${cuenta.numeroCuenta}<br>
        <strong>CBU:</strong> ${cuenta.CBU}<br>
        <strong>Saldo:</strong> ${cuenta.saldo}<br>
    `;
    
    cuenta.movimientos.forEach(movimiento => {
        const li = document.createElement('li');
        li.textContent = `${movimiento.fecha} - ${movimiento.descripcion}: ${movimiento.monto}`;
        historialMovimientos.appendChild(li);
    });
}
