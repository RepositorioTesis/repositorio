package com.vaadin.demo.dashboard;

import com.google.gwt.core.client.Callback;

public class ModalCallback extends Modal{
	private Callback<Boolean, Boolean> callback;
	public ModalCallback(String okLabel, String cancelLabel, String titulo,Callback<Boolean,Boolean> callback) {
		super(okLabel, cancelLabel, titulo);
		this.callback=callback;
	}
	@Override
	protected void pressOk(){
		callback.onSuccess(true);
	}
	
	
	
}
