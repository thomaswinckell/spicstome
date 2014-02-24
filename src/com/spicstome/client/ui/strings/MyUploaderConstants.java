package com.spicstome.client.ui.strings;

import gwtupload.client.IUploader.UploaderConstants;

public class MyUploaderConstants implements UploaderConstants {

	public MyUploaderConstants() {		
	}
	
	@Override
	public String uploadLabelCancel() {
		return null;
	}

	@Override
	public String uploadStatusCanceled() {
		return null;
	}

	@Override
	public String uploadStatusCanceling() {
		return null;
	}

	@Override
	public String uploadStatusDeleted() {
		return null;
	}

	@Override
	public String uploadStatusError() {
		return "Erreur dans le chargement de l'image.";
	}

	@Override
	public String uploadStatusInProgress() {
		return "Chargement de l'image en cours";
	}

	@Override
	public String uploadStatusQueued() {
		return null;
	}

	@Override
	public String uploadStatusSubmitting() {
		return null;
	}

	@Override
	public String uploadStatusSuccess() {
		return null;
	}

	@Override
	public String uploaderActiveUpload() {
		return null;
	}

	@Override
	public String uploaderAlreadyDone() {
		return null;
	}

	@Override
	public String uploaderBlobstoreError() {
		return null;
	}

	@Override
	public String uploaderBrowse() {
		return "Parcourir";
	}

	@Override
	public String uploaderInvalidExtension() {
		return "L'extension de l'image doit terminer par ";
	}

	@Override
	public String uploaderSend() {
		return "Charger";
	}

	@Override
	public String uploaderServerError() {
		return "Erreur dans le chargement de l'image !";
	}

	@Override
	public String submitError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploaderServerUnavailable() {
		return "Le serveur est indisponible.";
	}

	@Override
	public String uploaderTimeout() {
		return "Le temps de chargment a été écoulé. Veuillez Ré-essayer.";
	}

	@Override
	public String uploaderBadServerResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploaderBlobstoreBilling() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploaderInvalidPathError() {
		return "Le chemin de l'image est incorrect !";
	}
	
}
