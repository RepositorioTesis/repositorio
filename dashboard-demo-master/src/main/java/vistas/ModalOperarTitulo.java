package vistas;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional.TxType;

import org.hibernate.HibernateException;

import utils.HibernateUtil;
import it.portus.addon.numberfield.SpinnerNumberField;
import it.portus.addon.numberfield.TextualNumberField;
import it.portus.addon.numberfield.widgetset.shared.NumberFormat;
import it.portus.addon.numberfield.widgetset.shared.TextAlignment;

import com.google.gwt.core.client.Callback;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.demo.dashboard.ModalCallback;
import com.vaadin.demo.domain.Cotizacion;
import com.vaadin.demo.domain.Especie;
import com.vaadin.demo.domain.Operacion;
import com.vaadin.demo.domain.Tenencia;
import com.vaadin.demo.domain.UsuarioDetalle;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class ModalOperarTitulo extends ModalCallback{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2239168802623411282L;
	private Especie especie;
	private TextField txtMonto;
	public ModalOperarTitulo(Callback<Boolean, Boolean> callback,Especie especie) {
		super("Operar", "Cancelar", "Operar - "+especie.getEspecie(), callback);
		this.especie = especie;
		txtMonto = new TextField("Monto");
		txtMonto.setValue("0.01");
		txtMonto.setImmediate(true);
		txtMonto.addTextChangeListener(new TextChangeListener() {
			
			@Override
			public void textChange(TextChangeEvent event) {
				try {
					setOkCaption(Double.parseDouble(event.getText()) > 0d ?"Comprar" :"Vender");
					enableAction(true);
				} catch (Exception e) {
					enableAction(false);
				}
			}
		});
		txtMonto.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				try {
					Double foo = Double.parseDouble(txtMonto.getValue()) ;
				
				} catch (Exception e) {
					Notification.show("Dato Incorrecto", "Por favor ingrese el monto de la operacion. El mismo debe ser un valor numerico", Type.HUMANIZED_MESSAGE);
				}
				
			}
		});
		
		setLayout(txtMonto);
		
	}

	@Override
	protected void pressOk() {
		Operacion operacion = new Operacion();
		Tenencia tenencia = Tenencia.getTenencia(especie.getEspecie());
		if (tenencia == null){
			tenencia = new Tenencia();
			tenencia.setEliminado(false);
			tenencia.setEspecie(especie.getEspecie());
			tenencia.setUsuario(UsuarioDetalle.getCurrentUser().getUsuario());
			try {
				HibernateUtil.saveEntity(tenencia);
			} catch (Exception e) {
				Notification.show(e.getMessage(),Type.HUMANIZED_MESSAGE);
			}
			tenencia = Tenencia.getTenencia(especie.getEspecie());
		}
		operacion.setTenencia(tenencia.getTenencia());
		operacion.setFecha(new Date());
		Double nominal = Cotizacion.getCotizacion(especie.getEspecie());
		operacion.setValor((Double.parseDouble(txtMonto.getValue())/nominal)*100);
		try{
			HibernateUtil.saveEntity(operacion);
		} catch (Exception e){
			Notification.show(e.getCause().getMessage(),Type.HUMANIZED_MESSAGE);
		}
		UI.getCurrent().removeWindow(this);
		super.pressOk();
	}
}
