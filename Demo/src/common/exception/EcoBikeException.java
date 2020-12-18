package common.exception;;

public class EcoBikeException extends RuntimeException {

    public EcoBikeException() {

	}

	public EcoBikeException(String message) {
		super(message);
	}
}