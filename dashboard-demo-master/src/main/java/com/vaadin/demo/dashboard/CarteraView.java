/**
ç * DISCLAIMER
 * 
 * The quality of the code is such that you should not copy any of it as best
 * practice how to build Vaadin applications.
 * 
 * @author jouni@vaadin.com
 * 
 */

package com.vaadin.demo.dashboard;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import utils.Controls;
import utils.HibernateUtil;
import vistas.ModalOperarTitulo;

import com.google.gwt.core.client.Callback;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.Not;
import com.vaadin.demo.domain.Cartera;
import com.vaadin.demo.domain.Especie;
import com.vaadin.demo.domain.UsuarioDetalle;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class CarteraView extends VerticalLayout implements View {


	private VerticalLayout vtlMain;
	private Table tblCartera;
	private IndexedContainer ic;

    @Override
    public void enter(ViewChangeEvent event) {
        setSizeFull();
        addStyleName("timeline");

        Label header = new Label("Mi Cartera");
        header.addStyleName("h1");
        addComponent(header);

        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.setWidth("100%");
        toolbar.setSpacing(true);
        toolbar.setMargin(true);
        toolbar.addStyleName("toolbar");
        addComponent(toolbar);

        HashMap<String, Especie> especies = new HashMap<String, Especie>();
        for(Especie especie : Especie.getAll()){
        	especies.put(especie.getEspecie(), especie);
        }
        final ComboBox cmbEspecies = Controls.crearCombo(especies);
        cmbEspecies.setWidth("300px");
        toolbar.addComponent(cmbEspecies);
        cmbEspecies.addShortcutListener(new ShortcutListener("Add",
                KeyCode.ENTER, null) {

            @Override
            public void handleAction(Object sender, Object target) {
            }
        });

        Button add = new Button("Operar");
        add.addStyleName("default");
        add.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	try {
            	UI.getCurrent().addWindow(new ModalOperarTitulo(new Callback<Boolean, Boolean>() {

					@Override
					public void onFailure(Boolean reason) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Boolean result) {
						refreshTable();
						
					}
				},(Especie) cmbEspecies.getValue()));
            	} catch (Exception e){
            		Notification.show("Seleccione un titulo para operar.",Type.HUMANIZED_MESSAGE);
            	}
            }
        });
        toolbar.addComponent(add);
        toolbar.setComponentAlignment(add, Alignment.BOTTOM_LEFT);

        Button clear = new Button("Analizar");
        clear.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	Notification.show("Envio de datos de analisis", Type.TRAY_NOTIFICATION);
            }
        });
        toolbar.addComponent(clear);
        toolbar.setComponentAlignment(clear, Alignment.BOTTOM_RIGHT);
        toolbar.setExpandRatio(clear, 1);

        
        /*--------------------------*/
        
        
        
        
        vtlMain = new VerticalLayout();
        
        
        tblCartera = new Table();
        tblCartera.setSizeFull();
        
        ic = new IndexedContainer();
        ic.addContainerProperty("fila", HorizontalLayout.class, new HorizontalLayout());
        ic.addContainerProperty("especie", String.class, "");
        ic.addContainerProperty("nominal", Double.class, 0d);
        ic.addContainerProperty("saldo", Double.class, 0d);
        ic.addContainerProperty("valor", Double.class,0d);

    	
        
        vtlMain.addComponent(tblCartera);
        
        refreshTable();
      
        
        addComponent(vtlMain);
        setExpandRatio(vtlMain, 1);
        
        

    }
    private void refreshTable(){
    	ic.removeAllItems();
    	for(Cartera cartera: UsuarioDetalle.getCurrentUser().obtenerCartera()){
    		HorizontalLayout htlFila = new HorizontalLayout();
    		htlFila.addComponent(new Label(cartera.getEspecie()));
    		htlFila.addComponent(new Label("$"+cartera.getNominal().toString()));
    		htlFila.addComponent(new Label("$"+cartera.getSaldo().toString()));
    		Button btnComprarTitulo = new Button();
//    		btnComprarTitulo.addClickListener(new ClickListener() {
//				
//				@Override
//				public void buttonClick(ClickEvent event) {
//					UI.getCurrent().addWindow(new ModalOperarTitulo(especies. new Callback<Boolean, Boolean>() {
//						
//						@Override
//						public void onSuccess(Boolean result) {
//							refreshTable();
//							
//						}
//						
//						@Override
//						public void onFailure(Boolean reason) {
//							// TODO Auto-generated method stub
//							
//						}
//					}));
					
//				}
//			});
//							
							
							
    		htlFila.addComponent(btnComprarTitulo);
    		htlFila.setSizeFull();
    		Object id = ic.addItem();
    		ic.getContainerProperty(id, "fila").setValue(htlFila);
    		ic.getContainerProperty(id, "especie").setValue(cartera.getEspecie());
    		ic.getContainerProperty(id, "nominal").setValue(cartera.getNominal());
    		ic.getContainerProperty(id, "valor").setValue(cartera.getValor());
    		ic.getContainerProperty(id, "saldo").setValue(cartera.getSaldo());
    	}
    	
    	tblCartera.setContainerDataSource(ic);
    	tblCartera.setVisibleColumns(new Object[]{"especie","nominal","valor","saldo"});
    }

}
