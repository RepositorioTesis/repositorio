package com.vaadin.demo.dashboard;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class Modal extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8183916534788829335L;
	private Button btnCancel;
	private Button btnOk;
	private VerticalLayout lytRoot = new VerticalLayout();
	private String okLabel="";
	private String cancelLabel="";

	
	
	public Modal(String okLabel, String cancelLabel,String titulo){
		super(titulo);
		this.okLabel = okLabel;
		this.cancelLabel = cancelLabel;
		lytRoot.addComponent(new VerticalLayout());
		loadFooter();
		setModal(true);
        setClosable(false);
        setResizable(false);
        addStyleName("edit-dashboard");
		super.setContent(lytRoot);
		
		
	}
	
	public void setLayout(Component content){
		 if (lytRoot.getComponentCount() > 2) {
             // Remove the previous error message
			 lytRoot.removeComponent(lytRoot.getComponent(1));
         }
		 
		lytRoot.addComponentAsFirst(content);
		
	
	}
	private void loadFooter(){
	     lytRoot.addComponent(new HorizontalLayout() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4095924975351008491L;

			{
                 setMargin(true);
                 setSpacing(true);
                 addStyleName("footer");
                 setWidth("100%");
 
                 btnCancel = new Button(cancelLabel);
                 btnCancel.addClickListener(setCancelListener());
                 btnCancel.setClickShortcut(KeyCode.ESCAPE, null);
                 addComponent(btnCancel);
                 setExpandRatio(btnCancel, 1);
                 setComponentAlignment(btnCancel,
                         Alignment.TOP_RIGHT);

                 btnOk = new Button(okLabel);
                 btnOk.addStyleName("wide");
                 btnOk.addStyleName("default");
                 setOkListener();
                 btnOk.addClickListener(setOkListener());
                 btnOk.setClickShortcut(KeyCode.ENTER, null);
                 btnOk.setImmediate(true);
                 if(!okLabel.equals(""))
                	 addComponent(btnOk);
             }
         });
	}
	
	private ClickListener setOkListener(){
		return new ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1332996055596377876L;

			@Override
            public void buttonClick(ClickEvent event) {
            	pressOk();
            }
		};
	}
	
	protected void pressOk(){
		Notification.show("No Implementado");
	}
	
	private ClickListener setCancelListener(){
		return  new ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -8711699055397620367L;

			@Override
            public void buttonClick(ClickEvent event) {
              close();
            }
        };
	}

	protected void enableAction(Boolean p){
		btnOk.setEnabled(p);;
	}
	
	protected void setOkCaption(String caption){
		btnOk.setCaption(caption);
	}
	public void setAncho(String width){
		this.setWidth(width);
	}
}
