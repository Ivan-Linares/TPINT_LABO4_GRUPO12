package Entidad;

public class Informe {
		
		private String Region;
		private int clientesActivos;
		private int clientesInactivos;
		private int clientesPendientes;
		private float saldoTotal;
		private float movimientosAltasCuenta;
		private float movimientosAltasPrestamo;
		private float movimientosPagoPrestamo;
		private float movimientosExtracciones;
		private float movimientosDeposito;
		
		public Informe() 
		{
			//this.setCBU(ContCBU++);
		}
		
		public String getRegion() {
			return Region;
		}
		public void setRegion(String region) {
			Region = region;
		}
		public int getClientesActivos() {
			return clientesActivos;
		}
		public void setClientesActivos(int clientesActivos) {
			this.clientesActivos = clientesActivos;
		}
		public int getClientesInactivos() {
			return clientesInactivos;
		}
		public void setClientesInactivos(int clientesInactivos) {
			this.clientesInactivos = clientesInactivos;
		}
		public int getClientesPendientes() {
			return clientesPendientes;
		}
		public void setClientesPendientes(int clientesPendientes) {
			this.clientesPendientes = clientesPendientes;
		}
		public float getSaldoTotal() {
			return saldoTotal;
		}
		public void setSaldoTotal(float saldoTotal) {
			this.saldoTotal = saldoTotal;
		}
		public float getMovimientosAltasCuenta() {
			return movimientosAltasCuenta;
		}
		public void setMovimientosAltasCuenta(float movimientosAltasCuenta) {
			this.movimientosAltasCuenta = movimientosAltasCuenta;
		}
		public float getMovimientosAltasPrestamo() {
			return movimientosAltasPrestamo;
		}
		public void setMovimientosAltasPrestamo(float movimientosAltasPrestamo) {
			this.movimientosAltasPrestamo = movimientosAltasPrestamo;
		}
		public float getMovimientosPagoPrestamo() {
			return movimientosPagoPrestamo;
		}
		public void setMovimientosPagoPrestamo(float movimientosPagoPrestamo) {
			this.movimientosPagoPrestamo = movimientosPagoPrestamo;
		}
		public float getMovimientosExtracciones() {
			return movimientosExtracciones;
		}
		public void setMovimientosExtracciones(float movimientosExtracciones) {
			this.movimientosExtracciones = movimientosExtracciones;
		}
		public float getMovimientosDeposito() {
			return movimientosDeposito;
		}
		public void setMovimientosDeposito(float movimientosDeposito) {
			this.movimientosDeposito = movimientosDeposito;
		}



}
