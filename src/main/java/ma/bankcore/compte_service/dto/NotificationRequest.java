package ma.bankcore.compte_service.dto;

public class NotificationRequest {
	private String destinataire;
    private String sujet;
    private String message;

    public NotificationRequest(String destinataire, String sujet, String message) {
        this.destinataire = destinataire;
        this.sujet = sujet;
        this.message = message;
    }

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
