package demo.codeanalyzer.model;

/**
 * Stores details of problems found in the code
 * @author Deepa Sobhana, Seema Richard
 */
public class ErrorDescription {
    
    private String errorMessages;

    public void setErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
    }

	public String getErrorMessages() {
		return errorMessages;
	}

}
