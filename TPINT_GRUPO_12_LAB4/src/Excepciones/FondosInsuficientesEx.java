package Excepciones;

public class FondosInsuficientesEx extends Exception {

	public FondosInsuficientesEx(){
		
	}

	@Override
	public String getMessage() {
	
		return "Fondos insuficientes para realizar esta operacion!";
	}
	
	
}
